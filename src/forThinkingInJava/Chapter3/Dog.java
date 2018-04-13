package com.company.bablo.forThinkingInJava.Chapter3;

public class Dog {
    String name;
    String says;

    public static void main(String[] args) {
        Dog spot = new Dog();
        spot.name = "Spot";
        spot.says = "Roof!";

        Dog scruffy = new Dog();
        scruffy.name = "Scruffy";
        scruffy.says = "Wooof";

        System.out.println(spot.name + " " + spot.says);
        System.out.println(scruffy.name + " " + scruffy.says);

        Dog dog = spot;
        System.out.println(dog==spot);
        System.out.println(dog.equals(spot));
        System.out.println(dog==scruffy);
        System.out.println(dog.equals(scruffy));
        System.out.println(scruffy==spot);
        System.out.println(scruffy.equals(spot));

    }
}
