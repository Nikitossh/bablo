package com.company.bablo.entity;

import com.company.bablo.ConsoleView;
import com.company.bablo.persistent.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.*;

import static com.company.bablo.entity.Categories.getCategoriesSet;
import static com.company.bablo.persistent.DAO.getCategoriesRS;
import static com.company.bablo.persistent.DAO.selectionMonth;
import static com.company.bablo.persistent.DAO.selectionTotalValuesMonth;

public class MonthCost {

    // В мапе хранится пара категория-сумма, месяц и год для идентификации
    private Map<String, Integer> map;
    private int month;
    private int year;
    private String name;
    private static Formatter f = new Formatter(System.out);


    // При создании класса указываем месяц и год, метод getMonthCost() заполняет мапу
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
        ResultSet rsCategory = selectionMonth(month, year);
        while (rsCategory.next()) {
            map.put(rsCategory.getString(1), rsCategory.getInt(2));
        }
        return map;
    }

    public static void printMonthes(ArrayList<MonthCost> list) {
        f.format("%-20s", "Categories");
        for (int i = 0; i < list.size(); i++) {
            f.format("%-12s", list.get(i).name);
        }
        System.out.println();
    }

    public static void printSeparators(ArrayList<MonthCost> list) {
        f.format("%-20s", "---------");
        for (int i = 0; i < list.size(); i++) {
            f.format("%-12s", "--------");
        }
        System.out.println();
    }

    public static void printTotal(ArrayList<MonthCost> list) throws SQLException {
        f.format("%-20s", "Total:");
        for (int i = 0; i < list.size(); i++) {
            ResultSet rs = selectionTotalValuesMonth(list.get(i).month, list.get(i).year);
            while (rs.next())
                f.format("%-12s", rs.getString(1));
        }
    }

    public static void printTable(ArrayList<MonthCost> list) throws SQLException {
        // Печатаю шапку таблицы
        printMonthes(list);
        printSeparators(list);

        // Получаю все категории в виде TreeSet
        Set<String> set = getCategoriesSet(getCategoriesRS());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String category = (String) iterator.next();
            // Печатаю категории
            f.format("%-20s", category);
            // И значения из каждой мапы, название категории - ключ
            for (MonthCost m : list) {
                f.format("%-12s", m.map.get(category));
            }

            System.out.println();
        }

        // Печатаю total за месяцы
        printSeparators(list);
        printTotal(list);
    }



    public static ArrayList<MonthCost> getYear(int year) {
        ArrayList<MonthCost> theYear = new ArrayList<>();
        for (int i = 1; i < Month.values().length; i++) {
            theYear.add(new MonthCost(i, year, Month.of(i).toString()));
        }
        return theYear;
    }

    public static void main(String[] args) {
        ArrayList<MonthCost> year = getYear(2018);
        try {
            printTable(year);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
