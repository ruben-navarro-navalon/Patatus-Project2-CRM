package com.ironhack.main.menu.command;

public enum Command {
    UNKNOWN("UNKNOWN", 0),
    NEW_LEAD(Keyword.NEW + " " + Keyword.LEAD, 0),
    SHOW_LEADS(Keyword.SHOW + " " + Keyword.LEADS, 0),
    LOOKUP_LEAD(Keyword.LOOKUP + " " + Keyword.LEAD, 1),
    CONVERT_LEAD(Keyword.CONVERT, 1),
    CLOSE_LOST_OPP(Keyword.CLOSE_LOST, 1),
    CLOSE_WON_OPP(Keyword.CLOSE_WON, 1),
    HELP(Keyword.HELP, 0),
    EXIT(Keyword.EXIT, 0);

    private final String symbol;
    private final int nArgs;

    private Command(String symbol, int nArgs) {
        this.symbol = symbol;
        this.nArgs = nArgs;
    }

    private String getSymbol() {
        return this.symbol;
    }

    private int length() {
        return symbol.split(" ").length;
    }

    private int nArgs() {
        return this.nArgs;
    }

    private boolean hasArgs() {
        return this.nArgs > 0;
    }

    public int getArg(String[] inputArgs) {
        return Integer.parseInt(inputArgs[length() + nArgs() - 1]);
    }

    public static Command getCommand(String userInput) {
        String[] inputArgs = userInput.toUpperCase().trim().split(" +");
        String normalizedInput = "";
        for (String token : inputArgs)
            normalizedInput += token + " ";
        normalizedInput = normalizedInput.trim();

        try {
            // look for a command that matches the user input
            for(Command command : values()) {

                // skip the generic unknown command
                if (command.equals(UNKNOWN)) continue;

                if (normalizedInput.startsWith(command.getSymbol()) &&
                    inputArgs.length == command.length() + command.nArgs()) {
                    if (command.hasArgs())
                        command.getArg(inputArgs);
                    return command;
                }
            }
        }
        catch (Exception e) {
            return UNKNOWN;
        }
        return UNKNOWN;
    }

    private static final Command values[] = values();
    public static Command get(int ordinal) { return values[ordinal]; }
}
