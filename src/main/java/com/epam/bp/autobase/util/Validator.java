package com.epam.bp.autobase.util;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Validator {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Validator.class);
    private static final ResourceBundle VALIDATOR_PROPS = ResourceBundle.getBundle("validator");
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String INCORRECT_VALUE = RB.getString("error.incorrect-input");
    private static final String ERROR_PASSES_NOT_EQUALS = RB.getString("error.passes-not-equals");
    private static final String PASSWORD = "password";
    private static final String PASSWORD2 = "password-repeat";

    public static Boolean isValid(String value, String valueDefinition) {
        //check for parameters at properties
        if (!VALIDATOR_PROPS.containsKey("parameter." + valueDefinition)) return false;
        if (!VALIDATOR_PROPS.containsKey(VALIDATOR_PROPS.getString("parameter." + valueDefinition))) return false;
        //return result of matching 'value' with pattern from properties taken by 'valueDefinition'
        return Pattern.compile(VALIDATOR_PROPS.getString(VALIDATOR_PROPS.getString("parameter." + valueDefinition))).matcher(value).matches();
    }

    //method for automated validation of HttpServletRequest parameters
    public static String validateRequestParametersMap(HttpServletRequest request) {
        Map<String, String[]> parametersMap = request.getParameterMap();
        StringBuilder result = new StringBuilder();
        for (String key : parametersMap.keySet()) {
            if (!VALIDATOR_PROPS.containsKey("parameter." + key) | "".equals(parametersMap.get(key)[0])) continue;
            if (!isValid(parametersMap.get(key)[0], key)) {
                if (result.length() != 0) result.append(", ");
                else result.append(INCORRECT_VALUE);
                result.append(RB.getString("default." + key));
                LOGGER.info("Incorrect input: "+RB.getString("default." + key) + ": " + parametersMap.get(key)[0]);
            }
        }
        if ((parametersMap.containsKey(PASSWORD)) & (parametersMap.containsKey(PASSWORD2))) {
            String password1 = parametersMap.get(PASSWORD)[0];
            String password2 = parametersMap.get(PASSWORD2)[0];
            if (!password1.equals(password2)) {
                if (result.length() != 0) result.append(", ");
                else result.append(INCORRECT_VALUE);
                result.append(ERROR_PASSES_NOT_EQUALS);
            }
        }
        return result.toString().trim();
    }
}
