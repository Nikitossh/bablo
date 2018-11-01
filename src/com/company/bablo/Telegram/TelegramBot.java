package com.company.bablo.Telegram;

import com.company.bablo.entity.Cost;
import com.company.bablo.persistent.DAO;
import com.company.bablo.persistent.Queries;
import com.company.bablo.persistent.Query;
import com.company.bablo.regexp.Shablonator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.company.bablo.persistent.DAO.insertionCost;
import static com.company.bablo.persistent.DAO.insertionData;

public class TelegramBot extends TelegramLongPollingBot {
    private static String PROXY_HOST = "ytajm.tgproxy.me";
    private static Integer PROXY_PORT = 1080;
    private static String BOT_USERNAME = "myBabloBot";
    private static String BOT_TOKEN = "355265619:AAHIC3Gq3-tY4Wu_10Ifc9c8JIKBtBa1qvg";
    private Shablonator shablonator = new Shablonator();
    MessageHandler messageHandler = new MessageHandler();

    // Переопределяем конструктор для использования опций, в частности для proxy
    protected TelegramBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    public static void main(String[] args) {
        // todo: Сделать логирование всех событий для отладки
        try {
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            // Set up Http proxy
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

            botsApi.registerBot(new TelegramBot(botOptions));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    // При получении объекта update, бот отрабатывает данный метод.
    @Override
    public void onUpdateReceived(Update update) {
        // Получаем из update объект message и работаем с ним.
        Message message = new Message();
        if (update.hasMessage()) {
            message = update.getMessage();
        }
        // Получаем строку из message.
        String messageText = message.getText();
        // todo: Это в лог, а не в консоль.
        System.out.println(messageText);

        if (messageText.toLowerCase().equals("/stat")) {
            String monthStat = messageHandler.getStat();
            sendMsg(message, monthStat);
        }

        else if (messageText.toLowerCase().equals("/statcat")){
            String monthStatComment = messageHandler.getStatComment();
            sendMsg(message, monthStatComment);
        }


        // todo: сделать как-то покрасивее
        else if (messageText.matches(shablonator.TODAY) ||
                messageText.matches(shablonator.YESTERDAY) ||
                messageText.matches(shablonator.BEFORE_YESTERDAY) ||
                messageText.matches(shablonator.WITH_DATE)) {
            String[] costFields = shablonator.extractAllData(messageText);
            if (Cost.checkCost(costFields)) {
                Cost cost = new Cost(costFields);
                sendMsg(message, "ВОТ: " + cost.toString());
                insertionData(cost);
                if (insertionCost(cost) != 0)
                    sendMsg(message, "Платеж: " + cost.toString() + " добавлен");
            }
        }

        /** Если не совпало ни с одним шаблоном  */
        else {
            sendMsg(message, "У меня нет инструкций на этот счет. Вызовите help");
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
