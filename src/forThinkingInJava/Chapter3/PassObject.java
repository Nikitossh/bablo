package com.company.bablo.forThinkingInJava.Chapter3;

public class PassObject {
    static void f(Letter y) {
        y.aFloat = 1111.1111f;
    }

    public static void main(String[] args) {
        Letter x = new Letter();
        x.aFloat = 123123.213f;
        System.out.println("1: x.aFloat = " + x.aFloat);
        f(x);
        System.out.println("2: x.aFloat = " + x.aFloat);
    }

}
