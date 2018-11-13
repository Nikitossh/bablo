package com.company.bablo.Terminal;

import com.company.bablo.entity.MonthCost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Set;

import static com.company.bablo.entity.Categories.getCategoriesSet;
import static com.company.bablo.persistent.DAO.getCategoriesRS;
import static com.company.bablo.persistent.DAO.selectionTotalValuesMonth;

public class PrintMonthCost {
    static Formatter f = new Formatter(System.out);

    public static void printMonths(ArrayList<MonthCost> list) {
        f.format("%-20s", "Categories");
        for (int i = 0; i < list.size(); i++) {
            f.format("%-12s", list.get(i).getName());
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
            ResultSet rs = selectionTotalValuesMonth(list.get(i).getMonth(), list.get(i).getYear());
            while (rs.next())
                f.format("%-12s", rs.getString(1));
        }
    }

    // Вывод сводной таблицы
    public static void printTable(ArrayList<MonthCost> list) throws SQLException {
        // Печатаю шапку таблицы
        printMonths(list);
        printSeparators(list);
        // Получаю все категории в виде TreeSet
        Set<String> set = getCategoriesSet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String category = (String) iterator.next();
            // Печатаю категории
            f.format("%-20s", category);
            // И значения из каждой мапы, название категории - ключ
            for (MonthCost m : list) {
                f.format("%-12s", m.getMap().get(category));
            }

            System.out.println();
        }

        // Печатаю total за месяцы
        printSeparators(list);
        printTotal(list);
    }

}



