package com.company.bablo.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Created by nik on 4/12/17.
 * Modified 13/04/18
 * Last modified 03.10.2018
 */

public class Cost {
    private int value;
    private String category;
    private String comment;
    private LocalDate date;

    /** This constructor for creating with regexp/RegularExpression  */
    public Cost(String fields[]) {
        this.value = Integer.parseInt(fields[1]);
        this.category = fields[2];
        this.comment = fields[3];
        this.date = selectDate(fields[0]);
    }

    public static LocalDate selectDate(String str) {
        LocalDate result = null;
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
    public Cost(){};

    public Cost(int value, String category, String comment, LocalDate date) {
        this.value = value;
        this.category = category;
        this.comment = comment;
        this.date = date;
    }

    /** Check if some values are NULL or equals "" */
    public static boolean checkCost(String[] args) {
        String[] emptyArray = {"", "", ""};
        if(args[0] != null | args[1] != null | args[2] != null) {
            if (Arrays.equals(args, emptyArray)) {
                System.out.println("Одно из полей введено неверно");
                return false;
            }
            System.out.println("Все поля заполнены");
            return true;
        }
        else
            System.out.println("Не хватает данных для полноценного создания объекта!");
        return false;
    }

    @Override
    public String toString() {
        return "date: " + getDate() + "   " +
                "category: " + getCategory() + "   " +
                "value: " + getValue() + "   " +
                "comment: " + getComment();
    }

    public static void printCost(Cost cost) {
        System.out.print(cost.getDate().toString() + "  ");
        System.out.print(cost.getValue() + "  ");
        System.out.print(cost.getCategory() + "  ");
        System.out.print(cost.getComment() + "  ");
    }

    public  void setValue(int value) {
        this.value = value;
    }

    public  int getValue() {
        return value;
    }

    public  String getCategory() {
        return category;
    }

    public  void setCategory(String category) {
        this.category = category;
    }

    public  String getComment() {
        return comment;
    }

    public  void setComment(String comment) {
        this.comment = comment;
    }

    public   LocalDate getDate() {
        return date;
    }

    public  void setDate(LocalDate date) {
        this.date = date;
    }


}
