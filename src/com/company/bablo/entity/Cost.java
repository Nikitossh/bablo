package com.company.bablo;

import com.company.bablo.util.Inputs;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by nik on 4/12/17.
 * Modified 13/04/18
 */

public class Cost {
    private int value;
    private String category;
    private String comment;
    private LocalDate date;

    /** This constructor for creating with regexp/RegularExpression  */
    public Cost(String fields[]) {
        this.value = Integer.parseInt(fields[0]);
        this.category = fields[1];
        this.comment = fields[2];
        this.date = LocalDate.now();
    }

    public Cost(int value, String category, String comment, LocalDate date) {
        this.value = value;
        this.category = category;
        this.comment = comment;
        this.date = date;
    }

    public static LocalDate selectDate(int selector) {
        LocalDate today = LocalDate.now();
        LocalDate result;

        //System.out.println("Выберите дату:");
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

    // Получение списка всех категорий из БД.
    public static ArrayList<String> getCategoriesList() {
        ArrayList<String> resultList;
        ResultSet set = DAO.selectionCategoryList();
        resultList = ConsoleView.getListCategories(set);
        return resultList;
    }

    // Проверяет нет ли пустых полей в переданных аргументах.
    static boolean checkCost(String[] args) {
        if(args[0] != null | args[1] != null | args[2] != null) {
            System.out.println("Ну по крайней мере поля не пустые");
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
