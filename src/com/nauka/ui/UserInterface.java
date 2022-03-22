package com.nauka.ui;

import com.nauka.dao.Car;
import com.nauka.dao.Company;
import com.nauka.dao.Customer;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    private final Menu mainMenu = new MainMenu();
    private final Menu managerMenu = new ManagerMenu();
    private final ListMenu<Company> companyListMenu = new CompanyListMenu();
    private final ListMenu<Customer> customerListMenu = new CustomerListMenu();
    private final Menu companyMenu = new CompanyMenu();
    private final Menu customerMenu = new CustomerMenu();
    private Menu currentMenu = mainMenu;
    private int currentCompanyId;
    private int currentCustomerId;
    private boolean running = true;

    public void showMenu() {
        currentMenu.showMenu();
    }

    public Command getMenuItemFromInput() {
        while (true) {

            if (currentMenu instanceof ListMenu) {
                if (((ListMenu<?>) currentMenu).getItems().isEmpty()) return currentMenu.getCommands().get(0);
            }

            String chosenNumber = sc.nextLine();
            if (chosenNumber.matches("[0-" + currentMenu.getMaxMenuItemNumber() + "]")) {

                System.out.println();

                if (currentMenu instanceof ListMenu && !chosenNumber.equals("0")) {

                    int listIndex = Integer.parseInt(chosenNumber) - 1;
                    Object item = ((ListMenu<?>) currentMenu).getItems().get(listIndex);

                    if (item instanceof Company) {
                        currentCompanyId = ((Company) item).getId();
                    }

                    if (item instanceof Customer) {
                        currentCustomerId = ((Customer) item).getId();
                    }

                }

                return currentMenu.getCommands().get(Integer.parseInt(chosenNumber));
            }

            System.out.print("> ");

        }
    }

    public Company getCompanyFromInput() {
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

    public Car getCarFromInput() {
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

    public Customer getCustomerFromInput() {
        System.out.println("Enter the customer name:");
        System.out.print("> ");

        while (true) {
            String name = sc.nextLine();
            if (name.matches(".+")) {
                Customer customer = new Customer();
                customer.setName(name);
                return customer;
            } else {
                System.out.print("> ");
            }
        }
    }

    public void showCarList(List<Car> cars) {
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

    public void exit() {
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

    public Menu getCustomerListMenu() {
        return customerListMenu;
    }

    public Menu getCustomerMenu() {
        return customerMenu;
    }

    public int getCurrentCompanyId() {
        return currentCompanyId;
    }

    public int getCurrentCustomerId() {
        return currentCustomerId;
    }

    public void setCompanyList(List<Company> companies) {
        companyListMenu.setItems(companies);
    }

    public void setCustomerList(List<Customer> customers) {
        customerListMenu.setItems(customers);
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
