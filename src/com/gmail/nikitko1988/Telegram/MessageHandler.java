package com.gmail.nikitko1988.Telegram;

import com.gmail.nikitko1988.persistent.DAO;
import com.gmail.nikitko1988.persistent.Queries;
import com.gmail.nikitko1988.persistent.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gmail.nikitko1988.persistent.DAO.*;

/**  */

public class MessageHandler {

    public String getStat() throws SQLException{
        StringBuilder result = new StringBuilder();
        String total = "";
        ResultSet rsTotal = selectionTotalValuesMonth(0);
        if (rsTotal.next()) {
            total = rsTotal.getString(1);
        }
        ResultSet rsCategory = selectionThisMonth();
        while (rsCategory.next()) {
            String category = rsCategory.getString(1);
            String value = rsCategory.getString(2);

            result.append(category).append(" ").append(value).append("\t ").append("\n");
        }
        result.append("Total: \n").append(total);

        return result.toString();
    }

    public String getStatComment() throws SQLException{
        StringBuilder result = new StringBuilder();
        ResultSet rsStatCat = Query.selectData(DAO.createPreparedStatement(Queries.selectMonthByComments()));
        while (rsStatCat.next()) {
            String sum = rsStatCat.getString("SUM(value)");
            String cat = rsStatCat.getString("category");
            String com = rsStatCat.getString("comment");
            result.append(cat).append("\t").append(com).append("\t").append(sum).append("\n");
        }

        return result.toString();
    }

    public String getHelp() {
        return "Доступные команды: \n /stat\t Статистика за этот месяц" +
                "\n /statcom\t Сгрупированная по комментам" +
                "\n /help\t Вызов этой справки" +
                "\n Добавление трат запросом вида:" +
                "\n 200 food shop\t Добавление траты сегодня" +
                "\n y(y) 200 food shop \t вчера(позавчера)" +
                "\n 12.10 199 car fuel \t за 12 октября этого года";
    }

}


