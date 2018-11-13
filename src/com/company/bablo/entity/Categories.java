package com.company.bablo.entity;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.company.bablo.persistent.DAO.getCategoriesRS;

/**
 * Created by nik on 6/2/17.
 * Утилитарный класс для работы с категориями
 * @toString переписан для удобства работы с regexp
 */

public class Categories {
    private Map<Integer, String> map;

    public Categories() throws SQLException{
        map = getCategoriesMap();
    }

    /** Get categories Map */
    public static Map<Integer, String> getCategoriesMap() throws SQLException {
        ResultSet rs = getCategoriesRS();
        Map<Integer, String> map = new HashMap<>();
        while (rs.next())
            map.put(rs.getInt(1), rs.getString(2));

        return map;
    }

    /** Get categories List
     * Используется в ConsoleController
     * */
    public static List<String> getCategoriesList() throws SQLException {
        ResultSet resultSet = getCategoriesRS();
        ArrayList<String> list = new ArrayList<>();
            while (resultSet.next())
                list.add(resultSet.getString(2));

        return list;
    }

    /** Get categories Set */
    public static Set<String> getCategoriesSet() throws SQLException{
        ResultSet resultSet = getCategoriesRS();
        Set<String> setCategories = new TreeSet<>();
            while (resultSet.next()) {
                setCategories.add(resultSet.getString(2));
            }
        return setCategories;
    }

     /** create String like: "cat1|cat2|catN..."
     * It's useful for RegularExpression using in TelegramBot */
    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            result += entry.getValue() + "|";
        }
        /** remove last "|" */
        if (result.endsWith("|")) {
            result = result.substring(0, result.length()-1);
        }
        return result;
    }
}
