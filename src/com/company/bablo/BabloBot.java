package com.company.bablo;

import com.company.bablo.Terminal.Start;

/**
 * Created by nik on 4/24/17.
 */

public class BabloBot{
    private static ConsoleController consoleThread = new ConsoleController();     // объект потока работы с консолью

    public static void main(String[] args) {
        Start.startApp();
        //consoleThread.run();
    }


}
