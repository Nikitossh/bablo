package com.company.bablo.forThinkingInJava.Chapter4;

public class Fibonacci {
    public void printFibonacci(int arg) {
        int result = 0;
        int previousResult = 1;
        int prePreviousResult = 0;

        while (result < arg) {
            prePreviousResult = previousResult;
            previousResult = result;
            result = previousResult + prePreviousResult;
            System.out.println(result);
        }

    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.printFibonacci(1000);
    }

}
