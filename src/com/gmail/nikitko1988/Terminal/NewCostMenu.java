package com.gmail.nikitko1988.Terminal;

import com.gmail.nikitko1988.entity.Cost;
import com.gmail.nikitko1988.regexp.Shablonator;
import com.gmail.nikitko1988.util.Inputs;

import java.sql.SQLException;

import static com.gmail.nikitko1988.entity.Cost.addCost;

/**
 * Класс для работы в консоли.
 * Меню добавления новых костов.
 * */

public class NewCostMenu {
    // todo: Убрать этот ужас
     Shablonator shablonator;
    {
        try {
            shablonator = new Shablonator();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMenu() {
        System.out.println("Введите трату в одном из следующих форматов: ");
        System.out.println("100 food shop");
        System.out.println();
    }

    public void addTerminalCost() {
        String userData = Inputs.inputString();
        String[] costFields = shablonator.extractAllData(userData);
        // Если кост прошел проверку на валидность
        if (Cost.checkFields(costFields)) {
            // Собираем сущность и добавляем дату
            Cost cost = new Cost(costFields);
            addCost(cost);
    }
}
}
