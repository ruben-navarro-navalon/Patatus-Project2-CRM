package com.ironhack.main;

import com.ironhack.classes.Lead;
import com.ironhack.classes.LeadList;
import com.ironhack.classes.Opportunity;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private LeadList leadList;

    public void show(){

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Please, enter your command: ");
            String userInput = scanner.nextLine();

            if (userInput.isEmpty()) continue;

            Command command = checkCommand(userInput);

            if (command.equals(Command.UNKNOWN)) continue;

            String[] inputArgs = getArgsFromInput(userInput);
            switch(command) {
                case NEW_LEAD:
                    // todo newLead()
                    break;

                case LOOKUP_LEAD:
                    int idToLookup = Integer.parseInt(inputArgs[2]);
                    Lead lead = lookupLead(idToLookup);
                    System.out.println(lead);
                    break;

                case SHOW_LEADS:
                    System.out.println(leadList);
                    break;

                case CONVERT_LEAD:
                    int idToConvert = Integer.parseInt(inputArgs[1]);
                    Opportunity opp = convertLead(idToConvert);
                    break;

                case CLOSE_WON_OPP:
                    int idToCloseWon = Integer.parseInt(inputArgs[1]);
                    Opportunity oppWon = closeWonOpp(idToCloseWon);
                    break;

                case CLOSE_LOST_OPP:
                    int idToCloseLost = Integer.parseInt(inputArgs[1]);
                    Opportunity oppLost = closeLostOpp(idToCloseLost);
                    break;

                default:
                    break;
            }
        }
    }

    private String[] getArgsFromInput(String userInput) {
        return userInput.toUpperCase().trim().split(" +");
    }

    public Command checkCommand(String userInput) {

        String[] inputArgs = getArgsFromInput(userInput);

        try {
            // at least two args are present
            if (inputArgs.length < 2)
                throw new IllegalArgumentException();

            // first we check the second input string
            switch (inputArgs[1]) {
                case "LEAD":
                    switch (inputArgs[0]) {
                        case "NEW":
                            if (inputArgs.length <= 2)
                                return Command.NEW_LEAD;
                            throw new IllegalArgumentException();
                        case "LOOKUP":
                            if (inputArgs.length <= 3) {
                                Integer.parseInt(inputArgs[2]);
                                return Command.LOOKUP_LEAD;
                            }
                            throw new IllegalArgumentException();
                        default:
                            throw new IllegalArgumentException();
                    }

                case "LEADS":
                    if (inputArgs[0].equals("SHOW") && inputArgs.length <= 2)
                        return Command.SHOW_LEADS;
                    throw new IllegalArgumentException();

                default:
                    break;
            }

            // then we check the first input string
            switch (inputArgs[0]) {
                case "CONVERT":
                    if (inputArgs.length <= 2) {
                        Integer.parseInt(inputArgs[1]);
                        return Command.CONVERT_LEAD;
                    }
                    throw new IllegalArgumentException();

                case "CLOSE-LOST":
                    if (inputArgs.length <= 2) {
                        Integer.parseInt(inputArgs[1]);
                        return Command.CLOSE_LOST_OPP;
                    }
                    throw new IllegalArgumentException();

                case "CLOSE-WON":
                    if (inputArgs.length <= 2){
                        Integer.parseInt(inputArgs[1]);
                        return Command.CLOSE_WON_OPP;
                    }
                    throw new IllegalArgumentException();

                default:
                    throw new IllegalArgumentException();
            }
        }
        catch (Exception e)
        {
            System.err.println(userInput + ": unknown command");
        }
        return Command.UNKNOWN;
    }

    private Lead newLead() {
        // todo add new created Lead to LeadList
        return null;
    }

    private Lead lookupLead(int id) {
        // todo return leadList.get(id);
        return null;
    }

    private Opportunity convertLead(int id) {
        Lead lead = lookupLead(id);
        // todo return lead.convertToOpportunity();
        return null;
    }

    private Opportunity closeWonOpp(int id) {
        // todo
        return null;
    }

    private Opportunity closeLostOpp(int id) {
        // todo
        return null;
    }



}
