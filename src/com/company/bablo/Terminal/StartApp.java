package com.company.bablo.Terminal;

import com.company.bablo.Terminal.Start;

/**
 * Created by nik on 4/24/17.
 */

public class StartApp {
    private static Start startThread = new Start();     // объект потока работы с консолью
    //private static TelegramBot telegramBot = new TelegramBot();
    //private static ConsoleController consoleController = new ConsoleController();

    public static void main(String[] args) {
        startThread.run();
        //telegramBot.run();
        //consoleController.run();
    }


}
