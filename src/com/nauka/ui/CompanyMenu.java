package com.nauka.ui;

import java.util.HashMap;
import java.util.Map;

public class CompanyMenu extends Menu {

    private String companyName;

    public CompanyMenu() {
        setMaxMenuItemNumber(2);
        setCommands(mapCommands());
    }

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.CAR_LIST_MENU);
        commands.put(2, Command.CAR_CREATE);
        commands.put(0, Command.MANAGER_MENU);
        return commands;
    }

    @Override
    void showMenu() {
        System.out.println("'" + companyName + "'" + " company:");
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
        System.out.print("> ");
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
