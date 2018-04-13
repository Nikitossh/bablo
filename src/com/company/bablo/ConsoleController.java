package com.company.bablo;

import com.company.bablo.entity.Cost;
import com.company.bablo.persistent.DAO;
import com.company.bablo.util.Inputs;

import java.time.LocalDate;

import static com.company.bablo.ConsoleAux.selectDate;
import static com.company.bablo.ConsoleView.printSelectMonthCostsInCategory;
import static com.company.bablo.entity.Categories.getCategoriesList;
import static com.company.bablo.entity.Cost.*;
import static com.company.bablo.persistent.DAO.selectionLastMonthByCategory;

/**
 * Created by nik on 5/3/17.
 */
public class ConsoleController implements Runnable{

    public void run() {
        ConsoleView.printMainMenu();
        selectMainMenu();
    }

    // work with selected point by user in MainMenu
    public static void selectMainMenu() {
        int selector = Inputs.inputInt();

        assert (selector > 0) : "Вы выбрали значение меньше нуля!";
        switch (selector) {
            case 1:
                // Здесь вся логика работы программы при работе из консоли
                // Для начала запросим у пользовтеля данные для создания платежа.
                ConsoleView.printSelectDate();
                LocalDate date =  selectDate(Inputs.inputInt());
                // Запрашиваем ввод суммы
                ConsoleView.printWriteValue();
                int value = Inputs.inputInt();
                // Запрашиваем категорию.
                ConsoleView.printSelectCategory();
                ConsoleView.printListCategories(getCategoriesList());
                String category = Inputs.inputString();
                // Запрашиваем комментарий
                ConsoleView.printWriteComment();
                String comment = Inputs.inputString();

                System.out.println("Все поля заполнены, проверяем их на валидность...");
                Cost cost =  new Cost(value, category, comment, date);

                System.out.println("Создана сущность:");
                printCost(cost);
                System.out.println("Записываем данные в базу данных");

                if (DAO.insertionData(cost) != 0) System.out.println("Добавлена новая дата");
                else System.out.println("Произошла ошибка с добавлением даты!");

                if (DAO.insertionCost(cost) != 0) ConsoleView.printCostAddedSuccess();
                else ConsoleView.printCostAddedFail();
                ConsoleView.printMainMenu();
                selectMainMenu();
                break;

            case 2:
                ConsoleView.printLastCosts();
                ConsoleView.printSelectLastCost(DAO.selectionLastCosts(5));
                ConsoleView.printMainMenu();
                selectMainMenu();
                break;

            case 3:
                ConsoleView.printLastMonth();
                ConsoleView.printSelectThisMonth(DAO.selectionThisMonth());
                ConsoleView.printSelectCostsSumm(DAO.selectionTotalValuesThisMonth(0));
                ConsoleView.printSelectBudgetSummThisMonth(DAO.selectionTotalBudgetThisMonth());
                // Print total
                ConsoleView.printMainMenu();
                selectMainMenu();
                break;

            case 4:
                System.out.println("Создать бюджет на след месяц.");
                ConsoleView.printMainMenu();
                selectMainMenu();
                break;

            case 5:
                System.out.println("Удаление последней траты");
                System.out.println("Вы действительно хотите удалить эту трату?");
                ConsoleView.printSelectLastCost(DAO.selectionLastCosts(1));
                System.out.println("Введите 'y' для удаления или другой символ для выхода");
                String answer = Inputs.inputString();
                if (answer.equals("y") || answer.equals("Y")) {
                    DAO.deletionCost();
                    System.out.println("Запись удалена!");
                }
                else {
                    System.out.println("Запись НЕ удалена.");
                }
                ConsoleView.printMainMenu();
                selectMainMenu();
                break;

            case 6:
                System.out.println("bye-bye");
                System.exit(0);
                break;

            case 7:
                System.out.println("Вывод всех трат в одной категории за предыдущий месяц.");
                System.out.println("Введите категорию:");
                String selectCategory = Inputs.inputString();
                printSelectMonthCostsInCategory(DAO.selectionMonthCostsInCategory(selectCategory));
                break;

            case 8:
                System.out.println("Вывод всех трат по категориям за предыдущий месяц");
                ConsoleView.printSelectThisMonth(selectionLastMonthByCategory());
                ConsoleView.printSelectCostsSumm(DAO.selectionTotalValuesThisMonth(1));
                ConsoleView.printSelectBudgetSummThisMonth(DAO.selectionTotalBudgetThisMonth());
                selectMainMenu();


            default:
                System.out.println("Вы не выбрали ни одного пункта меню. Выберите.");
                selectMainMenu();
        }

    }


}
