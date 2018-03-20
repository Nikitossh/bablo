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

        // Этот if для вывода справки по вводу траты.
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















//  Здесь все для Inline реализации
//        // Получаем inlineQuery от update
//        // inlineQuery это то что мы шлем в бота через via @myBabloBot
//        InlineQuery inlineQuery = update.getInlineQuery();
//
//        // Получаем строчку с запросом
//        if (inlineQuery != null) {
//
//            String query = inlineQuery.getQuery();
//
//            /**
//             * 1. Если запрос "main", то создаем AnswerInlineQuery, присваиваем ему Id
//             * 2.
//             */
//
//            if (query.equals("main")) {
//
//                // Создаем ответ на запрос
//                AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
//                answerInlineQuery.setInlineQueryId(inlineQuery.getId());
//                // Создаем тело ответа
//                List<InlineQueryResult> results = new ArrayList<>();
//
//                // Создаем результат типа Article и заполняем обязательные поля(id, title, replyMarkup)
//                InlineQueryResultArticle inlineQueryResultArticle = new InlineQueryResultArticle();
//                // id результата приравниваем id запроса
//                inlineQueryResultArticle.setId(inlineQuery.getId());
//                // Это всплывет у пользователя на экране
//                inlineQueryResultArticle.setTitle("Меню трат.");
//                // Выбираем клавиатуру под сообщением(ее мы создаём ниже)
//                inlineQueryResultArticle.setReplyMarkup(keyboardMain());
//
//                // Создаем сообщение и присваиваем его результату
//                InputTextMessageContent inputTextMessageContent = new InputTextMessageContent();
//                inputTextMessageContent.setMessageText("Выберите пункт меню:");
//                inlineQueryResultArticle.setInputMessageContent(inputTextMessageContent);
//                // Добавляем все это в тело ответа и передаем тело в ответ
//                results.add(inlineQueryResultArticle);
//                answerInlineQuery.setResults(results);
//
//                // Отвечаем на запрос.
//                try {
//                    answerInlineQuery(answerInlineQuery);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//
//        // Получаем колбэк запрос от инлайнКлавиатуры
//        CallbackQuery callbackQuery = update.getCallbackQuery();
//
//
//        if (callbackQuery != null) {
//            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
//            answerCallbackQuery.setShowAlert(false);
//            answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
//
//            System.out.println(callbackQuery.getData());
//            answerCallbackQuery.setText(setAnswerText(callbackQuery.getData()));
//
//            try {
//                answerCallbackQuery(answerCallbackQuery);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//            System.out.print(callbackQuery.getData());
//        }
//
//
//        System.out.println(callbackQuery);
//
//    }
//
//    // Присваиваем строку ответа на коллбэк исходя из поля data в нем
//    public String setAnswerText(String data) {
//        String result;
//        if (data.equals("newCost")) {result = "Creating a new cost. Please fill in all fields."; }
//
//        else if (data.equals("selectLast5")) result = "Last five costs were:";
//
//        else if (data.equals("deleteLast")) result = "Do you want to delete the last cost?";
//
//        else if (data.equals("inactive")) result = "This section is inactive now";
//
//        else result = "Чот подозрительно как вы попали в этот пункт меню!";
//
//        return result ;
//    }
//
//    // Клавиатура для основного меню.
//    private InlineKeyboardMarkup keyboardMain() {
//        InlineKeyboardMarkup mainMenuKeyboard = new InlineKeyboardMarkup();
//
//        List<InlineKeyboardButton> keyboardButtonFirstRow = new ArrayList<>();
//        List<InlineKeyboardButton> keyboardButtonsSecondRow = new ArrayList<>();
//
//        List<List<InlineKeyboardButton>> keyboardButtonList = new ArrayList<>();
//
//        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//        inlineKeyboardButton.setText("Внести трату");
//        inlineKeyboardButton.setCallbackData("newCost");
//        inlineKeyboardButton.setSwitchInlineQuery("switch");
//
//        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
//        inlineKeyboardButton2.setText("Последние 5");
//        inlineKeyboardButton2.setCallbackData("selectLast5");
//
//        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
//        inlineKeyboardButton3.setText("Удалить последнюю");
//        inlineKeyboardButton3.setCallbackData("deleteLast");
//
//        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
//        inlineKeyboardButton4.setText("Inactive");
//        inlineKeyboardButton4.setCallbackData("inactive");
//
//        keyboardButtonFirstRow.add(inlineKeyboardButton);
//        keyboardButtonFirstRow.add(inlineKeyboardButton2);
//        keyboardButtonsSecondRow.add(inlineKeyboardButton3);
//        keyboardButtonsSecondRow.add(inlineKeyboardButton4);
//
//        keyboardButtonList.add(keyboardButtonFirstRow);
//        keyboardButtonList.add(keyboardButtonsSecondRow);
//        mainMenuKeyboard.setKeyboard(keyboardButtonList);
//
//        return mainMenuKeyboard;
//    }

    //
    // Клавиатура Главного меню типа ReplyKeyboardMarkup.
    private ReplyKeyboardMarkup keyboardMainMenu() {
        // Создание виртуальной клавиатуры.
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboad(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        // Создание массива кнопок на этой клавиатуре.
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Первый ряд кнопок
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Ввод трат");
        keyboardFirstRow.add("Просмотр трат.");
        // Второй ряд кнопок
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("Статистика.");
        // Добавляем ряды кнопок на клавиатуру
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // Устанавливаем клавиатуру
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    //  Клавиатура меню ввода трат.
    private ReplyKeyboardMarkup keyboardSelectDate() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboad(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Позавчера");
        keyboardFirstRow.add("Вчера");
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("Сегодня");
        keyboardSecondRow.add("Другая дата");
        keyboardRows.add(keyboardFirstRow);
        keyboardRows.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    // Клавиатура выбора категории.
    // Появляется после волшебного слова "Выберите категорию"
    private ReplyKeyboardMarkup keyboardSelectCategory() {
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setOneTimeKeyboad(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        // Получаем массив категорий из БД
        ArrayList<String> categories = getCategoriesList();
        if(categories.isEmpty()) {
            System.out.println("Список категорий пуст по необъяснимой пока причине!");
        }

        // Переменная buttonsInRow содержит количество кнопок в строке
        int buttonsInRow = 4;
        // Высчитываем количество строк и добаляем их в массив. СЧИТАЕТСЯ НЕВЕРНО! ПОПРАВИТЬ!
        int rows = (categories.size() / buttonsInRow) + 1;
        // Использую для проверки, затем УДАЛИТЬ.
        System.out.println("rows: " + rows + " count.categories: " + categories.size() + " buttonsInRow: " + buttonsInRow );

        // Устанавливаем необходимое количество рядов для кнопок
        for(int i = 0; i < rows; i++) {
            keyboardRows.add(new KeyboardRow());
        }
        // Для проверки количества рядов. Потом УДАЛИТЬ
        System.out.println("KeyboardRows: " + keyboardRows.size());

        // Пока пробую вот этим неизящным способом!
        // И он работает!!!
        keyboardRows.get(0).add(categories.get(0));
        keyboardRows.get(0).add(categories.get(1));
        keyboardRows.get(0).add(categories.get(2));
        keyboardRows.get(0).add(categories.get(3));
        keyboardRows.get(1).add(categories.get(4));
        keyboardRows.get(1).add(categories.get(5));
        keyboardRows.get(1).add(categories.get(6));
        keyboardRows.get(1).add(categories.get(7));
        keyboardRows.get(2).add(categories.get(8));
        keyboardRows.get(2).add(categories.get(9));
        keyboardRows.get(2).add(categories.get(10));

        // Вот таким изящным способом мы раскидываем категории по строкам.
        // Что-то только этот изящный способ работать перестал. РАЗОБРАТЬСЯ!
//        for (Iterator<String> iterator = categories.iterator(); iterator.hasNext();) {
//            for (int i = 0; i < rows; i++) {
//                keyboardRows.get(i).add(iterator.next());
//                iterator.remove();
//            }
//        }

        // Присваиваем созданную клавиатуру и возвращаем ее методом.
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }


    // Эта секция отвечает за посыл сообщений.
    private void sendMsg(Message message, String text, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        // Вставляем клавиатуру выбранную в методе selectKeyboard()
        sendMessage.setReplyMarkup(keyboardMarkup);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text, InlineKeyboardMarkup keyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        // Вставляем клавиатуру выбранную в методе selectKeyboard()
        sendMessage.setReplyMarkup(keyboardMarkup);
        //sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Вставляем клавиатуру выбранную в методе selectKeyboard()
        //sendMessage.setReplyMarkup(selectKeyboard());

        sendMessage.setChatId(message.getChatId().toString());
        // Закомментированная ниже строка отвечает на пришедшее сообщение этим же сообщением.
        // Для визуального подтверждения того, что работаешь в нужном меню, например.
        // sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}