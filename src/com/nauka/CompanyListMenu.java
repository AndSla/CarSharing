package com.nauka;

import java.util.*;
import java.util.stream.Collectors;

public class CompanyListMenu extends Menu {

    private List<Company> companies;

    public CompanyListMenu() {
    }

    public CompanyListMenu(List<Company> companies) {
        this.companies = companies;
        setMaxMenuItemNumber(companies.size());
        setCommands(mapCommands());
    }

    private Map<Integer, Command> mapCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(0, Command.EXIT);
        return commands;
    }

    @Override
    void showMenu() {
        Comparator<Company> byId = Comparator.comparingInt(Company::getId);

        if (!companies.isEmpty()) {
            System.out.println("Choose a company:");
            setCompanies(companies.stream().sorted(byId).collect(Collectors.toList()));
            for (int i = 0; i < companies.size(); i++) {
                System.out.println(i + 1 + ". " + companies.get(i));
            }
            System.out.println("0. Back");
            System.out.print("> ");
        } else {
            System.out.println("The company list is empty!");
            System.out.println();
        }
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

}
