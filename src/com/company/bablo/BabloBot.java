package com.company.bablo;

/**
 * Created by nik on 4/24/17.
 */

public class BabloBot{
    private static ConsoleController consoleThread = new ConsoleController();     // объект потока работы с консолью

    public static void main(String[] args) {
        consoleThread.run();
    }


}