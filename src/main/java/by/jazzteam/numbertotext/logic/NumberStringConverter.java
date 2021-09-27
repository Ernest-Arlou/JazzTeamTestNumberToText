package by.jazzteam.numbertotext.logic;

import by.jazzteam.numbertotext.bean.ExponentOfTen;
import by.jazzteam.numbertotext.dao.DAOHolder;
import by.jazzteam.numbertotext.logic.constant.NumbersRu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.regex.Pattern;


public class NumberStringConverter {

    private static final Map<String, String> MAP_NUMBER_0_TO_19 = NumbersRu.getNumbersMap();
    private static final Map<String, String> MAP_DOZENS = NumbersRu.getDozensMap();
    private static final Map<String, String> MAP_HUNDREDS = NumbersRu.getHundredsMap();

    private static final List<ExponentOfTen> exponentOfTenList = DAOHolder.getInstance().getDAO().getExponentsOfTen();

    public String convertNumberToString(String originString, boolean longScale) throws Exception {
        validate(originString);

        String convertedStr = "";


        if (originString.startsWith("-")) {
            convertedStr += MAP_NUMBER_0_TO_19.get("-") + " ";
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
        if (numberOfThreeDigitPart.equals("0") || numberOfThreeDigitPart.equals("1")) {
            return "";
        }
        if (numberOfThreeDigitPart.equals("2")) {
            return "тысяч";
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

        if (!Pattern.compile("-?[0-9]+").matcher(string).matches())
            throw new Exception("Incorrect format. Only numbers and \"-\" at the beginning are allowed.");
    }

    private String removeZerosAtBeginning(String string) {
        int strLength = string.length();

        if (strLength > 1) {
            for (int i = 0; i < strLength; i++) {
                if (string.startsWith("0")) {
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

        if (!threeDigitPart.startsWith("1") && !threeDigitPart.endsWith("0")) {
            convertedStr += convertOneDigit(threeDigitPart.substring(1), numberOfThreeDigitPart, longScale);
        }

        return convertedStr;
    }

    private String convertThreeDigitPart(String threeDigitPart, int numberOfThreeDigitPart, boolean longScale) {
        String convertedStr = "";

        if (!threeDigitPart.startsWith("0")) {
            convertedStr += MAP_HUNDREDS.get(threeDigitPart.substring(0, 1)) + " ";

            if (threeDigitPart.charAt(1) == '0' && threeDigitPart.charAt(2) == '0' && numberOfThreeDigitPart > 2) {
                convertedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + "ов" + " ";
            }

            else if (threeDigitPart.charAt(1) == '0' && threeDigitPart.charAt(2) == '0' && numberOfThreeDigitPart == 2) {
                convertedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + " ";
            }
        }

        convertedStr += convertTwoDigits(threeDigitPart.substring(1), numberOfThreeDigitPart, longScale);

        if (threeDigitPart.charAt(2) != '0' && threeDigitPart.charAt(1) != '1') {
            convertedStr += convertOneDigit(threeDigitPart.substring(2), numberOfThreeDigitPart, longScale);
        }
        return convertedStr;
    }


    private String convertTwoDigits(String originStr, int numberOfThreeDigitPart, boolean longScale) {
        String transformedStr = "";

        //(11-19)
        if (originStr.startsWith("1") && !originStr.endsWith("0")) {

            transformedStr += MAP_NUMBER_0_TO_19.get(originStr) + " ";
            transformedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale);

            if (numberOfThreeDigitPart > 2)
                transformedStr += "ов";

            transformedStr += " ";
        }

        else if (!originStr.startsWith("0")) {
            transformedStr += MAP_DOZENS.get(originStr.substring(0, 1)) + " ";

            if (originStr.endsWith("0")) {
                transformedStr += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale);

                if (numberOfThreeDigitPart > 2) {
                    transformedStr += "ов";
                }

                transformedStr += " ";

            }
        }

        return transformedStr;
    }


    private String convertOneDigit(String originStr, int numberOfThreeDigitPart, boolean longScale) {

        String translatedString = "";

        //выводит "один" со степенью
        if (originStr.equals("1") && numberOfThreeDigitPart > 2) {
            translatedString += MAP_NUMBER_0_TO_19.get("1") + " ";
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + " ";
        }

        //выводит в строку "один"
        else if (originStr.equals("1") && numberOfThreeDigitPart < 2)
            translatedString += MAP_NUMBER_0_TO_19.get("1") + " ";

            //выводит в строку склонение 1 и добавляет к слову "тысяч" оаончание "а"
        else if (originStr.equals("1") && numberOfThreeDigitPart == 2) {
            translatedString += MAP_NUMBER_0_TO_19.get("1_") + " ";
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + "а" + " ";
        }

        //выводит числа от 2 до 4 в строку с окончаниями
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && numberOfThreeDigitPart > 2) {
            translatedString += MAP_NUMBER_0_TO_19.get(originStr) + " ";
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + "а" + " ";
        }

        //выводит числа от 2 до 4 в строку с окончаниями
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && numberOfThreeDigitPart == 2) {
            //т.к. 2 имеет отличное от других чисел склонение, то в строку выводится по условию "две"
            if (originStr.equals("2"))
                translatedString += MAP_NUMBER_0_TO_19.get("2_") + " ";
            else
                translatedString += MAP_NUMBER_0_TO_19.get(originStr) + " ";
            translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + "и" + " ";
        }

        //выводит в строку числа, начиная от 2 и до 4 без окончаний
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && numberOfThreeDigitPart < 2)
            translatedString += MAP_NUMBER_0_TO_19.get(originStr) + " ";

            //выводит в строку числа, начиная от 5 и выше
        else if (Integer.parseInt(originStr) >= 5) {
            translatedString += MAP_NUMBER_0_TO_19.get(originStr) + " ";
            //степеням, таким как миллион и дальше придает окончание "ов"
            if (numberOfThreeDigitPart > 2)
                translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + "ов" + " ";
            else if (numberOfThreeDigitPart == 2) translatedString += getExponentOfTenName(String.valueOf(numberOfThreeDigitPart), longScale) + " ";
        } else translatedString += MAP_NUMBER_0_TO_19.get(originStr) + " ";

        return translatedString;
    }

}
