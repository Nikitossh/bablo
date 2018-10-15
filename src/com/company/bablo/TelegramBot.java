package com.company.bablo;


import com.company.bablo.regexp.RegularExpressions;
import com.company.bablo.entity.Cost;
import com.company.bablo.persistent.DAO;
import com.company.bablo.regexp.Shablonator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.company.bablo.persistent.DAO.insertionCost;
import static com.company.bablo.persistent.DAO.insertionData;


public class TelegramBot extends TelegramLongPollingBot {
    private RegularExpressions regularExpressions = new RegularExpressions();
    private Shablonator shablonator = new Shablonator();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelegramBot());
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
        Message message = new Message();
        if (update.hasMessage()) {
            message = update.getMessage();
        }
        // Получаем строку из message.
        String messageText = message.getText();
        System.out.println(messageText);

        if (messageText.equals("stat")) {
            System.out.println("Какого хуя!");
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

                    result += category + " " + value + "\t "  + "\n";
                }
                result += "Total: \n" + total;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.print(result);
            sendMsg(message, result);
        }

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
            System.out.println("Не совпало ни с чем");
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
