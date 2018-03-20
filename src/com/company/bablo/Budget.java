package com.company.bablo;

import java.time.LocalDate;

import static com.company.bablo.Inputs.inputString;

/**
 * Created by nik on 5/18/17.
 */
public class Budget {
    private LocalDate date;
    private int amount;
    private String comment;
    private String category;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
