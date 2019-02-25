package com.gmail.nikitko1988.entity;


import com.gmail.nikitko1988.Terminal.PrintMonthCost;
import com.gmail.nikitko1988.persistent.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.*;

public class MonthCost {
    // В мапе хранится пара категория-сумма, месяц и год для идентификации
    private Map<String, Integer> map;
    private int month;
    private int year;
    private String name;

    // При создании класса указываем месяц и год, метод getMonthCost() заполняет мапу
    // todo: посоветоваться с кем-то знающим по поводу инициализации в конструкторе. Какая-то хрень.
    public MonthCost(int month, int year, String name) {
        this.name = name;
        this.month = month;
        this.year = year;
        try {
            this.map = getMonthCost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Заполняем мапу исходя из значения месяца и года, указанного при создании объекта
    private Map<String, Integer> getMonthCost() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        ResultSet rsCategory = DAO.selectionMonth(month, year);
        while (rsCategory.next()) {
            map.put(rsCategory.getString(1), rsCategory.getInt(2));
        }
        return map;
    }

    public static ArrayList<MonthCost> getYear(int year) {
        ArrayList<MonthCost> theYear = new ArrayList<>();
        for (int i = 1; i <= Month.values().length; i++) {
            theYear.add(new MonthCost(i, year, Month.of(i).toString()));
        }
        return theYear;
    }

    // Это был main метод для проверки, но сейчас его оставлю
    public static void printYear(int year) {
        ArrayList<MonthCost> y = getYear(year);
        try {
            PrintMonthCost.printTable(y);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
