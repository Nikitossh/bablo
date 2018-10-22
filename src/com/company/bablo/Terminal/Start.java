package com.company.bablo.Terminal;

import com.company.bablo.ConsoleView;
import com.company.bablo.persistent.DAO;
import java.util.Scanner;

/** *
 * Это новый основной класс запуска программы bablo в консоли.
 * Created by nik 02.10.2018
 */


public class Start implements Runnable{
    NewCostMenu ncm = new NewCostMenu();
    Scanner scanner = new Scanner(System.in);

    /**
     * Выводит основное меню на экран.
     * */
    public void printMainMenu() {
        clearConsole();
        System.out.println("    Main Menu");
        System.out.println();
        System.out.println("* New cost");
        System.out.println("* In this month");
        System.out.println("* Last 10");
        System.out.println("* Details");
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
            start.doSmth();
            printWaitAnyKey();
            //clearConsole();
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
    public void doSmth() {
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
            if("d".equals(str)) {
                System.out.println("details");
                break;
            }

            // Меню с настройками программы. Неактивно до внедрения работы с файлами
            if("p".equals(str)) {
                clearConsole();
                System.out.println("Пункт с настройками программы еще не работает! Выберите другой пункт меню");
                break;
            }

            // Exit program with status 0
            if("qq".equals(str)) {
                System.out.println("Bye bye!");
                System.exit(0);
            }


        }
    }

    @Override
    public void run() {
        startApp();
    }
}
