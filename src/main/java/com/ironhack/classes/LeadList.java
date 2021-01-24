package com.ironhack.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class LeadList {
    // Properties
    private Map<Integer,Lead> leads;

    // Constructor
    public LeadList(Map<Integer, Lead> leads) {
        setLeads(leads);
    }

    //Methods

    // Show the list of Leads
    public List<Lead> showLeads() {
        List<Lead> leadsList = new ArrayList<>();
        for (Lead lead : leads.values()) {
            leadsList.add(lead);
        }
        return leadsList;
    }

    // To get Lead info by ID
    public Lead lookUpLeadId (int id) {
        return leads.get(id);
        // poner print dentro del metodo??
    }
    /** remove, duda */
//    public void remove(int id) {
//        leads.get(id) = null;
//        leads.remove(id);
//    }

    // Getters & Setters
    public Map<Integer, Lead> getLeads() {
        return leads;
    }

    public void setLeads(Map<Integer, Lead> leads) {
        this.leads = leads;
    }
}
