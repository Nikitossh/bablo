package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex16 {
    public static void main(String[] args) {
        Ex16 ex16 = new Ex16();
        String[] array = new String[] {"First", " Second", "Third"};
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        for (String toPrint : array) {
            System.out.println(toPrint);
        }
    }
}
