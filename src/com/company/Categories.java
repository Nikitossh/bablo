package com.company;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.company.ConsoleView.getListCategories;
import static com.company.ConsoleView.getCountCategories;
import static com.company.ConsoleView.printCostAddedFail;

/**
 * Created by nik on 6/2/17.
 * При создании экземпляра класса, создается список Map, конструктор которого опрашивает БД
 * и на основе ответа заполняет список.
 */
public class Categories {
    static Map<Integer, String> categories;


    public static void  main(String[] args) {

        for(Map.Entry entry : categories.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "Value: " + entry.getValue());
        }


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
        for (int i = 0; i < count; i++) {
            System.out.println(categoryArrayList.get(i));
            categories.put(i, (String) categoryArrayList.get(i));
        }
        System.out.println("Создан!");
        System.out.println("Вводим:");



    }


}
