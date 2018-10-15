package com.company.bablo.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Date {
    /** Parsing Date from String to LocalDate */
    static LocalDate selectDate(String str) {
        LocalDate result;
        if ("y".equals(str.toLowerCase()))
            return LocalDate.now().minusDays(1);
        else if ("yy".equals(str.toLowerCase()))
            return LocalDate.now().minusDays(2);
        else if (str.isEmpty())
            return LocalDate.now();
        else {
            try {
                str += "." + LocalDate.now().getYear();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                result = LocalDate.parse(str,dtf);
                System.out.println(result);
            } catch (Exception e) {
                return null;
            }
            return result;
        }
    }
}
