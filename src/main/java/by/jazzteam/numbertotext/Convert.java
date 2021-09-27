package by.jazzteam.numbertotext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;



public class Convert {

    //Содержатся единичные и составные числа, и знак отрицательности
    private static final TreeMap<String, String> MAP_NUMBER_1_TO_19 = new TreeMap<String, String>() {{
        put("-", "минус");
        put("0", "ноль");
        put("1", "один");
        put("1_", "одна");
        put("2", "два");
        put("2_", "две");
        put("3", "три");
        put("4", "четыре");
        put("5", "пять");
        put("6", "шесть");
        put("7", "семь");
        put("8", "восемь");
        put("9", "девять");
        put("11", "одинадцать");
        put("12", "двенадцать");
        put("13", "тринадцать");
        put("14", "четырнадцать");
        put("15", "пятнадцать");
        put("16", "шеснадцать");
        put("17", "семьнадцать");
        put("18", "восемьнадцать");
        put("19", "девятнадцать");
    }};

    //Содержатся десятки, от 10 и до 90
    private static final TreeMap<String, String> MAP_DOZENS_OF = new TreeMap<String, String>() {{
        put("1", "десять");
        put("2", "двадцать");
        put("3", "тридцать");
        put("4", "сорок");
        put("5", "пятьдесят");
        put("6", "шестьдесят");
        put("7", "семьдесят");
        put("8", "восемьдесят");
        put("9", "девяносто");
    }};

    //Содержатся сотни, от 100 и до 900
    private static final TreeMap<String, String> MAP_HUNDREDS = new TreeMap<String, String>() {{
        put("0", "");
        put("1", "сто");
        put("2", "двести");
        put("3", "триста");
        put("4", "четыреста");
        put("5", "пятьсот");
        put("6", "шестьсот");
        put("7", "семьсот");
        put("8", "восемьсот");
        put("9", "девятьсот");
    }};

    //Содержатся степени чисел, от 10^3 и до 10^96
    private static final TreeMap<String, String> MAP_EXPONENTS_OF_TEN = new TreeMap<String, String>() {{
        put("0", "");
        put("1", "");
        put("2", "тысяч");
        put("3", "миллион");
        put("4", "миллиард");
        put("5", "триллион");
        put("6", "квадриллион");
        put("7", "квинтиллион");
        put("8", "секстиллион");
        put("9", "септиллион");
        put("10", "октиллион");
        put("11", "нониллион");
        put("12", "дециллион");
        put("13", "андециллион");
        put("14", "дуодециллион");
        put("15", "тредециллион");
        put("16", "кваттордециллион");
        put("17", "квиндециллион");
        put("18", "сексдециллион");
        put("19", "септемдециллион");
        put("20", "октодециллион");
        put("21", "новемдециллион");
        put("22", "вигинтиллион");
        put("23", "анвигинтиллион");
        put("24", "дуовигинтиллион");
        put("25", "тревигинтиллион");
        put("26", "кватторвигинтиллион");
        put("27", "квинвигинтиллион");
        put("28", "сексвигинтиллион");
        put("29", "септемвигинтиллион");
        put("30", "октовигинтиллион");
        put("31", "новемвигинтиллион");
        put("32", "тригинтиллион");
        put("33", "антригинтиллион");

    }};

    /**
     * Метод в который передается строка для дальнейшего преобразования
     *
     * @param originString - получаемая строка чисел.
     * @return строка, полученная после преобразования числа в текстовый вид
     * @throws Exception - если строка длинною более 99 символов или пустая строка,
     *                   или введены символы не попадающие в формат -?[0-9]+.
     */
    public String convertNumberToString(String originString) throws Exception {

        if (originString.length() > 99)
            throw new Exception("Введен слишком длинный аргумент. Максимальная длинна строки 99 символов.");

        if (originString.length() == 0)
            throw new Exception("Вы ввели пустую строку. Преобразование невозможно.");

        if (!Pattern.compile("-?[0-9]+").matcher(originString).matches())
            throw new Exception("Строка введена некорректно. Используйте только числа и символ \"-\" в начале строки.");


        return subString(originString);
    }

    /**
     * Полученная строка разбивается на подстроки по 3 символа,
     * которые передаются в метод convertSubstring для преобразования
     */
    private String subString(String originStr) {
        String convertedStr = "";


        //проверка на наличие знака отрицательности
        if (originStr.startsWith("-")) {
            convertedStr += MAP_NUMBER_1_TO_19.get("-") + " ";
            originStr = originStr.substring(1);
        }

        int strLength = originStr.length();

        //если длинна строки > 1 символа
        if (strLength > 1) {
            for (int i = 0; i < strLength; i++) {
                if (originStr.startsWith("0")) {
                    originStr = originStr.substring(1);
                } else break;
            }
        }

        List<String> threDigitPartsList = new ArrayList<>();

        int numbOfThreeDigitParts = originStr.length() % 3 == 0 ? originStr.length() / 3 : originStr.length() / 3 + 1;

        while (originStr.length() > 3) {
            threDigitPartsList.add(originStr.substring(originStr.length() - 3));
            originStr = originStr.substring(0, originStr.length() - 3);
            numbOfThreeDigitParts--;
        }
        if (originStr.length() != 0) {
            threDigitPartsList.add(originStr);
        }


        for (int i = threDigitPartsList.size() - 1; 0 <= i; i--) {

            String threeDigitPart = threDigitPartsList.get(i);
            int exponent = i + 1;

            if (threeDigitPart.length() == 3) {
                //выводит в строку значение элемента массива с индексом 0
                if (!threeDigitPart.startsWith("0")) {
                    convertedStr += MAP_HUNDREDS.get(threeDigitPart.substring(0, 1)) + " ";
                    //выводит в строку степень с приставкой
                    if (threeDigitPart.charAt(1) == '0' && threeDigitPart.charAt(2) == '0' && exponent > 2)
                        convertedStr += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + "ов" + " ";
                        //выводит в строку "тысяч"
                    else if (threeDigitPart.charAt(1) == '0' && threeDigitPart.charAt(2) == '0' && exponent == 2)
                        convertedStr += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + " ";
                }

                //вызов метода для преобразования последних 2-х символов
                convertedStr += convertTwoDigits(threeDigitPart.substring(1), exponent);

                //вызов метода для преобразования последнего символа

                if (threeDigitPart.charAt(2) != '0' && threeDigitPart.charAt(1) != '1') {
                    convertedStr += convertOneDigit(threeDigitPart.substring(2), exponent);
                }

                continue;
            }

            if (threeDigitPart.length() == 2) {

                //вызов метода для преобразования массива в 2 символа
                convertedStr += convertTwoDigits(threeDigitPart, exponent);

                //вызов метода для преобразования последнего символа

                if (!threeDigitPart.startsWith("1") && !threeDigitPart.endsWith("0")) {
                    convertedStr += convertOneDigit(threeDigitPart.substring(1), exponent);
                }

                continue;
            }

            if (threeDigitPart.length() == 1) {
                convertedStr += convertOneDigit(threeDigitPart, exponent);
            }

        }

        return convertedStr.trim();

    }

    //    /**
//     * Преобразование подстроки размером в 2 символа
//     *
//     * @param subSubstr - получаемый массив подстрок
//     * @param i         - индекс степени числа
//     * @param j         - размер передаваемого массива
//     */
    private String convertTwoDigits(String originStr, int exponent) {


        String transformedStr = "";


        //выводит в строку составное число (11-19)
        if (originStr.startsWith("1") && !originStr.endsWith("0")) {

            transformedStr += MAP_NUMBER_1_TO_19.get(originStr) + " ";
            transformedStr += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent));

            if (exponent > 2)
                transformedStr += "ов";

            transformedStr += " ";
        }

        //выводит в строку значение ячейки массива с индексом j-1(0)
        else if (!originStr.startsWith("0")) {
            transformedStr += MAP_DOZENS_OF.get(originStr.substring(0, 1)) + " ";
            //добавляет степень

            if (originStr.endsWith("0")) {
                transformedStr += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent));

                System.out.println(exponent);

                if (exponent > 2) {
                    transformedStr += "ов";
                }

                transformedStr += " ";

            }
        }

        return transformedStr;
    }


    /**
     * Преобразование подстроки в 1 символ
     *
     * @param originStr - получаемая подстрока
     * @param exponent  - индекс степени числа
     */
    private String convertOneDigit(String originStr, int exponent) {

        String translatedString = "";

        //выводит "один" со степенью
        if (originStr.equals("1") && exponent > 2) {
            translatedString += MAP_NUMBER_1_TO_19.get("1") + " ";
            translatedString += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + " ";
        }

        //выводит в строку "один"
        else if (originStr.equals("1") && exponent < 2)
            translatedString += MAP_NUMBER_1_TO_19.get("1") + " ";

            //выводит в строку склонение 1 и добавляет к слову "тысяч" оаончание "а"
        else if (originStr.equals("1") && exponent == 2) {
            translatedString += MAP_NUMBER_1_TO_19.get("1_") + " ";
            translatedString += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + "а" + " ";
        }

        //выводит числа от 2 до 4 в строку с окончаниями
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && exponent > 2) {
            translatedString += MAP_NUMBER_1_TO_19.get(originStr) + " ";
            translatedString += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + "а" + " ";
        }

        //выводит числа от 2 до 4 в строку с окончаниями
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && exponent == 2) {
            //т.к. 2 имеет отличное от других чисел склонение, то в строку выводится по условию "две"
            if (originStr.equals("2"))
                translatedString += MAP_NUMBER_1_TO_19.get("2_") + " ";
            else
                translatedString += MAP_NUMBER_1_TO_19.get(originStr) + " ";
            translatedString += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + "и" + " ";
        }

        //выводит в строку числа, начиная от 2 и до 4 без окончаний
        else if (Integer.parseInt(originStr) >= 2 && Integer.parseInt(originStr) <= 4 && exponent < 2)
            translatedString += MAP_NUMBER_1_TO_19.get(originStr) + " ";

            //выводит в строку числа, начиная от 5 и выше
        else if (Integer.parseInt(originStr) >= 5) {
            translatedString += MAP_NUMBER_1_TO_19.get(originStr) + " ";
            //степеням, таким как миллион и дальше придает окончание "ов"
            if (exponent > 2)
                translatedString += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + "ов" + " ";
            else if (exponent == 2) translatedString += MAP_EXPONENTS_OF_TEN.get(String.valueOf(exponent)) + " ";
        } else translatedString += MAP_NUMBER_1_TO_19.get(originStr) + " ";

        return translatedString;
    }





    public static void main(String[] args) throws Exception {

        Convert conv = new Convert();
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите ваше число: ");
        System.out.print(conv.convertNumberToString(read.readLine()));
        read.close();

    }

}
