package com.company.bablo;

import java.io.IOException;

/**
 * Created by nik on 4/18/17.
 * This class was written for work in Unix console
 */
public class MainMenu {

    public static void clearUnixConsole() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
