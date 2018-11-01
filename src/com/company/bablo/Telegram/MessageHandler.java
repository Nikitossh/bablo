package com.company.bablo.Telegram;

import com.company.bablo.persistent.DAO;
import com.company.bablo.persistent.Queries;
import com.company.bablo.persistent.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageHandler {
    public String getStat() {
        StringBuilder result = new StringBuilder();
        String total = "";
        try {
            ResultSet rsTotal = DAO.selectionTotalValuesMonth(0);
            if (rsTotal.next()) {
                total = rsTotal.getString(1);
            }
            ResultSet rsCategory = DAO.selectionThisMonth();
            while (rsCategory.next()) {
                String category = rsCategory.getString(1);
                String value = rsCategory.getString(2);

                result.append(category).append(" ").append(value).append("\t ").append("\n");
            }
            result.append("Total: \n").append(total);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String getStatComment() {
        StringBuilder result = new StringBuilder();
        try {
            ResultSet rsStatCat = Query.selectData(DAO.createPreparedStatement(Queries.selectMonthByComments()));
            while (rsStatCat.next()) {
                String sum = rsStatCat.getString(1);
                String cat = rsStatCat.getString(2);
                String com = rsStatCat.getString(3);
                result.append(cat).append("\t").append(com).append("\t").append(sum).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}


