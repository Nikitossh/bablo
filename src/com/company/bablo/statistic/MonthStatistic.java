package com.company.bablo.statistic;

import com.company.bablo.persistent.DAO;

/**
 * Данный класс содержит все необходимое для вывода месячной статистики в telegramBot
 * TODO: Выводить данные о всех ошибочных записях в месяце. Для этого выборка по БД с поиском NULL в категории, дате или значении.Возможно еще лучше поставить NOT NULL для этих полей в таблице.
 */

public class MonthStatistic {

    public String printMonthStatistic() {
        String result;
        result = DAO.selectionThisMonth().toString();

        return  result;
    }


}
