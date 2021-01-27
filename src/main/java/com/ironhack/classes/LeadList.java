package com.ironhack.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeadList {
    // Properties
    private Map<Integer,Lead> leadsMap;

    // Constructor
    public LeadList(Map<Integer, Lead> leadsMap) {
        setLeadsMap(leadsMap);
    }

    //Methods

    // Show the list of Leads
    public List<Lead> showLeads() {
        List<Lead> leadsList = new ArrayList<>();
        for (Lead lead : leadsMap.values()) {
            leadsList.add(lead);
        }
        return leadsList;
    }

    // To get Lead info by ID
    public Lead lookUpLeadId (int id) {
        return leadsMap.get(id);
    }

    // To remove a Lead when converted to Opportunity
    public void remove(int id) {
        leadsMap.remove(id);
   }

    // Add lead into LeadList map
    public void addLeadInLeadList(Lead lead) {
        leadsMap.put(lead.getId(), lead);
    }

    // Getters & Setters
    public Map<Integer, Lead> getLeadsMap() {
        return leadsMap;
    }

    public void setLeadsMap(Map<Integer, Lead> leadsMap) {
        this.leadsMap = leadsMap;
    }

    @Override
    public String toString() {
        return leadsMap.values().stream()
                .map(Lead::toString)
                .collect(Collectors.joining("\n"));
    }
}
