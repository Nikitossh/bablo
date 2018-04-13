package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex19 {
    public static void main(String[] args) {
        Ex19 ex19 = new Ex19();
        String[] strings = {"sadfsdf", "sadfasdf", "asdfsdaf", "asdfasdf"};
        ex19.print(strings);
        String s = "asdfsdafs fas, asdfasdf,asdf asd ";
        print(s, s);
    }

    static void print(String... array) {
        for (String ar : array) {
            System.out.println(ar);
        }
    }
}
