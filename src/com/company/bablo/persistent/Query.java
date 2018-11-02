package com.company.bablo.persistent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query {
    public static ResultSet selectData(PreparedStatement ps) {
        return DAO.executePreparedStatement(ps);
    }
}
