package com.company.bablo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.List;

/**
 * Created by nik on 5/3/17.
 * Great modification with using Formatter on 27/4/18
 */
public class ConsoleView {

    // Print MainMenu to stdout
    public static void printMainMenu() {
        System.out.println("Вы в основном меню программы bablo:");
        System.out.println("1. Добавить трату.");
        System.out.println("2. Показать 5 последних трат.");
        System.out.println("3. Показать траты в этом месяце.");
        System.out.println("5. Удалить последнюю трату.");
        System.out.println("6. Выход из программы.");
        System.out.println("7. Вывод всех трат за предыдущий месяц по категории");
        System.out.println("9. Предыдущий месяц по подкатегориям");
    }

    public static void printSelectDate() {
        System.out.println("Выберите дату:");
        System.out.println("1. Позавчера");
        System.out.println("2. Вчера");
        System.out.println("3. Введите дату в формате yyyy-MM-dd");
        System.out.println("Или нажмите `Enter` если СЕГОДНЯ");
    }

    public static void printSelectCategory() {
        System.out.println("Выберите категорию из списка.");
        System.out.println("Для добавления новой категории нажмите \"n\" ");
    }

    public static void printWriteComment() {
        System.out.println("Напишите короткий(до 255 символов) комментарий к трате.");
    }

    public static void printCostAddedSuccess() {
        System.out.println("Трата успешно добавлена в базу данных!");
    }

    public static void printCostAddedFail() {
        System.out.println("ОШИБКА ПРИ ДОБАВЛЕНИИ ЗАПИСИ В БД!");
    }

    public static void printListCategories(List list) {
        assert !list.isEmpty();

        int count = list.size();
        for (int i = 0; i < count; i++) {
            if (i < 10) {
                System.out.println(i + ".  " + list.get(i));
            } else {
                System.out.println(i + ". " + list.get(i));
            }
        }
    }

    public static void printLastCosts() {
        System.out.println("Последние пять трат:");
    }

    public static void printLastMonth() {
        System.out.println("Текущие траты за этот месяц:");
    }


    // Будет 5 полей: id   date   category  sum   comment
    public static void printCosts(ResultSet rs) {
        Formatter f = new Formatter(System.out);
        f.format("%-6s %-15s %-15s %-15s %-50s", "id", "date", "value", "category", "comment");
        System.out.println();
        f.format("%-6s %-15s %-15s %-15s %-50s", "--", "----", "-----", "--------", "-------");
        System.out.println();
        try {
            while (rs.next()) {
                f.format("%-6s %-15s %-15s %-15s %-50s", rs.getInt(1), rs.getString(5), rs.getString(2), rs.getString(3), rs.getString(4));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printInCategory(ResultSet rs) {
        Formatter f = new Formatter(System.out);
        try {
            while (rs.next()) {
                f.format("%-20s %-10d %-50s", rs.getString(1), rs.getInt(2), rs.getString(3));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printByComment(ResultSet rs) {
        Formatter f = new Formatter(System.out);
        f.format("%-20s %-20s %-10s", "Value", "Category", "Comment");
        System.out.println();
        f.format("%-20s %-20s %-10s", "-----", "--------", "-------");
        System.out.println();
        try {
            while (rs.next()) {
                f.format("%-20s %-20s %-10s", rs.getString(1), rs.getString(2), rs.getString(3));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка в распарсе RS");
        }


    }

    public static void printMonth(ResultSet rs) {
        Formatter f = new Formatter(System.out);
        f.format("%-20s %-10s", "Category", "Sum");
        System.out.println();
        f.format("%-20s %-10s", "--------", "---");
        System.out.println();
        try {
            while (rs.next()) {
                f.format("%-20s %-10s", rs.getString(1), rs.getString(2));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при работе метода printMonth() в классе ConsoleView");
        }
    }

    public static void printTotal(ResultSet rs) {
        Formatter f = new Formatter(System.out);
        f.format("%-20s %-10s", "--------", "---");
        System.out.println();
        try {
            if (rs.next())
                f.format("%-20s %-10d", "Total", rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
