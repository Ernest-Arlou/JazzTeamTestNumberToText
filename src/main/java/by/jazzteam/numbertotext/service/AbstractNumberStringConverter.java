package by.jazzteam.numbertotext.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class AbstractNumberStringConverter implements NumberStringConverter{
    private static final String ZERO = "0";

    private static final String PATTERN_NUMBERS_AND_MINUS = "-?[0-9]+";

    public String removeZerosAtBeginning(String string) {
        int strLength = string.length();
        if (strLength > 1) {
            for (int i = 0; i < strLength; i++) {
                if (string.startsWith(ZERO)) {
                    string = string.substring(1);
                } else break;
            }
        }
        return string;
    }

    public List<String> divideByThreeDigits(String originString) {

        List<String> threeDigitPartsList = new ArrayList<>();

        while (originString.length() > 3) {
            threeDigitPartsList.add(originString.substring(originString.length() - 3));
            originString = originString.substring(0, originString.length() - 3);
        }
        if (originString.length() != 0) {
            threeDigitPartsList.add(originString);
        }

        return threeDigitPartsList;
    }

    public void validate(String string) throws Exception {
        if (string.length() == 0)
            throw new Exception("Empty string");

        if (!Pattern.compile(PATTERN_NUMBERS_AND_MINUS).matcher(string).matches())
            throw new Exception("Incorrect format. Only numbers and \"-\" at the beginning are allowed.");
    }

}
