package com.gmail.nikitko1988.persistent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query {
    public static ResultSet selectData(PreparedStatement ps) {
        return DAO.executePreparedStatement(ps);
    }
}
