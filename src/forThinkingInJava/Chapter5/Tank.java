package com.company.bablo.forThinkingInJava.Chapter5;

public class Tank {
    boolean isFilled;

    public static void main(String[] args) {
        Tank t1 = new Tank();
        Tank t2 = new Tank();
        t1.fill();
        t1.fill();
        t1.finalize();
        t2.empty();
        t2.empty();
        t2.finalize();
    }

    @Override
    protected void finalize() {
        if(this.isFilled) {
            System.out.println("Tank is NOT empty. We must empty it before finalize");
            System.out.println("Emptying the tank...");
            System.out.println("Tank was emptied and will be finalize");
            this.cleanUp();
        } else {
            this.cleanUp();
        }
    }

    void fill() {
        if(this.isFilled) {
            System.out.println("This tank has been already filled!");
        } else {
            System.out.println("The tank is filled");
            isFilled = true;
        }
    }

    void empty() {
        if(!this.isFilled) {
            System.out.println("This tank has been already emptied");
        } else {
            System.out.println("The tank is empty!");
            isFilled = false;
        }

    }

    void cleanUp() {
        System.out.println("Clean up the tank!");
    }



}
