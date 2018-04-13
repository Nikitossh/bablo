package com.company.bablo.forThinkingInJava.Chapter3;

public class Exercise2 {
    float a = 123.6f;

    public static void main(String[] args) {
        Exercise2 exercise2 = new Exercise2();
        Exercise2 exercise21 = new Exercise2();
        exercise21.a=3122.3f;
        System.out.println(exercise2);
        System.out.println(exercise21);
        System.out.println(exercise2.a);
        System.out.println(exercise21.a);
        exercise2 = exercise21;
        System.out.println(exercise2.a);
        System.out.println(exercise21.a);
        System.out.println(exercise2);
        System.out.println(exercise21);


    }
}
