package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex10 {
    public static void main(String[] args) {
        Ex10 ex10 = new Ex10();
        ex10.lala();
        ex10.finalize();
        Ex10 ex11 = new Ex10();
        Ex10 ex12 = new Ex10();
        Ex10 ex13 = new Ex10();
        System.gc();
    }

    @Override
    protected void finalize() {
        System.out.println("I was finalized");
    }

    void lala() {
        System.out.println("lalala");;
    }
}
