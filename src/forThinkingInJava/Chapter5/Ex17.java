package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex17 {
    Ex17(String argument) {
        System.out.println(argument);
    }

    public static void main(String[] args) {
        Ex17 bla = new Ex17("siski");
        Ex17 blabla = new Ex17("(.)(,)");
        Ex17[] ex17 = new Ex17[] {bla, blabla};
        printArray(47, 15, 12, 234, 234);
        printArray(ex17);
    }

    static public void printArray(Object... args) {
        for (Object obj :args) {
            System.out.println(obj);
        }
    }
}
