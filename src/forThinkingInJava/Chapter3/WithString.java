package com.company.bablo.forThinkingInJava.Chapter3;

public class WithString {
    public static void main(String[] args) {
        String a = "asd";
        String b = "asd";
        WithString withString = new WithString();
        withString.doSmth(a, b);
    }

    public void doSmth(String a, String b) {
//        System.out.println(a * b);
//        System.out.println(a / b);
//        System.out.println(a % b);
//        System.out.println(a - b);
        System.out.println(a + b);
        System.out.println(a==b);
        System.out.println(a!=b);
        System.out.println(a.equals(b));

//        System.out.println(a <<= 1);
//        System.out.println(a >> b);








    }
}
