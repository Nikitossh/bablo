package com.company.bablo.entity;

import java.time.LocalDate;
import java.util.Arrays;

import static com.company.bablo.entity.Date.selectDate;
import static com.company.bablo.persistent.DAO.insertionCost;
import static com.company.bablo.persistent.DAO.insertionData;

/**
 * Created by nik on 4/12/17.
 * Last modified 15.10.2018
 */

public class Cost {
    private int value;
    private String category;
    private String comment;
    private LocalDate date;

    /** This constructor for creating with regexp  */
    public Cost(String fields[]) {
        this.value = Integer.parseInt(fields[1]);
        this.category = fields[2];
        this.comment = fields[3];
        this.date = selectDate(fields[0]);
    }

    public Cost(){};

    public Cost(int value, String category, String comment, LocalDate date) {
        this.value = value;
        this.category = category;
        this.comment = comment;
        this.date = date;
    }

    // todo: Сделать класс абстрактным, реализовать разные методы добавления из ТелеграммБота и консоли
    public static void addCost(Cost cost) {
        // Сначала всегда добавляем дату
        insertionData(cost);
        if (insertionCost(cost) != 0)
            System.out.println("Cost добавлен в БД.");
    }

    /** Check if some values are NULL or equals "" */
    // todo: Проверять сам cost, а не предварительные поля. Это вынести в другую проверку, перед созданием costa
    public static boolean checkCost(String[] args) {
        String[] emptyArray = {"", "", ""};
        if(args[0] != null | args[1] != null | args[2] != null) {
            if (Arrays.equals(args, emptyArray)) {
                System.out.println("Одно из полей введено неверно");
                return false;
            }
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
