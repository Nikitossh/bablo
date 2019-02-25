package com.gmail.nikitko1988.Terminal;

import com.gmail.nikitko1988.Telegram.TelegramBot;

/**
 * Created by nik on 4/24/17.
 */

public class StartApp {
    private static Start startThread = new Start();     // объект потока работы с консолью
    private static Thread telegramBot = new Thread();
    //private static TelegramBot telegramBot = new TelegramBot();
    //private static ConsoleController consoleController = new ConsoleController();

    public static void main(String[] args) {
       telegramBot.run();
        startThread.run();

    }


}
