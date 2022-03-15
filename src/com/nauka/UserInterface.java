package com.nauka;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    private final Menu mainMenu = new MainMenu();
    private final Menu managerMenu = new ManagerMenu();
    private Menu currentMenu = mainMenu;
    private boolean running = true;

    void showMenu() {
        currentMenu.showMenu();
    }

    Command getMenuItemFromInput() {
        while (true) {
            String chosenNumber = sc.nextLine();
            if (chosenNumber.matches("[0-" + currentMenu.getMaxMenuItemNumber() + "]")) {
                System.out.println();
                return currentMenu.getCommands().get(Integer.parseInt(chosenNumber));
            } else {
                System.out.print("> ");
            }
        }
    }

    void showCompanyList(List<Company> companies) {
        Comparator<Company> byId = Comparator.comparingInt(Company::getId);

        if (!companies.isEmpty()) {
            System.out.println("Company list:");
            companies.stream().sorted(byId).forEach(System.out::println);
        } else {
            System.out.println("The company list is empty!");
        }
        System.out.println();
    }

    Company getCompanyFromInput() {
        System.out.println("Enter the company name:");
        System.out.print("> ");

        while (true) {
            String name = sc.nextLine();
            if (name.matches(".+")) {
                Company company = new Company();
                company.setName(name);
                return company;
            } else {
                System.out.print("> ");
            }
        }

    }

    void exit() {
        setRunning(false);
        System.out.println("Bye!");
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public Menu getManagerMenu() {
        return managerMenu;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

}
