package com.company.bablo.entity;

import com.company.bablo.persistent.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nik on 6/2/17.
 * Creating
 * При создании экземпляра класса, создается список Map, конструктор которого опрашивает БД
 * и на основе ответа заполняет список.
 */

public class Categories {
    private Map<Integer, String> map;

    public Categories(Map<Integer, String> map) throws SQLException{
        getCategoriesMap(DAO.getCategoriesRS(), map);
        this.map = map;
    }

    /** Get categories Map */
    public Map<Integer, String> getCategoriesMap(ResultSet rs, Map<Integer, String> map) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            map.put(rs.getInt(1), rs.getString(2));
        }
        return map;
    }

    /** Get categories List */
    public static ArrayList<String> getListCategories(ResultSet resultSet) {
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

    /** Get categories count */
    public static int getCountCategories(ResultSet resultSet) {
        int count = 0;

        try {
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
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
        HashMap<Integer, String> amp = new HashMap<>();

        try {
            Categories categories = new Categories(amp);
            System.out.println(categories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
