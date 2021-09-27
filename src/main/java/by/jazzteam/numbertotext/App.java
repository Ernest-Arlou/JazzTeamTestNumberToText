package by.jazzteam.numbertotext;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.math.BigInteger;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    static String numberStr = "12345678910";

    public static void main(String[] args) throws Exception {


        String originStr = "10000000000";

        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        System.out.println(nf.format(Long.parseLong(originStr)));


        Convert convert = new Convert();

//        System.out.println(convert.convertNumberToString("11"));


//        for (int i = 100000; i <= 10000000; i+=100000) {
//            System.out.println(i + " " + convert.convertNumberToString("" + i));
//        }







        System.out.println(originStr + " " + convert.convertNumberToString(originStr));


//
//        List<String> strings = new ArrayList<>();
//
//        int numbOfThreeDigitParts = originStr.length() % 3 == 0 ? originStr.length() / 3 : originStr.length() / 3 + 1;
//
//        while (originStr.length() > 3) {
//            strings.add(originStr.substring(originStr.length()-3));
//            originStr = originStr.substring(0,originStr.length()-3);
//            numbOfThreeDigitParts--;
//        }
//        if (originStr.length() != 0) {
//            strings.add(originStr);
//        }
//
//        System.out.println(strings);
//
//
//        for (int exponent = strings.size()-1; 0 <= exponent; exponent--) {
//            System.out.println(strings.get(exponent) + " " + (exponent + 1));
//        }



    }
}
