package com.company.bablo.forThinkingInJava.Chapter4;

import java.util.Random;

public class ex2 {
    // Version 1
//    public static void main(String[] args) {
//        Random random = new Random();
//        for (int i = 1; i <= 25; i++ ) {
//            int z = random.nextInt(5);
//            int x = random.nextInt(5);
//            if (z > x) {
//                System.out.println("z > x");
//            } else if (z == x) {
//                System.out.println("z is equally to x!");
//            } else {
//                System.out.println("z < x");
//            }
//        }
//    }

    //Version 2
    public static void main(String[] args) {
        Random random = new Random();
        while (true) {
            int z = random.nextInt(100);
            int x = random.nextInt(100);
            if (z > x) {
                System.out.println("z > x");
            } else if (z == x) {
                System.out.println("z is equally to x!");
            } else {
                System.out.println("z < x");
            }
        }
    }

}
