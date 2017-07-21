package com.company;

/**
 * Created by nik on 4/24/17.
 */

//import com.sun.deploy.uitoolkit.ui.ConsoleController;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.company.Cost.getCategoriesList;


public class BabloBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ConsoleView.printMainMenu();
        ConsoleController.selectMainMenu();
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BabloBot());
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

    @Override
    public void onUpdateReceived(Update update) {
        /*Message message = update.getMessage();
         if (message != null && message.hasText()) {
            // Информация о боте и доступных командах.
            if (message.getText().equals("/help"))
                sendMsg(message, "Привет, я робот");
            // Начало работы с ботом.
            if (message.getText().equals("/start"))
                sendMsg(message, BotAnswers.startBot(), keyboardMainMenu());

            // Меню ввода трат
            // В этом огромном блоке if ...
            // Будет вся логика работы бота с программой.
            //
            // Зашли в Главное Меню программы.
            // Выбрали "Ввод Трат"
            if (message.getText().equals("Ввод трат")) {
                sendMsg(message, "Выберите дату совершения платежа.", keyboardInsertCosts());
            }
            // Перед нами четыре кнопки, присваивающие значение переменной date
            // Если выбрана четвертая, то дату нужно ввести с клавиатуры.
            if (message.getText().equals("Позавчера")) {
                Cost.setDate(LocalDate.now().minusDays(2));
                setCategory(message);
            }
            if (message.getText().equals("Вчера")) {
                Cost.setDate(LocalDate.now().minusDays(1));
                setCategory(message);
            }
            if (message.getText().equals("Сегодня")) {
                Cost.setDate(LocalDate.now());
                setCategory(message);
            }
            if (message.getText().equals("Другая дата")) {
                // Здесь БылоБКруто вставить выбор даты с клавиатуры в стиле Календаря.
            }

            // Дошли до выбора категорий.
            // Хотелось бы сделать блок выбора динамическим, но как?











            else
                sendMsg(message, "Я не знаю что ответить на это");
        }*/

        // Получаем inlineQuery от update
        InlineQuery inlineQuery = update.getInlineQuery();
        // Получаем строчку с запросом
        if (inlineQuery != null) {
            String query = inlineQuery.getQuery();
            if (query.equals("main")) {
                // Создаем ответ на запрос
                AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
                answerInlineQuery.setInlineQueryId(inlineQuery.getId());
                // Создаем массив для результатов
                List<InlineQueryResult> results = new ArrayList<>();
                // Создаем результат типа Article и заполняем обязательные поля
                InlineQueryResultArticle inlineQueryResultArticle = new InlineQueryResultArticle();
                // id результата приравниваем id запроса
                inlineQueryResultArticle.setId(inlineQuery.getId());
                // То что всплывет у пользователя
                inlineQueryResultArticle.setTitle("Меню трат.");
                // Выбираем клавиатуру под сообщением
                inlineQueryResultArticle.setReplyMarkup(keyboardMain());
                // Создаем сообщение и присваиваем его результату
                InputTextMessageContent inputTextMessageContent = new InputTextMessageContent();
                inputTextMessageContent.setMessageText("Выберите пункт меню:");
                inlineQueryResultArticle.setInputMessageContent(inputTextMessageContent);
                // Добавляем в массив созданный результат.
                results.add(inlineQueryResultArticle);


                // Присваиваем массив для передачи ответом
                answerInlineQuery.setResults(results);
                // Отвечаем на запрос.
                try {
                    answerInlineQuery(answerInlineQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }

        // Получаем колбэк запрос от инлайнКлавиатуры
        CallbackQuery callbackQuery = update.getCallbackQuery();

        if (callbackQuery != null) {
            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
            answerCallbackQuery.setShowAlert(false);
            answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());

            System.out.println(callbackQuery.getData());
            answerCallbackQuery.setText(setAnswerText(callbackQuery.getData()));

            try {
                answerCallbackQuery(answerCallbackQuery);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            //System.out.print(callbackQuery.getData());
        }


        System.out.println(callbackQuery);

    }

    // Присваиваем строку ответа на коллбэк исходя из поля data в нем
    public String setAnswerText(String data) {
        String result;
        if (data.equals("newCost")) {
            result = "Creating a new cost. Please fill in all fields.";

        }

        else if (data.equals("selectLast5")) result = "Last five costs were:";

        else if (data.equals("deleteLast")) result = "Do you want to delete the last cost?";

        else if (data.equals("inactive")) result = "This section is inactive now";

        else result = "Чот подозрительно как вы попали в этот пункт меню!";

        return result ;
    }



    public void setCategory(Message message) {
        sendMsg(message, "Выбор категории.", keyboardSelectCategory());
    }

    private InlineKeyboardMarkup keyboardMain() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> keyboardButtonFirstRow = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsSecondRow = new ArrayList<>();

        List<List<InlineKeyboardButton>> keyboardButtonList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Внести трату");
        inlineKeyboardButton.setCallbackData("newCost");
        inlineKeyboardButton.setSwitchInlineQuery("switch");

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Последние 5");
        inlineKeyboardButton2.setCallbackData("selectLast5");

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Удалить последнюю");
        inlineKeyboardButton3.setCallbackData("deleteLast");

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Inactive");
        inlineKeyboardButton4.setCallbackData("inactive");

        keyboardButtonFirstRow.add(inlineKeyboardButton);
        keyboardButtonFirstRow.add(inlineKeyboardButton2);
        keyboardButtonsSecondRow.add(inlineKeyboardButton3);
        keyboardButtonsSecondRow.add(inlineKeyboardButton4);

        keyboardButtonList.add(keyboardButtonFirstRow);
        keyboardButtonList.add(keyboardButtonsSecondRow);
        inlineKeyboardMarkup.setKeyboard(keyboardButtonList);

        return inlineKeyboardMarkup;
    }


    // Клавиатура Главного меню.
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
        keyboardSecondRow.add("Статистика за месяц.");
        // Добавляем ряды кнопок на клавиатуру
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // Устанавливаем клавиатуру
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    //  Клавиатура меню ввода трат.
    private ReplyKeyboardMarkup keyboardInsertCosts() {
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
    private ReplyKeyboardMarkup keyboardSelectCategory() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboad(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        // Получаем массив категорий из БД
        ArrayList<String> categories = getCategoriesList();
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        // Переменная buttonsInRow содержит количество кнопок в строке
        int buttonsInRow = 3;
        // Высчитываем количество строк и добаляем их в массив.
        int rows = categories.size() / buttonsInRow;
        for(int i = 0; i < rows; i++) {
            keyboardRows.add(new KeyboardRow());
        }
        // Вот таким изящным способом мы раскидываем категории по строкам.
        for (Iterator<String> iterator = categories.iterator(); iterator.hasNext();) {
            for (int i = 0; i < rows; i++) {
                keyboardRows.get(i).add(iterator.next());
                iterator.remove();
            }
        }
        // Присваиваем созданную клавиатуру и возвращаем ее методом.
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }






    //
    //
    // Эта секция отвечает за посыл сообщений.
    //
    //
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