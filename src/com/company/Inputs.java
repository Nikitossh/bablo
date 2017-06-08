package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nik on 4/13/17.
 */
public class Inputs {

    public static int inputInt() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;

        while(true) {
            try {
                result = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("В введенном числе ошибка, введите его заново");
            }
            break;
        }

        return result;
    }

    public static String inputString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String result = "";

        while(true) {
            try {
                result = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ввод строки произошел с ошибкой. Повторите ввод корректно");
            }
            break;
        }
        return result;
    }

}
