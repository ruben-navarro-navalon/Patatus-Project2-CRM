package com.ironhack.main;

import com.ironhack.main.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        System.out.println("CRM Console. Type HELP for a command list.");
        menu.show();
    }
}
