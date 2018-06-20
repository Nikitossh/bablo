package com.company.bablo;

import com.company.bablo.persistent.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        System.out.println("4. Создать бюджет на следующий месяц.");
        System.out.println("5. Удалить последнюю трату.");
        System.out.println("6. Выход из программы.");
        System.out.println("7. Вывод всех трат за предыдущий месяц по категории");
    }

    public static void printSelectDate() {
        System.out.println("Вы в меню добавления трат.");
        System.out.println("Выберите дату совершения траты:");
        System.out.println("1. Позавчера");
        System.out.println("2. Вчера");
        System.out.println("3. Введите дату в формате yyyy-MM-dd");
        System.out.println("Или нажмите `Enter` если СЕГОДНЯ");
    }

    public static void printSelectCategory() {
        System.out.println("Выберите категорию из списка.");
        System.out.println("Для добавления новой категории нажмите \"n\" ");
    }

    public static void printWriteValue() {
        System.out.println("Напишите потраченную сумму.");
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

    public static void printMonth(ResultSet rs) {
        Formatter f = new Formatter(System.out);
        f.format("%-20s %-10s %-10s", "Category", "Sum", "Budget");
        System.out.println();
        f.format("%-20s %-10s %-10s", "--------", "---", "------");
        System.out.println();
        try {
            while (rs.next()) {
                f.format("%-20s %-10s %-10s", rs.getString(1), rs.getString(2), rs.getString(3));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при работе метода printMonth() в классе ConsoleView");
        }
    }

    public static void printTotal(ResultSet rs) {
        int budget = getBudget(DAO.selectionTotalBudgetThisMonth());
        Formatter f = new Formatter(System.out);
        f.format("%-20s %-10s %-10s", "--------", "---", "------");
        System.out.println();
        try {
            if (rs.next())
                f.format("%-20s %-10d %-10d", "Total", rs.getInt(1), budget);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    // Получаем строковое представление бюджета
    public static int getBudget(ResultSet rs) {
        try {
            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        ConsoleView.printMonth(DAO.selectionThisMonth());
        ConsoleView.printTotal(DAO.selectionTotalValuesThisMonth(1));
        ConsoleView.printInCategory(DAO.selectionMonthCostsInCategory("car"));
        ConsoleView.printCosts(DAO.selectionLastCosts(5));
    }
}


//    public static void printTab(int number) {
//        for (int i=0; i < number; i++) {
//            System.out.print("\t");
//        }
//    }
//      Закомментированные методы использовались для вывода данных до того как я освоил работу с Formatter
//     public static void printSelectThisMonth(ResultSet rsCategory) {
//        try {
//
//
//            System.out.println("Категория\t\t\tСумма\t\tБюджет" );
//            System.out.println("------------------------------------------");
//
//            while (rsCategory.next()) {
//                String category = rsCategory.getString(1);
//                String value = rsCategory.getString(2);
//                String amount = rsCategory.getString(3);
//
//                 //Print with "\t" as indents independents on category or
//                System.out.print(category);
//                if (category.length() < 4)                      printTab(5);
//                else if (category.equals("transport"))          printTab(3);
//                else if (category.equals("communications"))     printTab(2);
//                else                                            printTab(4);
//                System.out.print(value);
//                if (value.length() < 4)                         printTab(3);
//                else if (value.length() > 5)                    printTab(1);
//                else                                            printTab(2);
//                System.out.print(amount);
//                System.out.println();
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void printSelectLastCost(ResultSet resultSet) {
//        int categoryLength;
//        int valueLength;
//        try {
//            while (resultSet.next()) {
//                String id = resultSet.getString(1);
//                String category = resultSet.getString(2);
//                String value = resultSet.getString(3);
//                String comment = resultSet.getString(4);
//                String date = resultSet.getString(5);
//
//                // Проверка длины слова категории для выравнивания при выводе в консоль
//                if (category.length() < 5) {
//                    categoryLength = 3;
//                } else if (category.length() < 9) {
//                    categoryLength = 3;
//                } else if (category.length() < 14) {
//                    categoryLength = 2;
//                } else categoryLength = 1;
//
//                // Проверка длины значения траты для выравнивания при выводе на консоль
//                if (value.length() <= 3) {
//                    valueLength = 2;
//                } else if (value.length() <= 7) {
//                    valueLength = 1;
//                } else valueLength = 3;
//
//                // Вывод в консоль значений, разделенных табом с помощью метода printTab()
//                System.out.print(id);
//                printTab(1);
//                System.out.print(date);
//                printTab(1);
//                System.out.print(category);
//                // Варьируется после категории, взависимости от длины слова
//                printTab(categoryLength);
//                System.out.print(value);
//                // Варьируется от длины суммы
//                printTab(valueLength);
//                System.out.print(comment);
//                System.out.println();
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void printSelectCostsSumm(ResultSet resultSet) {
//        System.out.println("------------------------------------------");
//        try {
//            if (resultSet.next()) {
//                System.out.print("Итого:");
//                printTab(4);
//                System.out.print(resultSet.getString(1));
//                if (resultSet.getString(1).length() < 4)               printTab(3);
//                else                                                      printTab(2);
//            }
//        } catch (SQLException e) {
//            System.out.println("ОШИБКА ПРИ ПАРСИНГЕ РЕЗАЛТСЕТА!!!!!!!!!!");
//            e.printStackTrace();
//        }
//    }
//
//    public static void printSelectBudgetSummThisMonth(ResultSet resultSet) {
//        try {
//            if (resultSet.next()) {
//                System.out.print(resultSet.getInt(1));
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            System.out.print("Ошибка при получении ResultSet из selectionTotalAmountThisMonth");
//            e.printStackTrace();
//        }
//    }

//public static void printSelectMonthCostsInCategory(ResultSet resultSet) {
//        try {
//            while (resultSet.next()) {
//                System.out.print(resultSet.getString(1));
//                printTab(2);
//                System.out.print(resultSet.getString(2));
//                printTab(2);
//                System.out.print(resultSet.getString(3));
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }