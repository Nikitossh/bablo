package com.company.bablo.regexp;

import com.company.bablo.entity.Categories;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для обработки строк по шаблонам.
 * */


public class Shablonator {
    // fetch all categories from db in "food|car|etc" view
    private Categories c = new Categories();
    private String template;
    private final String DATE = "([0-9]{2}\\.|-[0-9]{2}\\.?|-?[0-9]{4}?)";
    private final String VALUE = "([0-9]{1,9})";
    private final String CATEGORY = "(" + c + ")";
    private final String COMMENT = "(\\w+{1,200})";
    private final String NS = "\\s+";

    public final String all = "^(.{1,5})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";

    /** 100 food shop               цифры       пробел   категория            пробел     комментарий     */
    public final String TODAY = "^([0-9]{1,9})\\s+(" +      c    +          ")\\s+?(\\w+?{1,200})?$";
    /** y 100 car wash                yesterday       */
    public final String YESTERDAY = "^([Yy]{1})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    public final String BEFORE_YESTERDAY = "^([Yy]{2})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    /**                                     day    month   space   AS TODAY         */
    public final String WITH_DATE = "^([0-9]{2}\\.[0-9]{2})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    private Pattern pattern;

    public String[] extractAllData(String userData) {
        String[] result = {"", "", "", ""};
        if (userData.matches(TODAY)) {
            pattern = Pattern.compile(TODAY);
            Matcher matcher = pattern.matcher(userData);
            if (matcher.find()) {
                result[1] = matcher.group(1);
                result[2] = matcher.group(2);
                result[3] = matcher.group(3);
            }
            else return result;
        }
        else {
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


    // только со строкой TODAY
    public String[] extractData(String userData) {
        String[] result = {"", "", ""};
        // Тут меняется
        pattern = Pattern.compile(TODAY);
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
