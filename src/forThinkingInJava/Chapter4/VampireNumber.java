package com.company.bablo.forThinkingInJava.Chapter4;

import java.util.ArrayList;

/**
 * Поиск чисел вампиров.
 * @version 0.1 2017-12-14
 * @author Nikita Shesterikov
 */

public class VampireNumber {
    public static void main(String[] args) {
        for (int vampireNumber = 1000; vampireNumber < 10000; vampireNumber++) {
            // Преобразовываю в строку для дальнейших манипуляций.
            String vampireNumberString = "" + vampireNumber;

            // Разбиваю посимвольно.
            String first = vampireNumberString.substring(0,1);
            String second = vampireNumberString.substring(1,2);
            String third = vampireNumberString.substring(2,3);
            String fourth = vampireNumberString.substring(3,4);

            // Разбиваю по всем возможным парам и преобразаю обратно в int
            int fs = Integer.parseInt(first+second);
            int ft = Integer.parseInt(first+third);
            int ffo = Integer.parseInt(first+fourth);
            int st = Integer.parseInt(second+third);
            int sf = Integer.parseInt(second+first);
            int sfo = Integer.parseInt(second+fourth);
            int tf = Integer.parseInt(third+first);
            int ts = Integer.parseInt(third+second);
            int tfo = Integer.parseInt(third+fourth);
            int fof = Integer.parseInt(fourth+first);
            int fos = Integer.parseInt(fourth+second);
            int fot = Integer.parseInt(fourth+third);

            // Проверяю число на "вампирность" и вывожу на печать.
            if(vampireNumber==(fs*tfo)) {
                System.out.println(vampireNumber + "   " + fs + " " + tfo);
            } else if (vampireNumber==(fs*fot)) {
                System.out.println(vampireNumber + "   " + fs + " " + fot);
            } else if (vampireNumber == (ft*sfo)) {
                System.out.println(vampireNumber + "   " + ft + " " + sfo);
            } else if (vampireNumber == (ft*fos)) {
                System.out.println(vampireNumber + "   " + ft + " " + fos);
            } else if(vampireNumber == (ffo*st)) {
                System.out.println(vampireNumber + "   " + ffo + " " + st);
            } else if (vampireNumber == (ffo*ts)) {
                System.out.println(vampireNumber + "   " + ffo + " " + ts);
            } else if (vampireNumber == (st*fof)) {
                System.out.println(vampireNumber + "   " + st + " " + fof);
            } else  if(vampireNumber == (sfo*tf)) {
                System.out.println(vampireNumber + "   " + sfo + " " + tf);
            } else if (vampireNumber == (sf*tfo)) {
                System.out.println(vampireNumber + "   " + sf + " " + tfo);
            } else if (vampireNumber == (sf*fot)) {
                System.out.println(vampireNumber + "   " + sf + " " + fot);
            } else if (vampireNumber == (tf*fos)) {
                System.out.println(vampireNumber + "   " + tf + " " + fos);
            } else if (vampireNumber == (ts*fof)) {
                System.out.println(vampireNumber + "   " + ts + " " + fof);
            }

        }
    }

}

