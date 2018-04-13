package com.company.bablo.forThinkingInJava.Chapter5;

public class Dog {
    void bark(int message, String zz) {
        System.out.println("Wooof wooof!");
    }

    void bark(String message, int zz) {
        System.out.println("Yes, I can tell!");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.bark(1 , "asdf");
        dog.bark("", 12);

    }


}
