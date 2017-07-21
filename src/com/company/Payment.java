package com.company;

import sun.util.resources.LocaleData;

import java.time.LocalDate;

/**
 * Created by nik on 10.07.17.
 */
abstract class Payment {
    private int value;
    private String category;
    private String comment;
    private LocalDate date;

    // Print all values of object
    public void printPayment() {
        System.out.print(value + "  " + category + "  " + comment + "  " + date);
        System.out.println();
    }

    /*
    Setters and getters
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
