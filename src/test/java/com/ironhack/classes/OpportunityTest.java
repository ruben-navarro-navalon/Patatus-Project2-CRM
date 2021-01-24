package com.ironhack.classes;

import com.ironhack.enums.Product;
import com.ironhack.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.ironhack.classes.Opportunity.getOpportunity;
import static org.junit.jupiter.api.Assertions.*;

class OpportunityTest {
    private static Opportunity opportunity;
    private static Opportunity opportunity2;
    private static Opportunity opportunity3;

    @BeforeAll
    static void beforeAll() {
        opportunity = new Opportunity(new Contact("Juan", "+34916826184","juan@asinc.com", "As INC."), Product.BOX, 5);
        opportunity2 = new Opportunity(new Contact("Alexa", "+34916826184","juan@asinc.com", "As INC."), Product.BOX, 5);
        opportunity3 = new Opportunity(new Contact("Siri", "+34916826184","juan@asinc.com", "As INC."), Product.BOX, 5);
    }

    @Test
    void closeWon_opportunityObject_statusChangedToWon() {
        opportunity.closeWon();
        assertEquals(Status.CLOSED_WON, opportunity.getStatus());
    }

    @Test
    void closeLost_opportunityObject_statusChangedToLost() {
        opportunity.closeLost();
        assertEquals(Status.CLOSED_LOST, opportunity.getStatus());
    }

    @Test
    void getOpportunity_opportunityList_opportunityWithCorrectId() {
        assertEquals(opportunity3, getOpportunity(2));
    }

    @Test
    void getOpportunity_opportunityList_opportunityWithIncorrectId() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {getOpportunity(9);});
    }

    @Test
    void generateId() {
        assertEquals(1, opportunity2.getId());
    }
}