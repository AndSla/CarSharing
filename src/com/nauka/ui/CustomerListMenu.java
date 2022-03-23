package com.nauka.ui;

import com.nauka.dao.Customer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerListMenu extends ListMenu<Customer> {

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(0, Command.MAIN_MENU);
        for (int i = 0; i < getItems().size(); i++) {
            commands.put(i + 1, Command.CUSTOMER_MENU);
        }
        return commands;
    }

    @Override
    void showMenu() {
        setMaxMenuItemNumber(getItems().size());
        setCommands(mapCommands());

        Comparator<Customer> byId = Comparator.comparingInt(Customer::getId);

        if (!getItems().isEmpty()) {
            System.out.println("Choose a customer:");
            setItems(getItems().stream().sorted(byId).collect(Collectors.toList()));
            for (int i = 0; i < getItems().size(); i++) {
                System.out.println(i + 1 + ". " + getItems().get(i));
            }
            System.out.println("0. Back");
            System.out.print("> ");
        } else {
            System.out.println("The customer list is empty!");
            System.out.println();
        }
    }

}
