package com.company.bablo.forThinkingInJava.Chapter4;

/**
* Нахождение простых чисел в пределах limit.
* Написано как задание по учебнику "Thinking in Java. 4 edition"
* 13.12.2017
* @author Шестериков Никита.
*/

public class ex4 {
    public static void main(String[] args) {
        boolean isPrimeNumber;
        int limit = 1000;

        // Начало с двух т.к. простые числа с 2 и начинаются.
        for (int i = 2; i < limit; i++) {
            isPrimeNumber = true;

            //Логика следущая: если остаток от деления числа i на все числа между (i-1) и 2,
            // то число помечается как простое
            for (int k = (i - 1); k >= 2; k--) {
                if ((i % k) == 0) {
                    isPrimeNumber = false;
                }
            }

            // Если число было помечено как простое, то оно выводится в стандартный поток вывода.
            if (isPrimeNumber) {
                System.out.println("Число " + i + " простое");
            }
        }
    }
}
