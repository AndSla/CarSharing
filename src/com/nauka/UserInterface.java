package com.nauka;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    private final Menu mainMenu = new MainMenu();
    private final Menu managerMenu = new ManagerMenu();
    private final Menu companyMenu = new CompanyMenu();
    private final Menu companyListMenu = new CompanyListMenu();
    private Menu currentMenu = mainMenu;
    private int currentCompanyId;
    private boolean running = true;

    void showMenu() {
        currentMenu.showMenu();
    }

    Command getMenuItemFromInput() {
        while (true) {

            if (currentMenu instanceof CompanyListMenu) {

                CompanyListMenu clm = (CompanyListMenu) currentMenu;
                if (clm.getCompanies().isEmpty()) {
                    System.out.println();
                    return Command.BACK_TO_MANAGER_MENU;
                }

                String chosenNumber = sc.nextLine();
                if (chosenNumber.matches("0")) {
                    System.out.println();
                    return Command.BACK_TO_MANAGER_MENU;
                }
                if (chosenNumber.matches("\\d")) {
                    int listIndex = Integer.parseInt(chosenNumber) - 1;
                    currentCompanyId = clm.getCompanies().get(listIndex).getId();
                    System.out.println();
                    return Command.COMPANY_MENU;
                }

            } else {

                String chosenNumber = sc.nextLine();
                if (chosenNumber.matches("[0-" + currentMenu.getMaxMenuItemNumber() + "]")) {
                    System.out.println();
                    return currentMenu.getCommands().get(Integer.parseInt(chosenNumber));
                }

            }

            System.out.print("> ");

        }
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

    Car getCarFromInput() {
        System.out.println("Enter the car name:");
        System.out.print("> ");

        while (true) {
            String name = sc.nextLine();
            if (name.matches(".+")) {
                Car car = new Car();
                car.setName(name);
                car.setCompanyId(currentCompanyId);
                return car;
            } else {
                System.out.print("> ");
            }
        }

    }

    void showCarList(List<Car> cars) {
        Comparator<Car> byId = Comparator.comparingInt(Car::getId);

        if (!cars.isEmpty()) {
            System.out.println("Car list:");
            cars = cars.stream().sorted(byId).collect(Collectors.toList());
            for (int i = 0; i < cars.size(); i++) {
                System.out.println(i + 1 + ". " + cars.get(i));
            }
        } else {
            System.out.println("The car list is empty!");
        }

        System.out.println();

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

    public Menu getCompanyListMenu() {
        return companyListMenu;
    }

    public Menu getCompanyMenu() {
        return companyMenu;
    }

    public int getCurrentCompanyId() {
        return currentCompanyId;
    }

    public void setCompanyList(List<Company> companies) {
        CompanyListMenu companyListMenu = (CompanyListMenu) getCompanyListMenu();
        companyListMenu.setCompanies(companies);
    }

    public void setCurrentCompany(Company company) {
        CompanyMenu companyMenu = (CompanyMenu) getCompanyMenu();
        companyMenu.setCompanyName(company.getName());
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

}
