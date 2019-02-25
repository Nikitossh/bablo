package com.gmail.nikitko1988.regexp;

import com.gmail.nikitko1988.entity.Categories;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для обработки строк по шаблонам.
 * */


public class Shablonator {
    // fetch all categories from db in "food|car|etc" view
    private Categories c = new Categories();
    private Pattern pattern;

    public final String all = "^(.{1,5})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";

    /** 100 food shop               цифры       пробел   категория            пробел     комментарий     */
    public final String TODAY = "^([0-9]{1,9})\\s+(" +      c    +          ")\\s+?(\\w+?{1,200})?$";
    /** y 100 car wash                yesterday       */
    public final String YESTERDAY = "^([Yy]{1})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    public final String BEFORE_YESTERDAY = "^([Yy]{2})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    /**                                     day    month   space   AS TODAY         */
    public final String WITH_DATE = "^([0-9]{2}\\.[0-9]{2})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";

    public Shablonator() throws SQLException {
    }

    // todo: Отрефакторить так чтоб без лапши.
    public String[] extractAllData(String userData) {
        String[] result = {"", "", "", ""};
        // Если cost без указания даты
        if (userData.matches(TODAY)) {
            pattern = Pattern.compile(TODAY);
            Matcher matcher = pattern.matcher(userData);
            if (matcher.find()) {
                result[1] = matcher.group(1);
                result[2] = matcher.group(2);
                result[3] = matcher.group(3);
            }
            else return result;
            // Иначе парсим и дату
        } else {
            pattern = Pattern.compile(all);
            Matcher matcher = pattern.matcher(userData);
            if (matcher.find()) {
                result[0] = matcher.group(1);
                result[1] = matcher.group(2);
                result[2] = matcher.group(3);
                result[3] = matcher.group(4);
            }
        }

        return result;
    }
//
//    // todo: Удалить этот метод, переключиться на метод выше
//    @Deprecated
//    public String[] extractData(String userData) {
//        String[] result = {"", "", ""};
//        pattern = Pattern.compile(TODAY);
//        Matcher matcher = pattern.matcher(userData);
//        if (matcher.find()) {
//            result[0] = matcher.group(1);
//            result[1] = matcher.group(2);
//            result[2] = matcher.group(3);
//        }
//        else {
//            return result;
//        }
//        return result;
//    }
}
