package by.jazzteam.numbertotext.bean;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class TestPair {

    @CsvBindByName(column = "NUMBER")
    private String number;
    @CsvBindByName(column = "EXPECTED_RESULT")
    private String expectedString;

    public TestPair() {
    }

    public TestPair(String number, String expectedString) {
        this.number = number;
        this.expectedString = expectedString;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpectedString() {
        return expectedString;
    }

    public void setExpectedString(String expectedString) {
        this.expectedString = expectedString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPair testPair = (TestPair) o;
        return Objects.equals(number, testPair.number) &&
                Objects.equals(expectedString, testPair.expectedString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, expectedString);
    }

    @Override
    public String toString() {
        return "TestPair{" +
                "number='" + number + '\'' +
                ", expectedString='" + expectedString + '\'' +
                '}';
    }
}
