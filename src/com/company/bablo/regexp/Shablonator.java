package com.company.bablo.regexp;

import com.company.bablo.entity.Categories;
import com.company.bablo.entity.Cost;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.bablo.persistent.DAO.insertionCost;

/**
 * Класс для обработки строк по шаблонам.
 * */


public class Shablonator {
    // Необходимо сразу подтянуть все категории для сравнения
    private Categories c = new Categories();

    /** 100 food (shop)             цифры       пробел   категория    опц:    пробел     комментарий     */
    private final String today = "^([0-9]{1,9})(\\s+)" +      c    +          "(((\\s+)?(\\w+)?){1,30})?$";
    private final Pattern pattern = Pattern.compile(today);


    public static void main(String[] args) {
        Shablonator shablonator = new Shablonator();
        String[] costField = shablonator.doMatch("100 food shop");
        // Если кост прошел проверку на валидность
        if (Cost.checkCost(costField))
            // и был добавлен в базу данных
            if (insertionCost(new Cost(costField)) != 0)
                System.out.println("Все прошло заебись!");


    }

    public String[] doMatch(String userData) {
        String[] result = {"", "", ""};
        Matcher matcher = pattern.matcher(userData);
        if (matcher.matches()) {
            System.out.println("Is matched!");
            result[0] = matcher.group(1);
            result[1] = matcher.group(3);
            result[2] = matcher.group(5);
        }
        else {
            System.out.println("No matches found.");
        }

        return result;
    }

}
