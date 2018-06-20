//package com.company.bablo.persistent;
//
//import com.company.bablo.entity.Cost;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.time.LocalDate;
//
//import static com.company.bablo.persistent.DAO.createPreparedStatement;
//import static com.company.bablo.persistent.DAO.executePreparedStatement;
//
//public class CRUD {
//
//    public static void main(String[] args) {
//        String sql = CreateSQL.insertNewCost(new Cost(100, "food", "test", LocalDate.now()));
//        PreparedStatement ps = createPS(sql);
//    }
//
//    public static PreparedStatement createPS(String sql) {
//        PreparedStatement ps = createPreparedStatement(sql);
//        return ps;
//    }
//
//    public static ResultSet selection(PreparedStatement ps) {
//        ResultSet rs = executePreparedStatement(ps);
//        return rs;
//    }
//}
