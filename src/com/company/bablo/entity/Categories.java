package com.company.bablo.entity;

import com.company.bablo.persistent.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nik on 6/2/17.
 * При создании экземпляра класса, создается список Map, конструктор которого опрашивает БД
 * и на основе ответа заполняет список.
 */

public class Categories {
    private Map<Integer, String> map;
    ResultSet rs = DAO.getCategoriesRS();

    public Categories(Map<Integer, String> map) throws SQLException{
        getCategoriesMap(rs);
        this.map = map;
    }


    /** Get categories Map */
    public Map<Integer, String> getCategoriesMap(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            map.put(rs.getInt(1), rs.getString(2));
        }

        return this.map;
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



    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Categories categories = null;
        try {
            categories = new Categories(map);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(categories.map);
        }
    }

}
