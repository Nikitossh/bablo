package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex15 {
    String string;
    String string2;
    {
        string2 = "asdfasdf|||";
        string = "hehe||";
    }

    Ex15() {
        System.out.println(string + string2);
    }

    public static void main(String[] args) {
        Ex15 ex15 = new Ex15();
    }
}
