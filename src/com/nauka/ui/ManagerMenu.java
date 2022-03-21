package com.nauka.ui;

import java.util.HashMap;
import java.util.Map;

public class ManagerMenu extends Menu {

    public ManagerMenu() {
        setMaxMenuItemNumber(2);
        setCommands(mapCommands());
    }

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.COMPANY_LIST_MENU);
        commands.put(2, Command.COMPANY_CREATE);
        commands.put(0, Command.MAIN_MENU);
        return commands;
    }

    @Override
    void showMenu() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        System.out.print("> ");
    }

}
