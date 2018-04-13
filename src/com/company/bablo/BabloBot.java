package com.company.bablo;

/**
 * Created by nik on 4/24/17.
 */

import com.company.bablo.regexp.RegularExpressions;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.bablo.Cost.getCategoriesList;


public class BabloBot extends TelegramLongPollingBot {
    static ConsoleController consoleThread;     // объект побочного потока
    private RegularExpressions regularExpressions = new RegularExpressions();


    public static void main(String[] args) {
        // Инициализируем и запускаем бота.
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BabloBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // Во втором потоке запускаем Контроллер работы в консоли!
        // При этом выход из консоли закрывает программу целиком!
        consoleThread = new ConsoleController();
        consoleThread.run();
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
            Cost cost = Cost.createCost(costFields);
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
                ResultSet rsTotal = DAO.selectionTotalValuesThisMonth(0);

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