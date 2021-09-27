package by.jazzteam.numbertotext.service;

public class NumberStringConverterHolder {
    private final NumberStringConverter numberStringConverter = new NumberStringConverterImpl();

    private static class InstanceHolder {
        public static final NumberStringConverterHolder instance = new NumberStringConverterHolder();
    }

    private NumberStringConverterHolder() {
    }

    public static NumberStringConverterHolder getInstance() {
        return NumberStringConverterHolder.InstanceHolder.instance;
    }

    public NumberStringConverter getNumberStringConverterHolder() {
        return numberStringConverter;
    }

}
