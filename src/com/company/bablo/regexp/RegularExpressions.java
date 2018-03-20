package com.company.bablo.regexp;
import java.util.Map;
import java.util.regex.*;

/**
 * This class for working with regular expressions.
 * If phrase, received from user in babloBot matches with one of expression, it will be processing by method's logic
 *
 * Обрабатываются сообщения вида `100 food shop`
 * @author nik
 * @date 07.02.2018
 */

public class RegularExpressions {
    // Пользуюсь данной переменной временно, надо будет делать строку выдергивая список категорий из БД и преобразуя
    //TODO: Сделать в классе DAO.Categories преобразование всех категорий из БД в строку для регулярного выражения.
    // в строку вида category1|category2|... для регулярки
    private String categories = "(alcohol|car|communication|face|family|flat|food|health|other|sport|transport)";
    private final String patternString = "^([0-9]{1,9})(\\s+)" + categories + "(((\\s+)?(\\w+)?){1,30})?$";
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
}
