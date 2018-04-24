package com.company.bablo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static com.company.bablo.persistent.DAO.getCategoriesRS;

/**
 * Created by nik on 6/2/17.
 * При создании экземпляра класса, создается список Map, конструктор которого опрашивает БД
 * и на основе ответа заполняет список.
 */
public class Categories {
    private Map<Integer, String> map;

    public Categories() throws SQLException {
        ResultSet rs = getCategoriesRS();
        System.out.println(rs);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            map.put(rs.getInt(1), rs.getString(2));
        }
        System.out.println(map);
    }

    // Вывод в консоль списка категорий.
    //
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

    // Вывод количества категорий
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
        Categories categories = null;
        try {
            categories = new Categories();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(categories);
        }
    }

}
