package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.company.ConsoleView.*;
import static com.company.Costs.*;
import static com.company.DAO.*;
import static com.company.Inputs.inputInt;
import static com.company.Inputs.inputString;

/**
 * Created by nik on 5/3/17.
 */
public class ConsoleController {

    // work with selected point by user in MainMenu
    public static void selectMainMenu() {
        int selector = inputInt();

        Costs cost = new Costs();
        switch (selector) {
            case 1:
                // Здесь вся логика работы программы при работе из консоли
                // Для начала запросим у пользовтеля данные для создания платежа.
                printSelectDate();
                setDate(selectDate(inputInt()));

                // Запрашиваем ввод суммы
                printWriteValue();
                setValue(inputInt());

                // Запрашиваем категорию.
                printSelectCategory();
                printListCategories(getCategoriesList());
                setCategory(inputString());
                // Сделать выбор категорий

                // Запрашиваем комментарий
                printWriteComment();
                setComment(inputString());

                System.out.println("Все поля заполнены, проверяем их на валидность...");
                try {
                    cost =  createCost(); } catch (Exception e) {
                    System.out.print("Ошибка при создании cost!");
                }

                System.out.println("Создана сущность:");
                printCost(cost);
                System.out.println("Записываем данные в базу данных");

                if (insertionData(cost) != 0) System.out.println("Добавлена новая дата");
                else System.out.println("Произошла ошибка с добавлением даты!");

                if (insertionCost(cost) != 0) printCostAddedSuccess();
                else printCostAddedFail();
                printMainMenu();
                selectMainMenu();
                break;

            case 2:
                printLastCosts();
                printSelectLastCost(selectionLastCosts());
                printMainMenu();
                selectMainMenu();
                break;

            case 3:
                printLastMonth();
                printSelectThisMonth(selectionThisMonth());
                printSelectTotalThisMonth(selectionTotalValuesThisMonth());
                printSelectTotalAmountThisMonth(selectionTotalBudgetThisMonth());
                // Print total
                printMainMenu();
                selectMainMenu();
                break;

            case 4:
                System.out.println("Создать бюджет на след месяц.");
                printMainMenu();
                selectMainMenu();
                break;

            case 5:
                System.out.println("Удаление последней траты");
                printMainMenu();
                selectMainMenu();
                break;

            case 6:
                System.out.println("bye-bye");
                System.exit(0);
                break;

            case 7:
                System.out.println("running");
                break;


            default:
                System.out.println("Вы не выбрали ни одного пункта меню. Выберите.");
                selectMainMenu();
        }

    }


}
