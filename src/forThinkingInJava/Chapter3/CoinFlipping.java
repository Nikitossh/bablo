package com.company.bablo.forThinkingInJava.Chapter3;

import java.util.Random;

public class CoinFlipping {
    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int result = random.nextInt(2);
            if (result < 1) {
                System.out.println("Решка");
            }
            else {
                System.out.println("Орел");
            }
        }
    }
}
