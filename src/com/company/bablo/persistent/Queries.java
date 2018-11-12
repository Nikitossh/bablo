package com.company.bablo.persistent;

import com.company.bablo.entity.Cost;
// TODO: Символ ' ломает строку запроса. При его наличии необходимо его экранировать.

/**
 * Created by nik on 4/17/17.
 *
 */
public class Queries {

// Cost
    static final String insertNewCost(Cost cost) {
        return "INSERT INTO bablo.costs" +
                "(value, comment, date_id, category_id) " +
                "VALUES (" +
                cost.getValue() + ", " +
                "'" + cost.getComment() + "'" + ", " +
                "(SELECT id FROM date WHERE date=" + "'" + cost.getDate().toString() + "'" + "), " +
                "(SELECT id FROM category WHERE category=" + "'" + cost.getCategory() + "'" + "));";
    }

    static String deleteLastCost() {
        return "DELETE FROM costs ORDER BY id DESC LIMIT 1;";
    }


    static String selectLastCosts(int limit) {
        return "SELECT costs.id, category.category, costs.value, costs.comment, date.date " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "ORDER BY id DESC " +
                "LIMIT " + limit + ";";
    }

    static String selectMonthTotal(int interval) {
        return "SELECT SUM(costs.value) AS value " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "WHERE MONTH(date)=MONTH(DATE_ADD(NOW(), INTERVAL -" + interval + " MONTH))" +
                "AND " +
                "YEAR(date)=YEAR(NOW());";
    }

    static String selectMonthTotal(int month, int year) {
        return "SELECT SUM(costs.value) AS value " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "WHERE MONTH(date)=" + month +
                " AND " +
                "YEAR(date)=" + year + ";";
    }


    // Выборка за месяц по категории, сгруппированная по комментам.
    // Используется как case 7 в ConsoleController
    static String selectMonthOneCategory(String category) {
        return "SELECT category.category, SUM(costs.value), costs.comment AS comment FROM costs " +
                "INNER JOIN category ON costs.category_id = category.id " +
                "INNER JOIN date ON costs.date_id = date.id " +
                "WHERE MONTH (date) = MONTH(DATE_ADD(NOW(), INTERVAL - 1 MONTH)) " +
                "AND YEAR (date) = YEAR(NOW()) " +
                "AND category='" + category + "' " +
                "GROUP BY comment " +
                "ORDER BY value;";
    }

    // Выборка за месяц для всех категорий, сгруппированная по комментам.
    // Используется как case 9 в ConsoleController
    // Используется в messageHandler.getStatComment()
    public static String selectMonthByComments() {
        return "select SUM(value), category, comment " +
                "from costs " +
                "INNER JOIN date ON date_id=date.id " +
                "INNER JOIN category ON category_id=category.id " +
                "where YEAR(date)=YEAR( NOW()) " +
                "AND MONTH(date)=MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH)) " +
                "GROUP BY comment " +
                "ORDER BY category;";
    }

// Дата и время
    static String insertNewDate(Cost cost) {
    String date = "'" + cost.getDate().toString() + "'";

        return "INSERT IGNORE INTO date(date) VALUES (" + date + ");";
    }

    /** todo: Эти два надо объеденить */
    static String selectThisMonth() {
        return "SELECT category.category, SUM(costs.value) AS value,  budget.amount " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN budget " +
                "ON costs.category_id=budget.category_id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "WHERE MONTH(date)=MONTH(NOW()) " +
                "AND " +
                "YEAR(date)=YEAR(NOW()) " +
                "GROUP BY category;";
    }
    static String selectMonth(int month, int year) {
        return "SELECT category.category, SUM(costs.value) AS value,  budget.amount " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN budget " +
                "ON costs.category_id=budget.category_id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "WHERE MONTH(date)=" + month +
                " AND " +
                "YEAR(date)=" + year +
                " GROUP BY category;";
    }

    static final String selectLastMonth() {
        return "SELECT category.category, SUM(costs.value) AS value,  budget.amount" +
                " FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN budget " +
                "ON costs.category_id=budget.category_id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "WHERE MONTH(date)=MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH)) " +
                "AND " +
                "YEAR(date)=YEAR(NOW()) " +
                "GROUP BY category;";
    }
    /** */


// Категории
    static String selectListCategories() {
        return "SELECT id, category FROM category ORDER BY category;";
    }

    static String selectCountCategories() {return "SELECT COUNT(category) FROM category;";}

    static String insertNewCategory(String category) {
        return "INSERT INTO category(category) VALUES ('" + category + "');";
    }

}
