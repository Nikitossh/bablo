package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex9 {
    Ex9(String x) {
        System.out.println("this is the first constructor");
    }

    Ex9(int z) {
        this("lala");
        System.out.println("this is the second one");
    }

    public static void main(String[] args) {
        Ex9 ex9 = new Ex9(2);
        ex9.a();
        b(ex9);
    }

    void a() {
        System.out.println("I am NON static");
    }

    static void b(Ex9 zz) {
        System.out.println("And I'm static!");
        zz.a();

    }

}
