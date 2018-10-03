package com.company.bablo.Terminal;//package com.company.bablo.Terminal;

//import com.company.bablo.entity.Categories;

import com.company.bablo.ConsoleView;
import com.company.bablo.persistent.DAO;

import java.util.Scanner;

/** *
 * Это новый основной класс запуска программы bablo в консоли.
 * Created by nik 02.10.2018
 */


public class Start {
    NewCostMenu ncm = new NewCostMenu();
    Scanner scanner = new Scanner(System.in);

    /**
     * Выводит основное меню на экран.
     * */
    public void printMainMenu() {
        System.out.println("* New cost");
        System.out.println("* In this month");
        System.out.println("* Last 10");
        System.out.println("* Details");
        System.out.println("* Preferences");
        System.out.println("* Quit");
    }

    public static void startApp() {
        Start start = new Start();
        start.printMainMenu();
        while (true) {
            start.doSmth();
            clearConsole();
        }
        /** Ожидаем нажатия нужной кнопки, остальные игнорируем*/
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
                ncm.printMenu();
                ncm.addCost();
                break;
            }

            if("i".equals(str) || "I".equals(str)) {
                System.out.println("Суммы трат в этом месяце:");
                ConsoleView.printMonth(DAO.selectionThisMonth());
                ConsoleView.printTotal(DAO.selectionTotalValuesMonth(0));
                break;
            }

            if("l".equals(str) || "L".equals(str)) {
                System.out.println("Список последних 10 трат:");
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
                System.out.println("Не работает! Выберите другой пункт меню");
                break;
            }

            // Exit program with status 0
            if("qq".equals(str)) {
                System.out.println("Bye bye!");
                System.exit(0);
            }


        }
    }

}
