package com.nauka;

import java.util.HashMap;
import java.util.Map;

public class CustomerMenu extends Menu{

    public CustomerMenu() {
        setMaxMenuItemNumber(3);
        setCommands(mapCommands());
    }

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.RENT_CAR);
        commands.put(2, Command.RETURN_CAR);
        commands.put(3, Command.RENTED_CARS);
        commands.put(0, Command.BACK_TO_MAIN_MENU);
        return commands;
    }

    @Override
    void showMenu() {
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
        System.out.print("> ");
    }

}
