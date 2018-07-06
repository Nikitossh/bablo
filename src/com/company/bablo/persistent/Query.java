package com.company.bablo.persistent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query {
    public static int insertData(String sqlQuery, PreparedStatement ps) {
        int result = 0;
        ps = DAO.createPreparedStatement(sqlQuery);
        result = DAO.executePreparedUpdate(ps);
        return result;
    }

    public static void selectData(String sqlQuery, PreparedStatement ps) {
        ps = DAO.createPreparedStatement(sqlQuery);
        DAO.executePreparedStatement(ps);
    }
}
