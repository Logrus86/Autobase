package com.epam.bp.autobase.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Validator {
    public static final Pattern namesPattern = Pattern.compile("([A-Z]{1}[a-z]{0,19})|([А-Я]{1}[а-я]{0,19})");
    public static final Pattern dobPattern = Pattern.compile("[\\d]{4}\\u002D[\\d]{2}\\u002D[\\d]{2}");
    public static final Pattern usernamePattern = Pattern.compile("[a-zA-Z]{1}[\\w\\u005F]{3,19}");
    public static final Pattern passwordPattern = Pattern.compile("[\\w]{3,20}");
    public static final Pattern emailPattern = Pattern.compile("[\\w\\u002E\\u005F]{0,20}@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]{2,3}");
    public static final Pattern smallNumbersPattern = Pattern.compile("[\\d]{1,4}");
    public static final Pattern numbersPattern = Pattern.compile("[\\d]+");
    public static final Pattern yearPattern = Pattern.compile("[\\d]{4}");
    public static final Map<String, Pattern> patternsMap = new HashMap<>();
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String INCORRECT_VALUE = RB.getString("error.incorrect-input");
    private static final String ERROR_PASSES_NOT_EQUALS = RB.getString("error.passes-not-equals");

    static {
        patternsMap.put("firstname", namesPattern);
        patternsMap.put("lastname", namesPattern);
        patternsMap.put("dob", dobPattern);
        patternsMap.put("username", usernamePattern);
        patternsMap.put("password", passwordPattern);
        patternsMap.put("password-repeat", passwordPattern);
        patternsMap.put("email", emailPattern);
        patternsMap.put("STANDING_PLACES_NUMBER", smallNumbersPattern);
        patternsMap.put("PASSENGER_SEATS_NUMBER_BUS", smallNumbersPattern);
        patternsMap.put("PASSENGER_SEATS_NUMBER_CAR", smallNumbersPattern);
        patternsMap.put("MAX_PAYLOAD", numbersPattern);
        patternsMap.put("RENTPRICE", numbersPattern);
        patternsMap.put("NOTOLDER", yearPattern);
    }

    public static String validateRequestParametersMap(HttpServletRequest request) {
        Map<String, String[]> parametersMap = request.getParameterMap();
        StringBuilder result = new StringBuilder();
        for (String key : parametersMap.keySet()) {
            if (!patternsMap.containsKey(key) | "".equals(parametersMap.get(key)[0])) continue;
            if (!isValid(parametersMap.get(key)[0], key)) {
                if (result.length() != 0) result.append(", ");
                else result.append(INCORRECT_VALUE);
                result.append(RB.getString("default." + key));
            }
        }
        if ((parametersMap.containsKey("password")) & (parametersMap.containsKey("password-repeat")) && !parametersMap.get("password")[0].equals(parametersMap.get("password-repeat")[0])) {
            if (result.length() != 0) result.append(", ");
            else result.append(INCORRECT_VALUE);
            result.append(ERROR_PASSES_NOT_EQUALS);
        }
        return result.toString().trim();
    }

    public static Boolean isValid(String value, String valueDefinition) {
        return patternsMap.containsKey(valueDefinition) && patternsMap.get(valueDefinition).matcher(value).matches();
    }
}
