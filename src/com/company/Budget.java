package com.company;

import java.time.LocalDate;

import static com.company.ConsoleView.printListCategories;
import static com.company.Cost.getCategoriesList;
import static com.company.Cost.inputDate;
import static com.company.Inputs.inputString;

/**
 * Created by nik on 5/18/17.
 */
public class Budget {
    private LocalDate date;
    private int amount;
    private String comment;
    private String category;

//    public static void main(String[] args) {
//        printListCategories(getCategoriesList());
//        System.out.println("Все верно? Сохраняем в БД? Y/n");
//        if (agree()) {
//            System.out.print("Is true!");
//        }
//        else System.out.print("Its false");
//    }
//
//    public static void sCreateCategoryBudget() {
//        new Budget().createCategoryBudget();
//    }
//
//    public Budget createCategoryBudget() {
//        Budget budget = new Budget();
//        budget.selectDate();
//        budget.selectAmount();
//        budget.selectCategory();
//        budget.selectComment();
//        budget.printPreInsert();
//        return budget;
//    }
//
//    public  void selectDate() {
//        System.out.println("Please, choose the year and month as yyyy-MM-dd");
//        Cost.setDate(inputDate());
//    }
//
//    public void selectCategory() {
//        System.out.println("Выберите категорию из списка:");
//        printListCategories(getCategoriesList());
//        setCategory(inputString());
//    }
//
//    public void selectAmount() {
//        System.out.println("Введите сумму:");
//        setAmount(Inputs.inputInt());
//    }
//
//    public void selectComment() {
//        System.out.println("Введите комментарий:");
//        setComment(Inputs.inputString());
//    }
//
//    public void printPreInsert() {
//        System.out.print(getCategory() + "  " + getAmount() + "  " + getComment());
//    }

    public static boolean agree() {
        System.out.println("Все верно? Y/n");
        String answer = inputString();
        if ((answer.equals("Y")) || (answer.equals("y"))) {
            return true;
        }
        else {
            return false;
        }
    }







    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
