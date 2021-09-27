package by.jazzteam.numbertotext.logic;

import org.junit.Assert;
import org.junit.Test;

public class NumberStringConverterTest {

    @Test
    public void convertNumberToString() {

        NumberStringConverter converter = new NumberStringConverter();
        String[][] actualAndExpected = {{
                "0",
                "-1597534862761943",
                "1597534862761943",
                "000000000001",
                "159753486276194315975348627619431597534862761943159753486276194315975348627619431597534862761943",
                "-0000000001597534862",
                "00000000001597534862",
                "01010159"
        }, {
                "ноль",
                "минус один квадриллион пятьсот девяносто семь триллионов пятьсот тридцать четыре миллиарда восемьсот шестьдесят два миллиона семьсот шестьдесят одна тысяча девятьсот сорок три",
                "один квадриллион пятьсот девяносто семь триллионов пятьсот тридцать четыре миллиарда восемьсот шестьдесят два миллиона семьсот шестьдесят одна тысяча девятьсот сорок три",
                "один",
                "сто пятьдесят девять новемвигинтиллиардов семьсот пятьдесят три новемвигинтиллиона четыреста восемьдесят шесть октовигинтиллионов двести семьдесят шесть септемвигинтиллионов сто девяносто четыре сексвигинтиллиона триста пятнадцать квинвигинтиллионов девятьсот семьдесят пять кватторвигинтиллионов триста сорок восемь тревигинтиллионов шестьсот двадцать семь дуовигинтиллионов шестьсот девятнадцать анвигинтиллионов четыреста тридцать один вигинтиллион пятьсот девяносто семь новемдециллионов пятьсот тридцать четыре октодециллиона восемьсот шестьдесят два септемдециллиона семьсот шестьдесят один седециллион девятьсот сорок три квиндециллиона сто пятьдесят девять кваттуордециллионов семьсот пятьдесят три тредециллиона четыреста восемьдесят шесть додециллионов двести семьдесят шесть ундециллионов сто девяносто четыре дециллиона триста пятнадцать нониллионов девятьсот семьдесят пять октиллионов триста сорок восемь септиллионов шестьсот двадцать семь секстиллионов шестьсот девятнадцать квинтиллионов четыреста тридцать один квадриллион пятьсот девяносто семь триллионов пятьсот тридцать четыре миллиарда восемьсот шестьдесят два миллиона семьсот шестьдесят одна тысяча девятьсот сорок три",
                "минус один миллиард пятьсот девяносто семь миллионов пятьсот тридцать четыре тысячи восемьсот шестьдесят два",
                "один миллиард пятьсот девяносто семь миллионов пятьсот тридцать четыре тысячи восемьсот шестьдесят два",
                "один миллион десять тысяч сто пятьдесят девять"}};
        for (int i = 0; i < actualAndExpected.length; ++i) {
            try {
                String actual = converter.convertNumberToString(actualAndExpected[0][i], false);
                String expected = actualAndExpected[1][i];
                Assert.assertEquals(expected, actual);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}