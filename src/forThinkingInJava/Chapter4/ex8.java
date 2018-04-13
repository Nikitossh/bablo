package com.company.bablo.forThinkingInJava.Chapter4;

public class ex8 {
    public static void main(String[] args) {
        for (int i=1; i<6; i++) {
        switch (i) {
            case 1: System.out.println("message" + i);
            case 2: System.out.println("message" + i);
            case 3: System.out.println("message" + i);
            case 4: System.out.println("message" + i);
            case 5: System.out.println("message" + i);
            default: System.out.println("default message!");
        }
        }
    }
}
