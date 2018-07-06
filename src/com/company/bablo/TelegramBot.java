package com.company.bablo;


import com.company.bablo.regexp.RegularExpressions;
import com.company.bablo.entity.Cost;
import com.company.bablo.persistent.DAO;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TelegramBot  extends TelegramLongPollingBot {
    private RegularExpressions regularExpressions = new RegularExpressions();

    public static void runBot() {
                TelegramLongPollingBot bot = new TelegramBot();
        // Инициализируем и запускаем бота.
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();


        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "myBabloBot";
    }

    @Override
    public String getBotToken() {
        return "355265619:AAHIC3Gq3-tY4Wu_10Ifc9c8JIKBtBa1qvg";
    }

    // Основной рабочий метод бота.
    // При получении объекта update, бот отрабатывает данный метод.
    @Override
    public void onUpdateReceived(Update update) {
        // Получаем из update объект message и работаем с ним.
        Message message = update.getMessage();
        // Получаем строку из message.
        String messageText = message.getText();

        // Обрабатываем строку на предмет совпадения с регулярным выражением.
        // Разбиваем на части, проверяем их и добавляем в БД.
        if (regularExpressions.isMatch(messageText)) {
            System.out.println("Найдено совпадение с регуляркой.");
            String[] costFields = regularExpressions.splitMessageText(messageText);
            System.out.println("Регулярка разбита на части и они переданы на проверку");
            System.out.print("value: " + costFields[0] + "  category: " + costFields[1] + "  comment: " + costFields[2]);
            Cost.checkCost(costFields);
            Cost cost = new Cost(costFields);
            // Добавляем дату, если не добавить, то будет NULL в БД
            DAO.insertionData(cost);
            System.out.println(cost);
            if (DAO.insertionCost(cost) != 0) {
                sendMsg(message, "Добавлен в базу данных.");
            }
        }

        // вывод справки по вводу траты.
        if (messageText.equals("Ввод трат")) {
            sendMsg(message, "Ввод трат осуществляется по шаблону \n" +
                    "100 food shop");
        }

        // Выборка статистики за этот месяц.
        if (messageText.equals("Статистика")) {
            String result = "";
            String total = "";

            try {
                ResultSet rsTotal = DAO.selectionTotalValuesMonth(0);

                if (rsTotal.next()) {
                    total = rsTotal.getString(1);
                }

                ResultSet rsCategory = DAO.selectionThisMonth();

                while (rsCategory.next()) {
                    String category = rsCategory.getString(1);
                    String value = rsCategory.getString(2);
                    /** Для вывода бюджета раскомменить строку ниже и amount в result*/
                    //String amount = rsCategory.getString(3);
                    result += category + " " + value + "\t "  + "\n";
                }
                result += "Total: \n" + total;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.print(result);
            sendMsg(message, result);

        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
