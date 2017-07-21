package com.company;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Created by nik on 4/17/17.
 * This class about add new costs to database
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
    static String selectMonthCosts() {
        return "SELECT SUM(costs.value) AS value " +
                "FROM costs " +
                "INNER JOIN category " +
                "ON costs.category_id=category.id " +
                "INNER JOIN date " +
                "ON costs.date_id=date.id " +
                "WHERE MONTH(date)=MONTH(NOW()) " +
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
                // New one
                "INNER JOIN budget " +
                "ON costs.category_id=budget.category_id " +
                //
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
        return "SELECT SUM(amount) " + "" +
                "FROM budget " +
                "INNER JOIN date " + "" +
                "ON budget.date_id=date.id " + "" +
                "WHERE MONTH(date)=MONTH(NOW()) " + "" +
                "AND " + "" +
                "YEAR(date)=YEAR(NOW());";
    }

}
