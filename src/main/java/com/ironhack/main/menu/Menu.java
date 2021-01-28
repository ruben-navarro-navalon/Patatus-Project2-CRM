package com.ironhack.main.menu;

import com.ironhack.classes.*;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import com.ironhack.main.menu.command.Command;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class Menu {

    private static final String USER_PROMPT = MenuColors.setColorYellow("CRM:>") + " ";
    private static final String HELP_FILEPATH = "src/main/resources/.help";


    private final Map<Integer, Lead> leadMap = new HashMap<>();
    private final Map<Integer, Account> accountMap = new HashMap<>();
    private final Map<Integer, Opportunity> opportunityMap = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void show(){


        while (true) {
            System.out.print(USER_PROMPT);
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) continue;

            Command command = checkCommand(userInput);

            String[] inputArgs = getArgsFromInput(userInput);
            switch(command) {
                case NEW_LEAD:
                    Lead newLead = newLead();
                    leadMap.put(newLead.getId(), newLead);
                    System.out.println(">> Added new Lead: " + newLead);
                    break;

                case LOOKUP_LEAD:
                    int idToLookup = command.getArg(inputArgs);
                    try {
                        Lead leadLookup = lookupLead(idToLookup);
                        System.out.println(leadLookup);
                    } catch (RuntimeException e) {
                        printLeadNotFound(idToLookup);
                    }
                    break;

                case SHOW_LEADS:
                    showLeads();
                    break;

                case CONVERT_LEAD:
                    int idToConvert = command.getArg(inputArgs);
                    try {
                        Lead leadConvert = lookupLead(idToConvert);
                        Account account = convertLead(leadConvert);

                        accountMap.put(account.getId(), account);
                        leadMap.remove(idToConvert);

                        System.out.println(">> Added new Account: " + account);
                        System.out.println("<< Removed Lead: " + leadConvert);

                    } catch (RuntimeException e) {
                        printLeadNotFound(idToConvert);
                    }
                    break;

                case CLOSE_WON_OPP:
                    int idToCloseWon = command.getArg(inputArgs);
                    closeWonOpp(idToCloseWon);
                    break;

                case CLOSE_LOST_OPP:
                    int idToCloseLost = command.getArg(inputArgs);
                    closeLostOpp(idToCloseLost);
                    break;

                case SHOW_ACCOUNTS:
                    showAccounts();
                    break;

                case HELP:
                    printHelp();
                    break;

                case UNKNOWN:
                    printUnknownCommand(userInput);
                    break;

                case EXIT:
                    System.out.println("talué!");
                default:
                    return;
            }
        }
    }

    private String[] getArgsFromInput(String userInput) {
        return userInput.toUpperCase().trim().split(" +");
    }

    public Command checkCommand(String userInput) {
        return Command.getCommand(userInput);
    }

    private Lead newLead() {

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone number: ");
        String phoneNumber = readFormattedString("VALID phone number: ", "\\+?\\d{9,13}");
        System.out.print("Email address: ");
        String email =  readFormattedString("VALID email address: ", "[\\w-.]+@(?:[\\w-]+\\.)+[\\w-]+");
        System.out.print("Company name: ");
        String companyName = scanner.nextLine().trim();

        return new Lead(name, phoneNumber, email, companyName);
    }

    private String readFormattedString(String onBadInput, String regex) {
        String input;
        int c = 0;
        while (true) {
            input = scanner.nextLine().trim();
            if (input.matches(regex))
                break;
            c++;
            if (c%3 == 0) {
                onBadInput = "Porfa, deja de vacilarme TT.TT\n" + onBadInput; // cada 3 inputs malos se frustra más
            }
            System.out.println(onBadInput);
        }
        if (c > 3)
            System.out.println("POR FIN");
        return input;
    }

    private void showLeads() {
        System.out.println(mapValuesToString(leadMap));
    }

    private Lead lookupLead(int id) {
        Lead lead = leadMap.get(id);
        if (lead == null)
            throw new RuntimeException();

        return lead;
    }

    private Account convertLead(Lead lead) {

        Contact decisionMaker = createContact(lead);
        Opportunity opportunity = createOpportunity(decisionMaker);

        return createAccount(decisionMaker, opportunity);
    }

    private Contact createContact(Lead lead) {
        return new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
    }

    private Opportunity createOpportunity(Contact decisionMaker) {
        String productOption;
        do {
            System.out.print("Product (" + Product.showOptions() + "): ");
            productOption = scanner.nextLine().trim();
        } while (!Product.isValid(productOption));
        Product product = Product.get(productOption);

        System.out.print("Quantity: ");
        int quantity = readNonNegativeInt("VALID Quantity:");


        Opportunity opportunity = new Opportunity(decisionMaker, product, quantity);
        opportunityMap.put(opportunity.getId(), opportunity);

        return opportunity;
    }

    private int readNonNegativeInt(String onBadInput) {
        int n = -1;
        int c = 0;
        while (n < 0) {
            String line = scanner.nextLine().trim();
            try {
                n = Integer.parseInt(line);
                if (n < 0)
                    System.out.print(onBadInput);
            } catch (Exception e) {
                System.out.print(onBadInput);
            }
            c++;
            if (c%3 == 0) {
                onBadInput = "Porfa, deja de vacilarme TT.TT\n" + onBadInput; // cada 3 inputs malos se frustra más
            }
        }
        if (c > 3)
            System.out.println("POR FIN");
        return n;
    }

    private Account createAccount(Contact contact, Opportunity opportunity) {
        String industryOption;
        do {
            System.out.print("Industry (" + Industry.showOptions() + "): ");
            industryOption = scanner.nextLine().trim();
        } while (!Industry.isValid(industryOption));
        Industry industry = Industry.get(industryOption);

        System.out.print("Number of employees: ");
        int employeeCount = readNonNegativeInt("VALID Number of employees:");
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        System.out.print("Country: ");
        String country = scanner.nextLine().trim();

        Account account = new Account(industry, employeeCount, city, country, new ArrayList<>(), new ArrayList<>());
        account.addToContactList(contact);
        account.addToOpportunityList(opportunity);

        return account;
    }

    private void closeWonOpp(int id) {
        try {
            opportunityMap.get(id).closeWon();
        } catch (NullPointerException e) {
            printOpportunityNotFound(id);
        }
    }
    private void closeLostOpp(int id) {
        try {
            opportunityMap.get(id).closeLost();
        } catch (NullPointerException e) {
            printOpportunityNotFound(id);
        }
    }

    private void showAccounts() {
        System.out.println(mapValuesToString(accountMap));
    }


    private void printHelp() {
        try {
            File file = new File(HELP_FILEPATH);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                //System.out.println(MenuColors.setColorYellow(fileScanner.nextLine()));
                System.out.println(fileScanner.nextLine());
            }
        } catch (Exception e) {
            //System.out.println(MenuColors.setColorRed("Error: Help file could not be found."));
            System.out.println("Error: Help file could not be found.");
        }
    }

    private void printUnknownCommand(String userInput) {
        //System.out.println(MenuColors.setColorRed("'" + userInput + "' is not a valid command."));
        System.out.println("'" + userInput + "' is not a valid command.");
    }

    private void printLeadNotFound(int id) {
        System.out.println("Lead with id=" + id + " not found.");
    }

    private void printOpportunityNotFound(int id) {
        System.out.println("Opportunity with id=" + id + " not found.");
    }

    public <T, T2> String mapValuesToString(Map<T2, T> map) {
        return map.values().stream()
                .map(T::toString)
                .collect(Collectors.joining("\n"));
    }

}
