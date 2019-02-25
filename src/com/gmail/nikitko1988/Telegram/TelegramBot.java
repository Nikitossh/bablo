package com.gmail.nikitko1988.Telegram;

import com.gmail.nikitko1988.entity.Cost;
import com.gmail.nikitko1988.regexp.Shablonator;
import com.gmail.nikitko1988.persistent.DAO;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class TelegramBot extends TelegramLongPollingBot implements Runnable {
    private static String PROXY_HOST = "rfckc.teletype.live";
    private static Integer PROXY_PORT = 1080;
    private static String BOT_USERNAME = "myBabloBot";
    private static String BOT_TOKEN = "355265619:AAHIC3Gq3-tY4Wu_10Ifc9c8JIKBtBa1qvg";
    private Shablonator shablonator = new Shablonator();
    MessageHandler messageHandler = new MessageHandler();

    @Override
    public void run() {
        TelegramBot.startBot();
    }

    public static void main(String[] args) {
        startBot();
    }

    // Переопределяем конструктор для использования опций, в частности для proxy
    protected TelegramBot(DefaultBotOptions botOptions) throws SQLException {
        super(botOptions);
    }

    public static void startBot() {
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
        } catch (TelegramApiException | SQLException e) {
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
        /* Получаем текст из сообщения */
        Message message = new Message();
        if (update.hasMessage())
            message = update.getMessage();
        String messageText = message.getText();

        /* Теперь сверяем полученное сообщение и обрабатываем его
          Ниже всё что связано со статистикой */
        try {
        if (messageText.toLowerCase().equals("/stat")) {
            String monthStat = messageHandler.getStat();
            sendMsg(message, monthStat);
        }

        else if (messageText.toLowerCase().equals("/statcom")){
            String monthStatComment = messageHandler.getStatComment();
            sendMsg(message, monthStatComment);
        }

        /* Это общая справка */
        else if(messageText.toLowerCase().equals("/help")) {
            String help = messageHandler.getHelp();
            sendMsg(message, help);
        }

        /* А здесь добавление новых трат */
        // todo: сделать как-то покрасивее
        else if (messageText.matches(shablonator.TODAY) ||
                messageText.matches(shablonator.YESTERDAY) ||
                messageText.matches(shablonator.BEFORE_YESTERDAY) ||
                messageText.matches(shablonator.WITH_DATE)) {
            // Получаем данные полей из текста сообщения
            String[] costFields = shablonator.extractAllData(messageText);
            // Чекаем эти поля, чекаем валидность коста и добавляем в БД, с оповещением в Телегу
            if (Cost.checkFields(costFields)) {
                Cost cost = new Cost(costFields);
                Cost.addCost(cost);
                if (DAO.insertionCost(cost) != 0)
                    sendMsg(message, "Платеж: " + cost.toString() + " добавлен");
            }
        }

        /* Если не совпало ни с одним шаблоном  */
        else {
            sendMsg(message, "У меня нет инструкций на этот счет. Вызовите /help");
        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    // Шлём в телеграмм текст
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
