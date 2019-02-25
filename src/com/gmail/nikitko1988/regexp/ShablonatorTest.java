package com.gmail.nikitko1988.regexp;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShablonatorTest {
    private Shablonator shablonator = new Shablonator();
    private String[] validData = {"", "100", "food", "test"};
    private String[] notMatched = {"", "", "", ""};

    ShablonatorTest() throws SQLException {
    }

    @Test
    void doMatch() {
        assertTrue(Arrays.equals(shablonator.extractAllData("100 food test"), validData));
        assertTrue(Arrays.equals(shablonator.extractAllData("10.0 food test"), notMatched));
        assertTrue(Arrays.equals(shablonator.extractAllData("100 notCategory blabla"), notMatched));
        assertTrue(Arrays.equals(shablonator.extractAllData("food blabla"), notMatched));
        assertTrue(Arrays.equals(shablonator.extractAllData("123 food"), notMatched));
        assertTrue(Arrays.equals(shablonator.extractAllData("y 123 food"), notMatched));
    }
}
