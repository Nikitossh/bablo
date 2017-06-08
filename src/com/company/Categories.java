package com.company;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.company.ConsoleView.getListCategories;
import static com.company.ConsoleView.getCountCategories;

/**
 * Created by nik on 6/2/17.
 */
public class Categories {
    ResultSet resultSet = null;
    //Map map = new HashMap<>();
    int count = 0;

    public static void  main(String[] args) {

        int count = 0;
        ArrayList categoryArrayList = new ArrayList();
        // Шаг 1. Получаем ResultSet списка категорий и количество категорий в списке
        ResultSet categoryList = DAO.selectionCategoryList();
        ResultSet countCategories = DAO.selectionCountCategories();
        // Шаг 2. Получаем значения из ResultSet
        count = getCountCategories(countCategories);
        categoryArrayList = getListCategories(categoryList);
        // Создаем Map
        System.out.println("Создаем и заполняем map");
        Map<Integer, String> map1 = new HashMap<>();
        for (int i = 0; i < count; i++) {
            map1.put(i, (String) categoryArrayList.get(i));
        }
        System.out.println("Создан!");
        System.out.println("Вводим:");



    }


}
