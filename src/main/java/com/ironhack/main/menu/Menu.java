package com.ironhack.main.menu;

import com.ironhack.classes.*;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import com.ironhack.main.menu.command.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.ironhack.classes.Opportunity.getOpportunity;

public class Menu {

    private static final String USER_PROMPT = MenuColors.setColorYellow("CRM:>") + " ";
    private static final String HELP_FILEPATH = "src/main/resources/.help";


    private LeadList leadList = new LeadList(new HashMap<>());
    private Map<Integer, Account> accountMap = new HashMap<>();
    // private Map<String, Account> accountMap = new HashMap<>();

    public void show(){

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(USER_PROMPT);
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) continue;

            Command command = checkCommand(userInput);
            //if (command.equals(Command.UNKNOWN)) continue;

            String[] inputArgs = getArgsFromInput(userInput);
            switch(command) {
                case NEW_LEAD:
                    Lead newLead = newLead();
                    leadList.addLeadInLeadList(newLead);
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
                        //leadList.remove(idToConvert);

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

                case HELP:
                    printHelp();
                    break;

                case UNKNOWN:
                    printUnknownCommand(userInput);
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
        return Command.getCommand(userInput);
    }

    private Lead newLead() {

        Scanner scanner = new Scanner(System.in);
//        System.out.println("New Lead\n--------------");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Email address: ");
        String email = scanner.nextLine().trim();
        System.out.print("Company name: ");
        String companyName = scanner.nextLine().trim();

//        System.out.println("New lead created:");
//        System.out.println(name);
//        System.out.println(phoneNumber);
//        System.out.println(email);
//        System.out.println(companyName);
//        System.out.println("--------------");
        Lead newLead =  new Lead(name, phoneNumber, email, companyName);
        return newLead;
    }

    private void showLeads() {
        System.out.println(leadList);
    }

    private Lead lookupLead(int id) {
        if (leadList.lookUpLeadId(id) == null)
            throw new RuntimeException();

        return leadList.getLeadsMap().get(id);
    }

    private Account convertLead(Lead lead) {

        Contact decisionMaker = createContact(lead);
        Opportunity opportunity = createOpportunity(decisionMaker);
        Account account = createAccount(decisionMaker, opportunity);

        return account;
    }

    private Contact createContact(Lead lead) {
        return new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
    }

    private Opportunity createOpportunity(Contact decisionMaker) {
        Scanner scanner = new Scanner(System.in);

        String productOption = "";
        do {
            System.out.print("Product (" + Product.showOptions() + "): ");
            productOption = scanner.nextLine().trim();
        } while (!Product.isValid(productOption));
        Product product = Product.get(productOption);

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        return new Opportunity(decisionMaker, product, quantity);
    }

    private Account createAccount(Contact contact, Opportunity opportunity) {
        Scanner scanner = new Scanner(System.in);

        String industryOption = "";
        do {
            System.out.print("Industry (" + Industry.showOptions() + "): ");
            industryOption = scanner.nextLine().trim();
        } while (!Industry.isValid(industryOption));
        Industry industry = Industry.get(industryOption);

        System.out.print("Number of employees: ");
        int employeeCount = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        System.out.print("Country: ");
        String country = scanner.nextLine().trim();

        Account account = new Account(industry, employeeCount, city, country, new ArrayList<Contact>(), new ArrayList<Opportunity>());
        account.addToContactList(contact);
        account.addToOpportunityList(opportunity);

        return account;
    }

    private void closeWonOpp(int id) {
        getOpportunity(id).closeWon();
    }

    private void closeLostOpp(int id) {
        getOpportunity(id).closeLost();
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

}
