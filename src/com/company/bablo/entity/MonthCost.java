package com.company.bablo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.*;

import static com.company.bablo.persistent.DAO.selectionMonth;

public class MonthCost {

    // В мапе хранится пара категория-сумма, месяц и год для идентификации
    private Map<String, Integer> map;
    private int month;
    private int year;

    // При создании класса указываем месяц и год, метод getMonthCost() заполняет мапу
    public MonthCost(int month, int year) {
        this.month = month;
        this.year = year;
        try {
            this.map = getMonthCost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Заполняем мапу исходя из значения месяца, указанного при создании объекта
    private Map<String, Integer> getMonthCost() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        ResultSet rsCategory = selectionMonth(month);
        while (rsCategory.next()) {
            map.put(rsCategory.getString(1), rsCategory.getInt(2));
        }
        return map;
    }

    public void printTable(ArrayList<MonthCost> list) {
        Formatter f = new Formatter(System.out);
        // Получаю все категории из первой же мапы
        Set<String> set = list.get(0).map.keySet();

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String category = (String) iterator.next();
            // Печатаю название категории
            f.format("%-20s", category);
            // И значения из каждой мапы, название категории - ключ
            for (MonthCost m : list) {
                f.format("%-10s ", m.map.get(category));
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList<MonthCost> months = new ArrayList<>();
        MonthCost year = new MonthCost(0,2018);
        for (int i = 1; i < Month.values().length; i++) {
            months.add(new MonthCost(i, 2018));
        }
        year.printTable(months);

        }
}
