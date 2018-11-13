package com.company.bablo.entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

import static com.company.bablo.entity.Date.selectDate;
import static com.company.bablo.persistent.DAO.insertionCost;
import static com.company.bablo.persistent.DAO.insertionData;

/** Класс, описывающий основную сущность программы - единичную трату(cost)
 * будет мапиться из таблицы costs базы bablo
 todo: что сломается когда я буду вынужден добавить поле id,
 todo: Убрать из БД таблицу date, добавить одноименную колонку в costs. Пофиг на дублирование дат... или нет?
 */
public class Cost {
    private int value;
    private String category;
    private String comment;
    private LocalDate date;

    /** This constructor for creating with regexp  */
    public Cost(String fields[]) {
        this.value = Integer.parseInt(fields[1]);
        this.category = fields[2];
        this.comment = fields[3];
        this.date = selectDate(fields[0]);
    }

    public Cost(){};

    public static void addCost(Cost cost) {
        try {
            // если проверка прошла,
            if (check(cost))
            // то добавляем дату,
            insertionData(cost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // если в ответ на добавление не 0, то добалено успешно
        if (insertionCost(cost) != 0)
            System.out.println("Cost добавлен в БД.");
    }

    public static boolean check(Cost cost) throws SQLException {
        if (!(cost.getValue() >= 0))
            return false;
        if (!Categories.getCategoriesSet().contains(cost.getCategory()))
            return false;
        if (cost.getComment().isEmpty())
            return false;
        return true;
    }

    /** Check fields if some values are NULL or equals to empty array */
    public static boolean checkFields(String[] args) {
        String[] emptyArray = {"", "", ""};
        if(args[0] != null | args[1] != null | args[2] != null) {
            if (Arrays.equals(args, emptyArray)) {
                System.out.println("Одно из полей введено неверно");
                return false;
            }
            return true;
        }
        else
            System.out.println("Не хватает данных для создания объекта!");
        return false;
    }

    @Override
    public String toString() {
        return "date: " + getDate() + "   " +
                "category: " + getCategory() + "   " +
                "value: " + getValue() + "   " +
                "comment: " + getComment();
    }

    public  int getValue() {
        return value;
    }

    public  String getCategory() {
        return category;
    }

    public  String getComment() {
        return comment;
    }

    public   LocalDate getDate() {
        return date;
    }
}
