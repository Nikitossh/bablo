package com.company.bablo.entity;

import com.company.bablo.persistent.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nik on 6/2/17.
 * Creating
 * При создании экземпляра класса, создается список Map, конструктор которого опрашивает БД
 * и на основе ответа заполняет список.
 */

public class Categories {
    private Map<Integer, String> map = new HashMap<>();

    public Categories(){
        try {
            getCategoriesMap(DAO.getCategoriesRS(), map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Get categories Map */
    public Map<Integer, String> getCategoriesMap(ResultSet rs, Map<Integer, String> map) throws SQLException {
        while (rs.next()) {
            map.put(rs.getInt(1), rs.getString(2));
        }
        return map;
    }

    /** Get categories List */
    public static List<String> getListCategories(ResultSet resultSet) {
        ArrayList<String> listCategories = new ArrayList<>();
        try {
            while (resultSet.next()) {
                listCategories.add(resultSet.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCategories;
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

    /** Testing class */
    public static void main(String[] args) {
        Categories categories = new Categories();
        System.out.println(categories);
    }

}
