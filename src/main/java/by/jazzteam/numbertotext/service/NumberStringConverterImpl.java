package by.jazzteam.numbertotext.service;

import by.jazzteam.numbertotext.bean.ExponentOfTen;
import by.jazzteam.numbertotext.dao.DAOHolder;
import by.jazzteam.numbertotext.service.constant.NumbersRu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.regex.Pattern;


public class NumberStringConverterImpl implements NumberStringConverter {
    private static final char ZERO_CHAR = '0';
    private static final char ONE_CHAR = '1';

    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final String ONE_DECLINATION  = "1_";
    private static final String TWO = "2";
    private static final String TWO_DECLINATION  = "2_";
    private static final String MINUS = "-";
    private static final String SPACE = " ";
    private static final String MULTIPLE_ENDING = "ов";

    private static final String THOUSAND = "тысяч";
    private static final String THOUSAND_SINGLE_ENDING = "а";
    private static final String THOUSAND_MULTIPLE_ENDING = "и";

    private static final String PATTERN_NUMBERS_AND_MINUS = "-?[0-9]+";

    private static final Map<String, String> MAP_NUMBER_0_TO_19 = NumbersRu.getNumbersMap();
    private static final Map<String, String> MAP_DOZENS = NumbersRu.getDozensMap();
    private static final Map<String, String> MAP_HUNDREDS = NumbersRu.getHundredsMap();

    private static final List<ExponentOfTen> exponentOfTenList = DAOHolder.getInstance().getDAO().getExponentsOfTen();

    @Override
    public String convertNumberToString(String originString, boolean longScale) throws Exception {
        validate(originString);

        String convertedStr = "";


        if (originString.startsWith(MINUS)) {
            convertedStr += MAP_NUMBER_0_TO_19.get(MINUS) + SPACE;
            originString = originString.substring(1);
        }

        originString = removeZerosAtBeginning(originString);

        List<String> threeDigitPartsList = divideByThreeDigits(originString);


        for (int i = threeDigitPartsList.size() - 1; 0 <= i; i--) {

            String threeDigitPart = threeDigitPartsList.get(i);
            int numberOfThreeDigitPart = i + 1;

            if (threeDigitPart.length() == 3) {

                convertedStr += convertThreeDigitPart(threeDigitPart, numberOfThreeDigitPart, longScale);

                continue;
            }

            if (threeDigitPart.length() == 2) {

                convertedStr += convertTwoDigitPart(threeDigitPart, numberOfThreeDigitPart, longScale);

                continue;
            }

            if (threeDigitPart.length() == 1) {
                convertedStr += convertOneDigit(threeDigitPart, numberOfThreeDigitPart, longScale);
            }

        }

        return convertedStr.trim();
    }


    private String getExponentOfTenName(String numberOfThreeDigitPart, boolean longScale) {
        if (numberOfThreeDigitPart.equals(ZERO) || numberOfThreeDigitPart.equals(ONE)) {
            return "";
        }
        if (numberOfThreeDigitPart.equals(TWO)) {
            return THOUSAND;
        }

        long expOfTen = (Long.parseLong(numberOfThreeDigitPart) - 1) * 3;

        for (ExponentOfTen exp : exponentOfTenList) {
            if (!longScale) {
                if (exp.getShortScaleExponent() == expOfTen) {
                    return exp.getName();
                }
            } else {
                if (exp.getLongScaleExponent() == expOfTen) {
                    return exp.getName();
                }
            }
        }
        return "";
    }

    private void validate(String string) throws Exception {
        if (string.length() == 0)
            throw new Exception("Empty string");

        if (!Pattern.compile(PATTERN_NUMBERS_AND_MINUS).matcher(string).matches())
            throw new Exception("Incorrect format. Only numbers and \"-\" at the beginning are allowed.");
    }

    private String removeZerosAtBeginning(String string) {
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

    private List<String> divideByThreeDigits(String originString) {

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


    private String convertTwoDigitPart(String threeDigitPart, int numberOfThreeDigitPart, boolean longScale) {
        String convertedStr = "";

        convertedStr += convertTwoDigits(threeDigitPart, numberOfThreeDigitPart, longScale);

        if (!threeDigitPart.startsWith(ONE) && !threeDigitPart.endsWith(ZERO)) {
            convertedStr += convertOneDigit(threeDigitPart.substring(1), numberOfThreeDigitPart, longScale);
        }

        return convertedStr;
    }

    private String convertThreeDigitPart(String threeDigitPart, int numberOfThreeDigitPart, boolean longScale) {
        String convertedStr = "";

        if (!threeDigitPart.startsWith(ZERO)) {
            convertedStr += MAP_HUNDREDS.get(threeDigitPart.substring(0, 1)) + SPACE;

            if (threeDigitPart.charAt(1) == ZERO_CHAR && threeDigitPart.charAt(2) == ZERO_CHAR && numberOfThreeDigitPart > 2) {
                convertedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + MULTIPLE_ENDING + SPACE;
            }

            else if (threeDigitPart.charAt(1) == ZERO_CHAR && threeDigitPart.charAt(2) == ZERO_CHAR && numberOfThreeDigitPart == 2) {
                convertedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + SPACE;
            }
        }

        convertedStr += convertTwoDigits(threeDigitPart.substring(1), numberOfThreeDigitPart, longScale);

        if (threeDigitPart.charAt(2) != ZERO_CHAR && threeDigitPart.charAt(1) != ONE_CHAR) {
            convertedStr += convertOneDigit(threeDigitPart.substring(2), numberOfThreeDigitPart, longScale);
        }
        return convertedStr;
    }


    private String convertTwoDigits(String originStr, int numberOfThreeDigitPart, boolean longScale) {
        String transformedStr = "";

        //(11-19)
        if (originStr.startsWith(ONE) && !originStr.endsWith(ZERO)) {

            transformedStr += MAP_NUMBER_0_TO_19.get(originStr) + SPACE;
            transformedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale);

            if (numberOfThreeDigitPart > 2)
                transformedStr += MULTIPLE_ENDING;

            transformedStr += SPACE;
        }

        else if (!originStr.startsWith(ZERO)) {
            transformedStr += MAP_DOZENS.get(originStr.substring(0, 1)) + SPACE;

            if (originStr.endsWith(ZERO)) {
                transformedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale);

                if (numberOfThreeDigitPart > 2) {
                    transformedStr += MULTIPLE_ENDING;
                }

                transformedStr += SPACE;

            }
        }

        return transformedStr;
    }


    private String convertOneDigit(String originStr, int numberOfThreeDigitPart, boolean longScale) {

        String translatedString = "";

        //выводит "один" со степенью
        if (originStr.equals(ONE) && numberOfThreeDigitPart > 2) {
            translatedString += MAP_NUMBER_0_TO_19.get(ONE) + SPACE;
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + SPACE;
        }

        //выводит в строку "один"
        else if (originStr.equals(ONE) && numberOfThreeDigitPart < 2)
            translatedString += MAP_NUMBER_0_TO_19.get(ONE) + SPACE;

            //выводит в строку склонение 1 и добавляет к слову "тысяч" оаончание "а"
        else if (originStr.equals(ONE) && numberOfThreeDigitPart == 2) {
            translatedString += MAP_NUMBER_0_TO_19.get(ONE_DECLINATION) + SPACE;
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + THOUSAND_SINGLE_ENDING + SPACE;
        }

        //выводит числа от 2 до 4 в строку с окончаниями
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && numberOfThreeDigitPart > 2) {
            translatedString += MAP_NUMBER_0_TO_19.get(originStr) + SPACE;
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + THOUSAND_SINGLE_ENDING + SPACE;
        }

        //выводит числа от 2 до 4 в строку с окончаниями
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && numberOfThreeDigitPart == 2) {
            //т.к. 2 имеет отличное от других чисел склонение, то в строку выводится по условию "две"
            if (originStr.equals(TWO))
                translatedString += MAP_NUMBER_0_TO_19.get(TWO_DECLINATION) + SPACE;
            else
                translatedString += MAP_NUMBER_0_TO_19.get(originStr) + SPACE;
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + THOUSAND_MULTIPLE_ENDING + SPACE;
        }

        //выводит в строку числа, начиная от 2 и до 4 без окончаний
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && numberOfThreeDigitPart < 2)
            translatedString += MAP_NUMBER_0_TO_19.get(originStr) + SPACE;

            //выводит в строку числа, начиная от 5 и выше
        else if (Integer.parseInt(originStr) >= 5) {
            translatedString += MAP_NUMBER_0_TO_19.get(originStr) + SPACE;
            //степеням, таким как миллион и дальше придает окончание "ов"
            if (numberOfThreeDigitPart > 2)
                translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + MULTIPLE_ENDING + SPACE;
            else if (numberOfThreeDigitPart == 2) translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + SPACE;
        } else translatedString += MAP_NUMBER_0_TO_19.get(originStr) + SPACE;

        return translatedString;
    }

}
