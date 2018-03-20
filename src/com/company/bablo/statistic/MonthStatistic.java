package com.company.bablo.statistic;

import com.company.bablo.DAO;

/**
 * Данный класс содержит все необходимое для вывода месячной статистики в telegramBot
 */

public class MonthStatistic {

    public String printMonthStatistic() {
        String result;
        result = DAO.selectionThisMonth().toString();

        return  result;
    }


}
