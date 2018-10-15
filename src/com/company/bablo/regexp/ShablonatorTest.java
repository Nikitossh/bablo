package com.company.bablo.regexp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShablonatorTest {
    private Shablonator shablonator = new Shablonator();
    private String[] validData = {"100", "food", "test"};
    private String[] notMatched = {"", "", ""};

    @Test
    void doMatch() {
        assertTrue(Arrays.equals(shablonator.extractData("100 food test"), validData));
        assertTrue(Arrays.equals(shablonator.extractData("10.0 food test"), notMatched));
        assertTrue(Arrays.equals(shablonator.extractData("100 notCategory blabla"), notMatched));
        assertTrue(Arrays.equals(shablonator.extractData("food blabla"), notMatched));
        assertTrue(Arrays.equals(shablonator.extractData("123 food"), notMatched));
    }
}
