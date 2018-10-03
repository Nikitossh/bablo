package com.company.bablo.Terminal;

import com.company.bablo.entity.Cost;
import com.company.bablo.regexp.Shablonator;
import com.company.bablo.util.Inputs;

import static com.company.bablo.persistent.DAO.insertionCost;

/**
 * Класс для работы в консоли.
 * Меню добавления новых костов.
 * */

public class NewCostMenu {
     Shablonator shablonator = new Shablonator();

    public void printMenu() {
        System.out.println("Введите трату в одном из следующих форматов: ");
        System.out.println("100 food shop");
        System.out.println();
    }

    public void addCost() {
        String userData = Inputs.inputString();
        String[] costFields = shablonator.doMatch(userData);
        // Если кост прошел проверку на валидность
        if (Cost.checkCost(costFields))
            // и был добавлен в базу данных
            if (insertionCost(new Cost(costFields)) != 0)
                System.out.println("Cost добавлен в БД.");
    }
}
