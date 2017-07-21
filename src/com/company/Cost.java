package com.company;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.company.ConsoleView.getListCategories;
import static com.company.Inputs.inputString;

/**
 * Created by nik on 4/12/17.
 */
public class Cost {
    private static int value;
    private static String category;
    private static String comment;
    private static LocalDate date;


    public static Cost createCost()  {
        Cost cost = new Cost();
        System.out.println(getDate() + " " + getValue() + " " + getCategory() + " " + getComment());
        setDate(date);
        setValue(value);
        setCategory(category);
        setComment(comment);
        return cost;
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
        String date = inputString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate result = LocalDate.parse(date, formatter);

        return result;

    }


    // Получение списка всех категорий из БД.
    public static ArrayList<String> getCategoriesList() {
        ArrayList<String> resultList;
        ResultSet set = DAO.selectionCategoryList();
        resultList = getListCategories(set);
        return resultList;
    }


    static void checkCost() {
        System.out.println("Выполняется проверка валидности введенных данных...");
    }

    public static void printCost(Cost cost) {
        System.out.print(getDate().toString() + "  ");
        System.out.print(getValue() + "  ");
        System.out.print(getCategory() + "  ");
        System.out.print(getComment() + "  ");

    }

    public static void setValue(int value) {
        Cost.value = value;
    }

    public static int getValue() {
        return value;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Cost.category = category;
    }

    public static String getComment() {
        return comment;
    }

    public static void setComment(String comment) {
        Cost.comment = comment;
    }

    public  static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        Cost.date = date;
    }


}
