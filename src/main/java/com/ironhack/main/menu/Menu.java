package com.ironhack.main.menu;

import com.ironhack.classes.*;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;

import java.util.Scanner;

public class Menu {
    private LeadList leadList;
    // todo private Map<Integer, Account> accountMap;

    public void show(){

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("CRM:> ");
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) continue;

            Command command = checkCommand(userInput);
            if (command.equals(Command.UNKNOWN)) continue;

            String[] inputArgs = getArgsFromInput(userInput);
            switch(command) {
                case NEW_LEAD:
                    newLead();
                    break;

                case LOOKUP_LEAD:
                    int idToLookup = Integer.parseInt(inputArgs[2]);
                    Lead lead = lookupLead(idToLookup);
                    System.out.println(lead);
                    break;

                case SHOW_LEADS:
                    showLeads();
                    break;

                case CONVERT_LEAD:
                    int idToConvert = Integer.parseInt(inputArgs[1]);
                    Account account = convertLead(idToConvert);
                    // todo accountMap.put(account.getId(), account);
                    break;

                case CLOSE_WON_OPP:
                    int idToCloseWon = Integer.parseInt(inputArgs[1]);
                    closeWonOpp(idToCloseWon);
                    break;

                case CLOSE_LOST_OPP:
                    int idToCloseLost = Integer.parseInt(inputArgs[1]);
                    closeLostOpp(idToCloseLost);
                    break;

                case EXIT:
                default:
                    return;
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
            if (inputArgs.length < 2) {
                // except the special command exit
                if (inputArgs[0].equals("EXIT"))
                    return Command.EXIT;
                throw new IllegalArgumentException();
            }

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
            //System.err.println(userInput + ": unknown command");
            System.out.println(userInput + ": unknown command");
        }
        return Command.UNKNOWN;
    }

    private Lead newLead() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("New Lead\n--------------");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Email address: ");
        String email = scanner.nextLine().trim();
        System.out.print("Company name: ");
        String companyName = scanner.nextLine().trim();

        System.out.println("New lead created:");
        System.out.println(name);
        System.out.println(phoneNumber);
        System.out.println(email);
        System.out.println(companyName);
        System.out.println("--------------");

        // todo add new created Lead to LeadList
        return null;
    }

    private void showLeads() {
        //leadList.show();
        System.out.println(leadList);
    }

    private Lead lookupLead(int id) {
        // todo return leadList.get(id);
        return null;
    }

    private Account convertLead(int id) {
        Lead lead = lookupLead(id);

        // todo lanzar exception desde lookupLead?
//        if (lead == null)
//            throw new RuntimeException("The lead with id=" + id + " does not exist.");

        Contact decisionMaker = createContact(lead);
        Opportunity opportunity = createOpportunity(lead);
        Account account = createAccount(decisionMaker, opportunity);

        return account;
    }

    private Contact createContact(Lead lead) {
        // todo return newContact
        return null;
    }

    private Opportunity createOpportunity(Lead lead) {
        Scanner scanner = new Scanner(System.in);
        int productOption = 0;
        do {
            System.out.print("Product ([1]HYBRID [2]FLATBED [3]BOX): ");
            productOption = Integer.parseInt(scanner.nextLine().trim());
        } while (productOption < 1 || productOption > Product.values().length);
        Product product = Product.values()[productOption-1];
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        // todo return lead.convertToOpportunity(product, quantity);
        return null;
    }

    private Account createAccount(Contact contact, Opportunity opportunity) {
        Scanner scanner = new Scanner(System.in);
        int industryOption = 0;
        do {
            System.out.print("Industry ([1]PRODUCE [2]ECOMMERCE [3]MANUFACTURING [4]MEDICAL [5]OTHER): ");
            industryOption = Integer.parseInt(scanner.nextLine().trim());
        } while (industryOption < 1 || industryOption > Industry.values().length);
        Industry industry = Industry.values()[industryOption-1];
        System.out.print("Number of employees: ");
        int employeeCount = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        System.out.print("Country: ");
        String country = scanner.nextLine().trim();

        // todo return new Account(contact, opportunity, industry, employeeCount, city, country);
        return null;
    }


    private void closeWonOpp(int id) {
        // todo getOpportunity(id).closeWon()
    }

    private void closeLostOpp(int id) {
        // todo getOpportunity(id).closeLost()
    }

}