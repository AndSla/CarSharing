package com.nauka.ui;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends Menu {

    public MainMenu() {
        setMaxMenuItemNumber(3);
        setCommands(mapCommands());
    }

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.MANAGER_MENU);
        commands.put(2, Command.CUSTOMER_LIST_MENU);
        commands.put(3, Command.CUSTOMER_CREATE);
        commands.put(0, Command.EXIT);
        return commands;
    }

    @Override
    void showMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create customer");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

}
