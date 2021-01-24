package com.ironhack.main.menu;

import com.ironhack.classes.*;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import com.ironhack.main.menu.command.Command;
import com.ironhack.main.menu.command.Keyword;

import java.io.File;
import java.util.Scanner;

public class Menu {

    private static final String USER_PROMPT = MenuColors.setColorYellow("CRM:>") + " ";
    private static final String HELP_FILEPATH = "src/main/resources/.help";


    private LeadList leadList;
    // todo private Map<Integer, Account> accountMap;

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
                    newLead();
                    break;

                case LOOKUP_LEAD:
                    int idToLookup = command.getArg(inputArgs);
                    Lead lead = lookupLead(idToLookup);
                    System.out.println(lead);
                    break;

                case SHOW_LEADS:
                    showLeads();
                    break;

                case CONVERT_LEAD:
                    int idToConvert = command.getArg(inputArgs);
                    Account account = convertLead(idToConvert);
                    // todo accountMap.put(account.getId(), account);
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
                    try {
                        File file = new File(HELP_FILEPATH);
                        Scanner fileScanner = new Scanner(file);
                        while(fileScanner.hasNextLine()) {
                            System.out.println(MenuColors.setColorYellow(fileScanner.nextLine()));
                        }
                    }
                    catch (Exception e) {
                        System.out.println(MenuColors.setColorRed("Error: Help file could not be found."));
                    }
                    break;

                case UNKNOWN:
                    System.out.println(MenuColors.setColorRed("'" + userInput + "' is not a valid command."));
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
        // todo return new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
        return null;
    }

    private Opportunity createOpportunity(Lead lead) {
        Scanner scanner = new Scanner(System.in);

        String productOption = "";
        do {
            System.out.print("Product (" + Product.showOptions() + "): ");
            productOption = scanner.nextLine().trim();
        } while (!Product.isValid(productOption));
        Product product = Product.get(productOption);

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        // todo return lead.convertToOpportunity(product, quantity);
        return null;
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
