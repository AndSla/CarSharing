package com.nauka;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends Menu {

    public MainMenu() {
        setMaxMenuItemNumber(1);
        setCommands(mapCommands());
    }

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.LOGIN);
        commands.put(0, Command.EXIT);
        return commands;
    }

    @Override
    void showMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

}
