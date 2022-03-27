package com.nauka;

import com.nauka.dao.*;
import com.nauka.ui.Command;
import com.nauka.ui.UserInterface;

public class Main {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection(args);
        CompanyDAO companyDAO = new CompanyDAOImpl(dbConnection.getConnection());
        CarDAO carDAO = new CarDAOImpl(dbConnection.getConnection());
        CustomerDAO customerDAO = new CustomerDAOImpl(dbConnection.getConnection());
        UserInterface ui = new UserInterface();
        Command chosenMenuItem;

        while (ui.isRunning()) {
            ui.showMenu();
            chosenMenuItem = ui.getMenuItemFromInput();

            switch (chosenMenuItem) {
                case MAIN_MENU:
                    ui.setCurrentMenu(ui.getMainMenu());
                    break;
                case MANAGER_MENU:
                    ui.setCurrentMenu(ui.getManagerMenu());
                    break;
                case COMPANY_LIST_MENU:
                    ui.setBackAndActionCommandInCompanyListMenu(Command.MANAGER_MENU, Command.COMPANY_MENU);
                    ui.setCompanyList(companyDAO.getAllCompanies());
                    ui.setCurrentMenu(ui.getCompanyListMenu());
                    break;
                case COMPANY_MENU:
                    ui.setCurrentMenu(ui.getCompanyMenu());
                    break;
                case COMPANY_CREATE:
                    companyDAO.addCompany(ui.getCompanyFromInput());
                    break;
                case CUSTOMER_LIST_MENU:
                    ui.setCustomerList(customerDAO.getAllCustomers());
                    ui.setCurrentMenu(ui.getCustomerListMenu());
                    break;
                case CUSTOMER_MENU:
                    ui.setCurrentMenu(ui.getCustomerMenu());
                    break;
                case CUSTOMER_CREATE:
                    customerDAO.addCustomer(ui.getCustomerFromInput());
                    break;
                case CAR_CREATE:
                    carDAO.addCar(ui.getCarFromInput());
                    break;
                case CAR_LIST:
                    ui.showCarList(carDAO.getAllCompanyCars(ui.getCurrentCompany().getId()));
                    break;
                case CAR_LIST_MENU:
                    ui.setCompanyInCarListMenu(ui.getCurrentCompany());
                    ui.setCarList(carDAO.getAllCompanyCars(ui.getCurrentCompany().getId()));
                    ui.setCurrentMenu(ui.getCarListMenu());
                    break;
                case CAR_RENT_CHOOSE_COMPANY:
                    if (!customerDAO.hasRentedCar(ui.getCurrentCustomer())) {
                        ui.setBackAndActionCommandInCompanyListMenu(Command.CUSTOMER_MENU, Command.CAR_LIST_MENU);
                        ui.setCompanyList(companyDAO.getAllCompanies());
                        ui.setCurrentMenu(ui.getCompanyListMenu());
                    }
                    break;
                case CAR_RENT:
                    customerDAO.rentACar(ui.getCurrentCustomer(), ui.getCurrentCar());
                    ui.setCarList(carDAO.getAllCompanyCars(ui.getCurrentCompany().getId()));
                    ui.setCurrentMenu(ui.getCustomerMenu());
                    Customer customer = customerDAO.getCustomerById(ui.getCurrentCustomer().getId());
                    ui.updateCurrentCustomer(customer);
                    break;
                case CAR_RETURN:
                    customerDAO.returnCar(ui.getCurrentCustomer());
                    customer = customerDAO.getCustomerById(ui.getCurrentCustomer().getId());
                    ui.updateCurrentCustomer(customer);
                    break;
                case CAR_RENTED:
                    Integer rentedCarId = ui.getCurrentCustomer().getRentedCarId();
                    if (rentedCarId != null) {
                        Car car = carDAO.getCarById(rentedCarId);
                        System.out.println("Your rented car:");
                        System.out.println(car.getName());
                        Company company = companyDAO.getCompanyById(car.getCompanyId());
                        System.out.println("Company:");
                        System.out.println(company.getName() + "\n");
                    } else {
                        System.out.println("You didn't rent a car!\n");
                    }
                    break;
                case EXIT:
                    ui.exit();
                    break;
            }

        }

    }

}
