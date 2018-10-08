package com.company.bablo;

import com.company.bablo.Terminal.Start;

/**
 * Created by nik on 4/24/17.
 */

public class BabloBot{
    private static Start startThread = new Start();     // объект потока работы с консолью
    private static TelegramBot telegramBot = new TelegramBot();
    private static ConsoleController consoleController = new ConsoleController();

    public static void main(String[] args) {
        //startThread.run();
        //telegramBot.runBot();
        consoleController.run();
    }


}
