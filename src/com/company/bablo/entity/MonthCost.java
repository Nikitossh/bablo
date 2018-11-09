package com.company.bablo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.company.bablo.persistent.DAO.selectionMonth;

/** Этот класс соде */
//todo: Сломается когда будут разные категории в мапах

public class MonthCost {

    // В мапе хранится пара категория-сумма, месяц и год для идентификации
    private Map<String, Integer> map;
    private int month;
    private int year;

    // При создании класса указываем месяц и год, метод getMonthCost() заполняет мапу
    // todo: будет падать если такого месяца еще нет
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
        MonthCost year = new MonthCost(0,2018);
        MonthCost jan = new MonthCost(1, 2018);
        MonthCost feb = new MonthCost(2, 2018);
        MonthCost mar = new MonthCost(3, 2018);
        MonthCost apr = new MonthCost(4, 2018);
        MonthCost may = new MonthCost(5, 2018);
        ArrayList<MonthCost> months = new ArrayList<>();
        months.add(jan);
        months.add(feb);
        months.add(mar);
        months.add(apr);
        months.add(may);
         year.printTable(months);

        }
}
