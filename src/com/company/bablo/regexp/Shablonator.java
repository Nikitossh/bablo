package com.company.bablo.regexp;

import com.company.bablo.entity.Categories;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для обработки строк по шаблонам.
 * */


public class Shablonator {
    // fetch all categories from db in "category1|category2|category3" view
    private Categories c = new Categories();

    /** 100 food shop               цифры       пробел   категория            пробел     комментарий     */
    private final String today = "^([0-9]{1,9})\\s+(" +      c    +          ")\\s+?(\\w+?{1,30})?$";
    private final Pattern pattern = Pattern.compile(today);

    // Сейчас работает только со строкой today
    public String[] doMatch(String userData) {
        String[] result = {"", "", ""};
        Matcher matcher = pattern.matcher(userData);
        if (matcher.find()) {
            result[0] = matcher.group(1);
            result[1] = matcher.group(2);
            result[2] = matcher.group(3);
        }
        else {
            return result;
        }
        return result;
    }
}
