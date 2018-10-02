//package com.company.bablo.Terminal;

//import com.company.bablo.entity.Categories;

import com.company.bablo.Terminal.NewCostMenu;

import java.util.Scanner;

/** *
 * Это новый основной класс запуска программы bablo в консоли.
 * Created by nik 02.10.2018
 */


public class Start {
//    Categories categories = new Categories();
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

    public static void main(String[] args) {
        Start start = new Start();
        start.printMainMenu();
        while (true) {
            start.doSmth();
            clearConsole();
        }
        /** Ожидаем нажатия нужной кнопки, остальные игнорируем*/
    }

    public static void clearConsole() {
        System.out.print("\033\143");
    }

    public void doSmth() {
//        Scanner scanner = new Scanner(System.in);

        while (true) {
            String str = scanner.next();

            if("n".equals(str) || "N".equals(str)) {
                ncm.printNewCost();
                ncm.work();
                break;
            }
            if("i".equals(str)) {
                // Идем в метод создания костов
                System.out.println("in this month");
            }
            if("l".equals(str)) {
                // Идем в метод создания костов
                System.out.println("last 10");
            }
            if("d".equals(str)) {
                // Идем в метод создания костов
                System.out.println("details");
            }
            if("p".equals(str)) {
                // Идем в метод создания костов
                System.out.println("Preferences");
            }
            if("qq".equals(str)) {
                // Идем в метод создания костов
                System.out.println("Quitting...");
                System.exit(0);
            }


        }
    }

}
