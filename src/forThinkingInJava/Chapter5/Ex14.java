package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex14 {
    static String first = "I am first";
    static String second;

    static {
        second = "And I am the second";
    }

    static void printString() {
        System.out.println(first);
        System.out.println(second);
    }

    public static void main(String[] args) {
        printString();
//        printString(second);
//        printString(second);
        System.out.println("in main");
        printString();
    }
}
