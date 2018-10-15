package com.company.bablo;

import com.company.bablo.util.Inputs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ConsoleAux {


    public static LocalDate selectDate(int selector) {
        LocalDate today = LocalDate.now();
        LocalDate result;

        switch (selector) {
            case 1 :
                result = today.minusDays(2);
                break;
            case 2 :
                result = today.minusDays(1);
                break;
            case 3 :
                result = inputDate();
                break;
            default :
                result = today;
                break;
        }
        System.out.println("Выбрана дата:");
        System.out.println(result);
        return result;
    }


    static LocalDate inputDate() {
        System.out.println("Формат ввода даты в виде yyyy-MM-dd");
        String date = Inputs.inputString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate result = LocalDate.parse(date, formatter);
        return result;
    }
}
