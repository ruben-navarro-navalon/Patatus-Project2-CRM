package com.ironhack.classes;

import com.ironhack.enums.*;

import java.util.ArrayList;
import java.util.List;

public class Opportunity {
    private int id;
    private int quantity;
    private Contact decisionMaker;
    private Status status;
    private Product product;
    private final static List<Opportunity> opportunityList = new ArrayList<>();

    //Constructor
    public Opportunity(Contact decisionMaker, Product product, int quantity) {
        setId();
        setDecisionMaker(decisionMaker);
        status = Status.OPEN;
        setProduct(product);
        setQuantity(quantity);
        opportunityList.add(this);
    }

    // To generate autoincrementID:
    private static int idGenerator = 0;

    //Method to call from menu
    public void closeWon(){
        this.setStatus(Status.CLOSED_WON);
    }

    //Method to call from menu
    public void closeLost(){
        this.setStatus(Status.CLOSED_LOST);
    }

    //Method to call from menu
    public static Opportunity getOpportunity(int id){
        if (getOpportunityList().get(id).getId() == id){
            return getOpportunityList().get(id);
        } else {
            System.out.println("The id " + id + " is not in our Opportunities database.\n");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    //Getters and setters
    public void setId() {
        this.id = generateId();
    }

    public static int generateId()  {
        return idGenerator++;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
