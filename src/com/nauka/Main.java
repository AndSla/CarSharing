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
                case MANAGER_MENU:
                    ui.setCurrentMenu(ui.getManagerMenu());
                    break;
                case CUSTOMER_LIST_MENU:
                    ui.setCustomerList(customerDAO.getAllCustomers());
                    ui.setCurrentMenu(ui.getCustomerListMenu());
                    break;
                case CUSTOMER_MENU:
                    Customer customer = customerDAO.getCustomerById(ui.getCurrentCustomer().getId());
                    if (customer != null) {
                        ui.setCurrentMenu(ui.getCustomerMenu());
                    } else {
                        ui.setCurrentMenu(ui.getCustomerListMenu());
                    }
                    break;
                case COMPANY_LIST_MENU:
                    ui.setBackCommandInCompanyListMenu(Command.MANAGER_MENU, Command.COMPANY_MENU);
                    ui.setCompanyList(companyDAO.getAllCompanies());
                    ui.setCurrentMenu(ui.getCompanyListMenu());
                    break;
                case COMPANY_CREATE:
                    companyDAO.addCompany(ui.getCompanyFromInput());
                    break;
                case COMPANY_MENU:
                    Company company = companyDAO.getCompanyById(ui.getCurrentCompany().getId());
                    if (company != null) {
                        ui.setCurrentCompany(company);
                        ui.setCurrentMenu(ui.getCompanyMenu());
                    } else {
                        ui.setCurrentMenu(ui.getCompanyListMenu());
                    }
                    break;
                case CAR_CREATE:
                    carDAO.addCar(ui.getCarFromInput());
                    break;
                case CAR_LIST:
                    ui.showCarList(carDAO.getAllCompanyCars(ui.getCurrentCompany().getId()));
                    break;
                case CAR_LIST_MENU:
                    ui.setCarList(carDAO.getAllCompanyCars(ui.getCurrentCompany().getId()));
                    ui.setCurrentMenu(ui.getCarListMenu());
                    break;
                case CAR_RENT_CHOOSE_COMPANY:
                    ui.setBackCommandInCompanyListMenu(Command.CUSTOMER_MENU, Command.CAR_LIST_MENU);
                    ui.setCompanyList(companyDAO.getAllCompanies());
                    ui.setCurrentMenu(ui.getCompanyListMenu());
                    break;
                case CAR_RENT:
                    customerDAO.rentACar(ui.getCurrentCustomer(), ui.getCurrentCar());
                    ui.setCurrentMenu(ui.getCustomerMenu());
                    break;
                case CUSTOMER_CREATE:
                    customerDAO.addCustomer(ui.getCustomerFromInput());
                    break;
                case MAIN_MENU:
                    ui.setCurrentMenu(ui.getMainMenu());
                    break;
                case EXIT:
                    ui.exit();
                    break;
            }

        }

    }

}
