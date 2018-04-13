package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex8 {
    void x() {
        System.out.println("calling x");
    }

    void y() {
        x();
        this.x();
        System.out.println("calling y");
    }

    public static void main(String[] args) {
        Ex8 ex8 = new Ex8();
        ex8.y();

    }


}
