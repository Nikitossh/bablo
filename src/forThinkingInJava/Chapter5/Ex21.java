package com.company.bablo.forThinkingInJava.Chapter5;

public class Ex21 {
    Papers papers;

    Ex21(Papers papers) {
        this.papers = papers;
    }

    public static void main(String[] args) {
        Ex21
                black = new Ex21(Papers.BLACK),
                blue = new Ex21(Papers.BLUE),
                yellow = new Ex21(Papers.YELLOW);
        black.describe();
        blue.describe();
        yellow.describe();
    }

    void describe() {
        System.out.print("This paper is ");
        switch (papers) {
            case RED:
                System.out.println("red");
                break;
            case BLUE:
                System.out.println("blue");
                break;
            case PINK:
                System.out.println("pink");
                break;
            case BLACK:
                System.out.println("black");
                break;
            case WHITE:
                System.out.println("white");
                break;
            case YELLOW:
                System.out.println("yellow");
                break;
            default:
                System.out.println("its impossible!");
        }
    }

}
