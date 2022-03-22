package com.nauka.ui;

import com.nauka.dao.Car;
import com.nauka.dao.Company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CarListMenu extends ListMenu<Car> {

    private Company company;

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(0, Command.CAR_RENT_CHOOSE_COMPANY);
        for (int i = 0; i < getItems().size(); i++) {
            commands.put(i + 1, Command.CAR_RENT);
        }
        return commands;
    }

    @Override
    void showMenu() {
        setMaxMenuItemNumber(getItems().size());
        setCommands(mapCommands());

        Comparator<Car> byId = Comparator.comparingInt(Car::getId);

        if (!getItems().isEmpty()) {
            System.out.println("Choose a car:");
            setItems(getItems().stream().sorted(byId).collect(Collectors.toList()));
            for (int i = 0; i < getItems().size(); i++) {
                System.out.println(i + 1 + ". " + getItems().get(i));
            }
            System.out.println("0. Back");
            System.out.print("> ");
        } else {
            System.out.println("No available cars in the '" + company.getName() + "' company");
            System.out.println();
        }
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}


