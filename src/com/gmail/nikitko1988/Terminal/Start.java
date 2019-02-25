package com.gmail.nikitko1988.Terminal;

import com.gmail.nikitko1988.persistent.DAO;

import java.sql.SQLException;
import java.util.Scanner;

import static com.gmail.nikitko1988.Terminal.PrintMonthCost.printSelectionTotalAndAngWholeYear;
import static com.gmail.nikitko1988.Terminal.PrintMonthCost.printSelectionTotalAndAvgYear;
import static com.gmail.nikitko1988.entity.MonthCost.printYear;

/** *
 * Интерфейс для работы с программой из консоли
 * Created by nik 02.10.2018
 */


public class Start implements Runnable {
    private NewCostMenu ncm = new NewCostMenu();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        startApp();
    }

    // Main Menu
    public void printMainMenu() {
        clearConsole();
        System.out.println("    Main Menu");
        System.out.println();
        System.out.println("* New cost");
        System.out.println("* In this month");
        System.out.println("* Last 10");
        System.out.println("* Year");
        System.out.println("* WholeYear(wy)");
        System.out.println("* Preferences");
        System.out.println("* Quit");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void printWaitAnyKey() {
        System.out.println("Введите любой символ и нажмите 'Ввод' для выхода в основное меню...");
        isWaiting();
    }

    public static void startApp() {
        Start start = new Start();
        /** Ожидаем нажатия нужной кнопки, остальные игнорируем*/
        while (true) {
            start.printMainMenu();
            try {
                start.doSmth();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            printWaitAnyKey();
        }
    }

    /** Ожидание нажатия любой кнопки */
    public static void isWaiting() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        while (true) {
            if (s != null) {
                break;
            }
        }
    }

    /** Очистка консоли в Linux */
    public static void clearConsole() {
        System.out.print("\033\143");
    }

    /** Бесконечный цикл в ожидании ввода одной из ключевых букв */
    public void doSmth() throws SQLException {
        while (true) {
            String str = scanner.next();

            // Добавление cost в отдельном классе
            if("n".equals(str) || "N".equals(str)) {
                clearConsole();
                ncm.printMenu();
                ncm.addTerminalCost();
                break;
            }

            if("i".equals(str) || "I".equals(str)) {
                clearConsole();
                System.out.println("Суммы трат в этом месяце:");
                ConsoleView.printMonth(DAO.selectionThisMonth());
                ConsoleView.printTotal(DAO.selectionTotalValuesMonth(0));
                break;
            }

            if("l".equals(str) || "L".equals(str)) {
                clearConsole();
                System.out.println("Список последних 10 трат:");
                System.out.println();
                ConsoleView.printCosts(DAO.selectionLastCosts(10));
                break;
            }

            // Отдельный класс с детализацией
            // Пока только выборка помесячно за год
            // todo: Год запрашивать у пользователя, а не хардкодом
            if("y".equals(str.toLowerCase())) {
                clearConsole();
                printYear(2018);
                break;
            }

            if("wy".equals(str.toLowerCase())) {
                clearConsole();
                printSelectionTotalAndAvgYear();
                printSelectionTotalAndAngWholeYear();
                break;
            }

            // Меню с настройками программы. Неактивно до внедрения работы с I/O в файлы
            if("p".equals(str)) {
                clearConsole();
                System.out.println("Пункт с настройками программы еще не работает! Выберите другой пункт меню");
                break;
            }

            // Exit program with status 0
            if("qq".equals(str.toLowerCase())) {
                System.out.println("Bye bye!");
                System.exit(0);
            }


        }
    }
}
