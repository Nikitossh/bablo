package com.company.bablo.persistent;

/**
 * Created by nik on 2/21/17.
 * Class for connecting to database and work with it
 * todo: вытащить данные для подключения к БД в файл конфигурации
 */


import com.company.bablo.entity.Cost;

import java.sql.*;

public class DAO {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/bablo";
    private static final String USER = "nik";
    private static final String PASS = "SimplePassword";

    // Создаем новый connection и возвращаем его
    private static Connection getDBConnection() {
        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return connection;
    }

    // Создание preparedStatement с помощью полученного в качестве аргумента SQL
    public static PreparedStatement createPreparedStatement(String SQL) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return preparedStatement;
    }

    // Выполняет созданный SELECT в БД и возвращает ResultSet
    protected static ResultSet executePreparedStatement(PreparedStatement preparedStatement) {
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    // Выполняет INSERT, UPDATE или DELETE и возвращает число.
    protected static int executePreparedUpdate(PreparedStatement preparedStatement) {
        int result = 0;

        try {
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }


    // Далее идет блок выполнения запросов полученных из класса Queries

    // Все что связано с датой и временем
    // Добавление даты
    public static int insertionData(Cost cost) {
        return executePreparedUpdate(createPreparedStatement(Queries.insertNewDate(cost)));
    }

    // Все что связано с тратами
    // Добавляем запись.
    public static int insertionCost(Cost cost) {
       return executePreparedUpdate(createPreparedStatement(Queries.insertNewCost(cost)));
    }

    // Удаление последней записи по ID
    public static int deletionCost() {
        return executePreparedUpdate(createPreparedStatement(Queries.deleteLastCost()));
    }

    // Выборка последних пяти трат
    // Для другого количества меняем limit
    public static ResultSet selectionLastCosts(int limit) {
        String sql = Queries.selectLastCosts(limit);
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }

    // Получаем category, values, budget.amount
    public static ResultSet selectionThisMonth() {
        String sql = Queries.selectThisMonth();
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }
    public static ResultSet selectionMonth(int month) {
        String sql = Queries.selectMonth(month);
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }


    public static ResultSet selectionLastMonthByCategory() {
        String sql = Queries.selectLastMonth();
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }


    // Сумма всех трат за текущий месяц
    public static ResultSet selectionTotalValuesMonth(int interval) {
        String sql = Queries.selectMonthTotal(interval);
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }


// Все что связано с категориями
    // Получение списка категорий
    public static ResultSet getCategoriesRS() {
        String sql = Queries.selectListCategories();
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }

    // Получение количества категорий. В ResultSet будет одно число!
    public static ResultSet getCategoriesCountRS() {
        String sql = Queries.selectCountCategories();
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }

    // Получение всех трат за прыдущий месяц в указанной категории
    public static ResultSet selectionMonthCostsInCategory(String category) {
        String sql = Queries.selectMonthOneCategory(category);
        ResultSet rs = executePreparedStatement(createPreparedStatement(sql));
        return rs;
    }
}
