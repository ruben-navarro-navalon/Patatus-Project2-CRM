package com.ironhack.enums;

public enum Product {
    HYBRID("Hybrid"),
    FLATBED("Flatbed"),
    BOX("Box");

    private final String name;
    Product() {
        this.name = this.name();
    }
    Product(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private static final Product values[] = values();
    public static Product get(int ordinal) { return values[ordinal]; }

    public static boolean isValid(String input) {
        try {
            get(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Product get(String input) {
        return get(Integer.parseInt(input)-1);
    }

    public static String showOptions() {
        String options = "";
        for(Product product : values) {
            options += "[" + (product.ordinal()+1) + "]" + product.getName() + " ";
        }
        return options.trim();
    }
}
