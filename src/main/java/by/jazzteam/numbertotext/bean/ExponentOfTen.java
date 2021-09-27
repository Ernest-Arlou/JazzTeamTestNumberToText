package by.jazzteam.numbertotext.bean;

import java.util.Objects;

public class ExponentOfTen {
    private String name;
    private long shortScaleExponent;
    private long longScaleExponent;

    public ExponentOfTen() {

    }

    public ExponentOfTen(String name, long shortScaleExponent, long longScaleExponent) {
        this.name = name;
        this.shortScaleExponent = shortScaleExponent;
        this.longScaleExponent = longScaleExponent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getShortScaleExponent() {
        return shortScaleExponent;
    }

    public void setShortScaleExponent(long shortScaleExponent) {
        this.shortScaleExponent = shortScaleExponent;
    }

    public long getLongScaleExponent() {
        return longScaleExponent;
    }

    public void setLongScaleExponent(long longScaleExponent) {
        this.longScaleExponent = longScaleExponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExponentOfTen that = (ExponentOfTen) o;
        return shortScaleExponent == that.shortScaleExponent &&
                longScaleExponent == that.longScaleExponent &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortScaleExponent, longScaleExponent);
    }

    @Override
    public String toString() {
        return "ExponentOfTen{" +
                "name='" + name + '\'' +
                ", shortScaleExponent=" + shortScaleExponent +
                ", longScaleExponent=" + longScaleExponent +
                '}';
    }
}
