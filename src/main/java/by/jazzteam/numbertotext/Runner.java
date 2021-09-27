package by.jazzteam.numbertotext;

import by.jazzteam.numbertotext.logic.NumberStringConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Runner {
    static String numberStr = "12345678910";

    public static void main(String[] args) throws Exception {

        NumberStringConverter conv = new NumberStringConverter();
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите ваше число: ");
        System.out.print(conv.convertNumberToString(read.readLine(), false));
        read.close();

        System.out.println(conv.convertNumberToString("1000000", false));













        String originStr = "1000000000000";

//        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
//                RuleBasedNumberFormat.SPELLOUT);
//        System.out.println(nf.format(Long.parseLong(originStr)));


        NumberStringConverter numberStringConverter = new NumberStringConverter();

//        System.out.println(convert.convertNumberToString("11"));


        for (int i = 0; i <= 100; i++) {
            System.out.println(i + " " + numberStringConverter.convertNumberToString("" + i, false));
        }







//        System.out.println(originStr + " " + convert.convertNumberToString(originStr, true));





    }
}
