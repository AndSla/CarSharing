package com.nauka.ui;

import com.nauka.dao.Customer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerListMenu extends Menu {
    private List<Customer> customers;

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(0, Command.MAIN_MENU);
        for (int i = 0; i < customers.size(); i++) {
            commands.put(i + 1, Command.CUSTOMER_MENU);
        }
        return commands;
    }

    @Override
    void showMenu() {
        setMaxMenuItemNumber(customers.size());
        setCommands(mapCommands());

        Comparator<Customer> byId = Comparator.comparingInt(Customer::getId);

        if (!customers.isEmpty()) {
            System.out.println("Choose a customer:");
            setCustomers(customers.stream().sorted(byId).collect(Collectors.toList()));
            for (int i = 0; i < customers.size(); i++) {
                System.out.println(i + 1 + ". " + customers.get(i));
            }
            System.out.println("0. Back");
            System.out.print("> ");
        } else {
            System.out.println("The customers list is empty!");
            System.out.println();
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
