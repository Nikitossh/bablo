package com.company;

import java.time.LocalDate;

/**
 * Created by nik on 10.07.17.
 */
public class FoodPaymentTestDrive {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Payment testPayment = new FoodPayment();
        //testPayment.setCategory("food");
        testPayment.setComment("testComment for Food");
        testPayment.setDate(today);
        testPayment.setValue(1234567);
        testPayment.getCategory();
        testPayment.getComment();
        testPayment.getDate();
        testPayment.getValue();

        FoodPayment testPayment2 = new FoodPayment();
        //testPayment2.setCategory("food");
        testPayment2.setComment("testComment2");
        testPayment2.setDate(today);
        testPayment2.setValue(1234567);
        testPayment2.getCategory();
        testPayment2.getValue();
        testPayment2.getComment();
        testPayment2.getDate();

        testPayment.printPayment();
        testPayment2.printPayment();

    }
}
