package com.company.bablo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nik on 6/2/17.
 * При создании экземпляра класса, создается список Map, конструктор которого опрашивает БД
 * и на основе ответа заполняет список.
 */
public class Categories {
    static Map<Integer, String> categories;

    public static void  main(String[] args) {
        int count = 0;
        ArrayList categoryArrayList = new ArrayList();
        // Шаг 1. Получаем ResultSet списка категорий и количество категорий в списке
        ResultSet categoryList = DAO.selectionCategoryList();
        ResultSet countCategories = DAO.selectionCountCategories();
        // Шаг 2. Получаем значения из ResultSet
        count = ConsoleView.getCountCategories(countCategories);
        categoryArrayList = ConsoleView.getListCategories(categoryList);

        // Заполняем Map

        for (int i = 0; i < count; i++) {
            System.out.println(categoryArrayList.get(i));
            categories.put(i, (String) categoryArrayList.get(i));
        }
        System.out.println("Создан!");
        System.out.println("Вводим:");


        for(Map.Entry entry : categories.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "Value: " + entry.getValue());
        }


    }


}
