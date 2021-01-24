package com.ironhack.classes;

import com.ironhack.classes.Account;
import com.ironhack.enums.Industry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private static Account account1;
    private static Account account2;

    @BeforeAll
    public static void setUp() {
        List<Contact> contactList = new ArrayList<>();
        List<Opportunity> opportunityList = new ArrayList<>();
        account1 = new Account(Industry.PRODUCE, 3, "Madrid", "Spain",contactList,opportunityList);
        account2 = new Account(Industry.ECOMMERCE, 5, "Barcelona", "Spain", contactList, opportunityList);
    }

    @Test
    void setId_FirstAccount_ID0(){
        assertEquals(0,account1.getId());
    }

    @Test
    void generateID_NewAccount_ID1(){
        assertEquals(1, account2.getId());
    }

}