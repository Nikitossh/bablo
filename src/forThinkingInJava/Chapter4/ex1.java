package com.company.bablo.forThinkingInJava.Chapter4;

public class ex1 {
//    public static void main(String[] args) {
//        for (int i = 1; i <= 100; i++) {
//            System.out.println(i);
//        }
//    }

    //Exercise 7
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i == 99) {
                return;
            }
            System.out.println(i);
        }
    }
}
