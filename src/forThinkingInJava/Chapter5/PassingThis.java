package com.company.bablo.forThinkingInJava.Chapter5;

public class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple());
    }
}
