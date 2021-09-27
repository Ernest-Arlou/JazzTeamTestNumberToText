package by.jazzteam.numbertotext.service.constant;

import java.util.Map;
import java.util.TreeMap;

public class NumbersRu {
    private static final TreeMap<String, String> MAP_NUMBER_0_TO_19 = new TreeMap<String, String>() {{
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

    private static final TreeMap<String, String> MAP_DOZENS = new TreeMap<String, String>() {{
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

    public static Map<String, String> getNumbersMap(){
        return new TreeMap<>(MAP_NUMBER_0_TO_19);
    }

    public static Map<String, String> getDozensMap(){
        return new TreeMap<>(MAP_DOZENS);
    }

    public static Map<String, String> getHundredsMap(){
        return new TreeMap<>(MAP_HUNDREDS);
    }
}
