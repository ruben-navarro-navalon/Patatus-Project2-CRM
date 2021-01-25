package com.ironhack.classes;

import com.ironhack.enums.Industry;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Account {
    // Properties
    private int id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Contact> contactList;
    private List<Opportunity> opportunityList;

    private final static List<Account> accountList = new ArrayList<>();

    // This is for the incremented self-generated id:
    private static int idGenerator = 0;

    // Constructor:
    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList, List<Opportunity> opportunityList) {
        setId();
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        setContactList(contactList);
        setOpportunityList(opportunityList);
        accountList.add(this);
    }

    public void addToContactList(Contact contact){
        getContactList().add(contact);
    }
    public void addToOpportunityList(Opportunity opportunity){
        getOpportunityList().add(opportunity);
    }

    @Override
    public String toString() {


        return "\n   ID-" + id +
                " | industry: " + industry +
                " | employeeCount: " + employeeCount +
                " | city: " + city +
                " | country: " + country +
                "\n   List of Contacts:" +
                "\n      " + contactList.stream()
                .map(Contact::toString)
                .collect(Collectors.joining("\n")) +
                "\n   List of Opportunities:" +
                "\n      " + opportunityList.stream()
                .map(Opportunity::toString)
                .collect(Collectors.joining("\n"));
    }

    // Gettes & Setters:
    public int getId() {
        return id;
    }

    public void setId() {
        this.id = generateId();
    }

    public static int generateId(){
        return idGenerator++;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    public static List<Account> getAccountList() {
        return accountList;
    }
}
