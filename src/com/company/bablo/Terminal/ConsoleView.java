package com.company.bablo.Terminal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

/**
 * Created by nik on 5/3/17.
 * Great modification with using Formatter on 27/4/18
 */
public class ConsoleView {

    // Будет 5 полей: id   date   category  sum   comment
    public static void printCosts(ResultSet rs) throws SQLException{
        Formatter f = new Formatter(System.out);
        f.format("%-6s %-15s %-15s %-15s %-50s", "id", "date", "value", "category", "comment");
        System.out.println();
        f.format("%-6s %-15s %-15s %-15s %-50s", "--", "----", "-----", "--------", "-------");
        System.out.println();
        while (rs.next()) {
                f.format("%-6s %-15s %-15s %-15s %-50s", rs.getInt(1), rs.getString(5), rs.getString(2), rs.getString(3), rs.getString(4));
                System.out.println();
        }
    }

    public static void printMonth(ResultSet rs) throws SQLException {
        Formatter f = new Formatter(System.out);
        f.format("%-20s %-10s", "Category", "Sum");
        System.out.println();
        f.format("%-20s %-10s", "--------", "---");
        System.out.println();
        while (rs.next()) {
                f.format("%-20s %-10s", rs.getString(1), rs.getString(2));
                System.out.println();
        }
    }

    public static void printTotal(ResultSet rs) throws SQLException {
        Formatter f = new Formatter(System.out);
        f.format("%-20s %-10s", "--------", "---");
        System.out.println();
        if (rs.next())
            f.format("%-20s %-10d", "Total", rs.getInt(1));
        System.out.println();
    }

}
