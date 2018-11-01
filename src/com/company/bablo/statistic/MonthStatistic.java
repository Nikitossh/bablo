package com.company.bablo.statistic;

import com.company.bablo.persistent.DAO;

public class MonthStatistic {
    /** Статистика за этот месяц для телеграмма */

    public String monthStatistic() {
        String result = DAO.selectionThisMonth().toString();

        return  result;
    }

    public static void main(String[] args) {
        MonthStatistic ms = new MonthStatistic();
        System.out.println(ms.monthStatistic());
    }

}
