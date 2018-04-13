package com.company.bablo.forThinkingInJava.Chapter4;

public class ex6 {
    public static void main(String[] args) {
        System.out.println(test(10, 5, 1, 11));
        System.out.println(test(5, 10, 2, 7));
        System.out.println(test(10, 5, 5, 6));

    }

    static int test(int testval, int target, int begin, int end) {
        if (testval >= begin && testval <= end) {
            System.out.println("testval in " + begin + " ... " + end + " range");
        }
        if(testval > target) {
            return +1;
        }
        else if (testval < target) {
            return -1;
        }
        else
            return 0;
    }
}
