package com.company.bablo.regexp;

import com.company.bablo.entity.Categories;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class for working with regular expressions.
 * If phrase, received from user in babloBot matches with one of expression, it will be processing by method's logic
 *
 * Обрабатываются сообщения вида `100 food shop`
 * @author nik
 * @date 07.02.2018
 */

public class RegularExpressions {
    private Categories c = new Categories();
    private final String patternString = "^([0-9]{1,9})(\\s+)" + c + "(((\\s+)?(\\w+)?){1,30})?$";
    private final Pattern pattern = Pattern.compile(patternString);

    public boolean isMatch(String messageText) {
        Matcher matcher = pattern.matcher(messageText);
        return matcher.matches();
    }

    public String[] splitMessageText(String messageText) {
        String[] result = {"", "", ""};
        Matcher matcher = pattern.matcher(messageText);
        matcher.matches();
        System.out.println(matcher.group(1));
        result[0] = matcher.group(1);
        System.out.println(matcher.group(3));
        result[1] = matcher.group(3);
        if(matcher.group(4) != null) {
            result[2] = matcher.group(4);
        }
        else {
            result[2] = "no comment";
        }
        System.out.println(matcher.group(5));

        return result;
    }

    public static void main(String[] args) {
        RegularExpressions regularExpressions = new RegularExpressions();
        System.out.println(regularExpressions.c);
    }
}
