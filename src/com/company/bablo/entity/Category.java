package com.company.bablo.entity;

/**
 * Сейчас не использую, пригодится при переходе на ORM
 * */

public class Category {
    private int id;
    private String name;

    /** It's pretty useful for me to use only name of Category */
    @Override
    public String toString() {
        return name;
    }

    Category() {};


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
