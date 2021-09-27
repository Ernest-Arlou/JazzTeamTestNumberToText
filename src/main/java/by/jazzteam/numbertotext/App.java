package by.jazzteam.numbertotext;

/**
 * Hello world!
 */
public class App {
    static String numberStr = "12345678910";

    public static void main(String[] args) throws Exception {


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
