package com.nauka;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    private final Menu mainMenu = new MainMenu();
    private final Menu managerMenu = new ManagerMenu();
    private final Menu companyListMenu = new CompanyListMenu();
    private final Menu companyMenu = new CompanyMenu();
    private Menu currentMenu = mainMenu;
    private int currentCompanyId;
    private boolean running = true;

    void showMenu() {
        currentMenu.showMenu();
    }

    Command getMenuItemFromInput() {
        while (true) {
            String chosenNumber = sc.nextLine();

            if (currentMenu instanceof CompanyListMenu) {

                if (chosenNumber.matches("\\d")) {
                    currentCompanyId = Integer.parseInt(chosenNumber);
                    System.out.println();
                    return Command.COMPANY_MENU;
                }

            } else {

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
