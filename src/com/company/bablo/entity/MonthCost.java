package com.company.bablo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.company.bablo.persistent.DAO.selectionMonth;
import static com.company.bablo.persistent.DAO.selectionThisMonth;

/** Этот класс соде */

public class MonthCost {
    public Map<String, Integer> getMap() {
        return map;
    }

    private Map<String, Integer> map;
    private int month;
    private int year;

    public MonthCost(int month, int year) {
        this.month = month;
        this.year = year;
        try {
            this.map = getMonthCost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        Set<String> set = list.get(0).map.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String category = (String) iterator.next();
            f.format("%-20s", category);

            for (MonthCost m : list) {
                f.format("%-15s ", m.map.get(category));
            }

            System.out.println();
    }
    }

    public static void main(String[] args) {
        MonthCost year = new MonthCost(0,2018);
        MonthCost jan = new MonthCost(1, 2018);
        MonthCost feb = new MonthCost(2, 2018);
        MonthCost mar = new MonthCost(3, 2018);
        ArrayList<MonthCost> months = new ArrayList<>();
        months.add(jan);
        months.add(feb);
        months.add(mar);
         year.printTable(months);

        }


}
