package com.ironhack.enums;
import com.ironhack.main.menu.command.Command;

public enum Industry {
    // Values
    PRODUCE("Produce"),
    ECOMMERCE("e-Commerce"),
    MANUFACTURING("Manufacturing"),
    MEDICAL("Medical"),
    OTHER("Other");

    private final String name;

    Industry() { this.name = this.name();}
    Industry(String name) { this.name = name; }

    public String getName() {
        return this.name;
    }

    private static final Industry values[] = values();
    public static Industry get(int ordinal) { return values[ordinal]; }


    public static boolean isValid(String input) {
        try {
            get(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Industry get(String input) {
        return get(Integer.parseInt(input)-1);
    }

    public static String showOptions() {
        String options = "";
        for(Industry industry : values) {
            options += "[" + (industry.ordinal()+1) + "]" + industry/*.getName()*/ + " ";
        }
        return options.trim();
    }

    public String toString() {
        return getName();
    }
}
