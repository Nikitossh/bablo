package com.company.bablo.Terminal;

import com.company.bablo.regexp.Shablonator;
import com.company.bablo.util.Inputs;

/**
 * Класс для работы в консоли.
 * */

public class NewCostMenu {
     Shablonator shablonator = new Shablonator();

    public void printNewCost() {
        System.out.println("Введите трату в одном из следующих форматов: ");
        System.out.println("100 food shop");
//        System.out.println("yy 2020 car fuel");
//        System.out.println("23.08.2018 2002 food shop");
        System.out.println();
//        System.out.println("Ну и вообще в идеале сделать рутинную по обработке комментария");
//        System.out.println("И если совпадает с шаблоном, то сразу же подставляет нужную категорию");
//        System.out.println("В теории это ускорит работу с прогой, но не думаю, что это очень важно");
    }

    public void work() {
        String userData = Inputs.inputString();
        shablonator.doMatch(userData);
    }
}
