package com.company;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.company.ConsoleView.getListCategories;
import static com.company.CreateSQL.selectListCategories;

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
