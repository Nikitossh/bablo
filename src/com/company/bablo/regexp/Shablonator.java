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

    String day = "^" + DATE + NS + VALUE + NS + CATEGORY + NS + COMMENT +"$";
    String y = "^" + "Y|y" + NS + VALUE + NS + CATEGORY + NS + COMMENT + "$";
    String yy = "^" + "YY|Yy|yY|yy" + NS + VALUE + NS + CATEGORY + NS + COMMENT + "$";
    String tod = "^" + NS + VALUE + NS + CATEGORY + NS + COMMENT + "$";

    /** 100 food shop               цифры       пробел   категория            пробел     комментарий     */
    public final String TODAY = "^([0-9]{1,9})\\s+(" +      c    +          ")\\s+?(\\w+?{1,200})?$";
    /** y 100 car wash                yesterday       */
    public final String YESTERDAY = "^([Yy]{1})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    private final String BEFORE_YESTERDAY = "^([Yy]{2})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    /**                                     day    month   space   AS TODAY         */
    private final String WITH_DATE = "^([0-9]{2}\\.[0-9]{2})\\s+([0-9]{1,9})\\s+(" + c + ")\\s+?(\\w+?{1,200})?$";
    private Pattern pattern;

    public String isMatch(String message) {
        if (message.matches(WITH_DATE)) {
            return WITH_DATE;
        }
        else if (message.matches(TODAY)) {
            return TODAY;
        }
         else if (message.matches(YESTERDAY)) {
            return YESTERDAY;
        } else if (message.matches(BEFORE_YESTERDAY)) {
             return BEFORE_YESTERDAY;
        } else
            return "";

    }

    public String[] extractData(String userData, String template) {
        String[] result = {"", "", "", ""};
        pattern = Pattern.compile(template);
        Matcher matcher = pattern.matcher(userData);
        if (matcher.find()) {
            // TODAY
            if (matcher.groupCount() == 3)
                result[0] = LocalDate.now().toString();
            // YESTERDAY
            else if (matcher.group(1).toLowerCase().equals("y")) {
                result[0] = LocalDate.now().minusDays(1).toString();
            }
            // DAY_BEFORE_YESTERDAY
            else if (matcher.group(1).toLowerCase().equals("yy")) {
                result[0] = LocalDate.now().minusDays(2).toString();
            }
            // WITH_DATE
            else {
                result[0] = LocalDate.parse(matcher.group(1)).toString();
            }

            // Заполняем остальные группы
            result[1] = matcher.group(2);
            result[2] = matcher.group(3);
            result[3] = matcher.group(4);
        }
        return result;
    }


    // только со строкой TODAY
    public String[] doMatch(String userData) {
        String[] result = {"", "", ""};
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
