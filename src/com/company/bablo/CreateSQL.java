package com.company.bablo;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * Created by nik on 4/17/17.
 *
 */
public class CreateSQL {

// Трата
    @NotNull
    static String insertNewCost(Cost cost) {
        return "INSERT INTO bablo.costs" +
                "(value, comment, date_id, category_id) " +
                "VALUES (" +
                cost.getValue() + ", " +
                "'" + cost.getComment() + "'" + ", " +
                "(SELECT id FROM date WHERE date=" + "'" + cost.getDate().toString() + "'" + "), " +
                "(SELECT id FROM category WHERE category=" + "'" + cost.getCategory() + "'" + "));";
    }

    @NotNull
    @Contract(pure = true)
    static String deleteLastCost() {
        return "DELETE FROM costs ORDER BY id DESC LIMIT 1;";
    }


    @NotNull
    @Contract(pure = true)
    static String selectLastCost(int limit) {
        return "SELECT costs.id, category.category, costs.value, costs.comment, date.date " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "ORDER BY id DESC " +
                "LIMIT " + limit + ";";
    }

    @NotNull
    @Contract(pure = true)
    static String selectMonthTotalValueOfCosts(int interval) {
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

    static String selectLastMonthCosts() {
        return "SELECT SUM(costs.value) AS value " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "WHERE MONTH(date)=MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH))" +
                "AND " +
                "YEAR(date)=YEAR(NOW());";
    }



// Дата и время
    @NotNull
    static String insertNewDate(Cost cost) {
    String date = "'" + Cost.getDate().toString() + "'";

        return "INSERT IGNORE INTO date(date) VALUES (" + date + ");";
    }

    @NotNull
    @Contract(pure = true)
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
        // За предыдущий месяц.
//        SELECT category.category, SUM(costs.value) AS value,  budget.amount FROM costs INNER JOIN category
//        ON costs.category_id=category.id
//        INNER JOIN budget ON costs.category_id=budget.category_id
//        INNER JOIN date ON costs.date_id=date.id
//        WHERE MONTH(date)=MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH)) AND YEAR(date)=YEAR(NOW()) GROUP BY category;
    }

    static final String selectLastMonthByCategory() {
        return "SELECT category.category, SUM(costs.value) AS value,  budget.amount FROM costs INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN budget ON costs.category_id=budget.category_id " +
                "INNER JOIN date ON costs.date_id=date.id " +
                "WHERE MONTH(date)=MONTH(DATE_ADD(NOW(), INTERVAL -1 MONTH)) AND YEAR(date)=YEAR(NOW()) GROUP BY category;";
    }


// Категории
    @NotNull
    @Contract(pure = true)
    static String selectListCategories() {
        return "SELECT category FROM category;";
    }

    @NotNull
    @Contract(pure = true)
    static String selectCountCategories() {return "SELECT COUNT(category) FROM category;";}

    @NotNull
    @Contract(pure = true)
    static String insertNewCategory(String category) {
        return "INSERT INTO category(category) VALUES ('" + category + "');";
    }

    // Выборка за месяц по одной категории, просуммированная по одинаковым комментам.
    @NotNull
    @Contract(pure = true)
    static String selectMonthByCategory(String category) {
        return "SELECT category.category, SUM(costs.value), costs.comment AS comment FROM costs " +
        "INNER JOIN category ON costs.category_id = category.id " +
        "INNER JOIN date ON costs.date_id = date.id " +
        "WHERE MONTH (date) = MONTH(DATE_ADD(NOW(), INTERVAL - 1 MONTH)) " +
        "AND YEAR (date) = YEAR(NOW()) " +
        "AND category='" + category + "' " +
        "GROUP BY comment " +
        "ORDER BY value;";
    }


    // Бюджет
    @NotNull
    @Contract(pure = true)
    static final String selectOneMonthBudget() {
        return "SELECT category.category, budget.amount, comment FROM budget NATURAL JOIN category ON category_id";
    }



    @NotNull
    static final String insertBudgetCategory(Budget budget) {
        return "INSERT INTO budget (amount, comment, date_id, category_id)"
                + "VALUES (" + budget.getAmount() +", " + budget.getComment() + ", "
                + "(SELECT id FROM date WHERE date=" + "'"  + budget.getDate()  + "'" + "), "
                + "(SELECT id FROM category WHERE category=" + "'" + budget.getCategory() + "'" + "));";
    }

    @NotNull
    @Contract(pure = true)
    static String selectTotalBudgetThisMonth() {
        // Это сейчас не работает, потому что бюджет не привязывается к месяцу. Он пока один и тот же на все месяцы
//        return "SELECT SUM(amount) " + "" +
//                "FROM budget " +
//                "INNER JOIN date " + "" +
//                "ON budget.date_id=date.id " + "" +
//                "WHERE MONTH(date)=MONTH(NOW()) " + "" +
//                "AND " + "" +
//                "YEAR(date)=YEAR(NOW());";
        // Здесь же выборка уже идет исходя из значений
        return "SELECT SUM(amount) FROM budget";
    }

    @NotNull
    @Contract(pure = true)
    static final String insertOneCategory(int value, String comment, Categories category, LocalDate date) {
        return "INSERT INTO budget(amount, comment, category_id, date_id) " +
                "VALUES(" + value + ", " + comment + ", " +
                "(SELECT id FROM category WHERE category = '" + category + "'), " +
                "(SELECT id FROM date WHERE date = '" + date + "'));";
    }

}
