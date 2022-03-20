package com.nauka;

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
                case LOGIN_AS_MANAGER:
                case BACK_TO_MANAGER_MENU:
                    ui.setCurrentMenu(ui.getManagerMenu());
                    break;
                case LOGIN_AS_CUSTOMER:
                    ui.setCustomerList(customerDAO.getAllCustomers());
                    ui.setCurrentMenu(ui.getCustomerListMenu());
                    break;
                case CUSTOMER_MENU:
                    Customer customer = customerDAO.getCustomerById(ui.getCurrentCustomerId());
                    if (customer != null) {
                        ui.setCurrentMenu(ui.getCustomerMenu());
                    } else {
                        ui.setCurrentMenu(ui.getCustomerListMenu());
                    }
                    break;
                case COMPANY_LIST:
                    ui.setCompanyList(companyDAO.getAllCompanies());
                    ui.setCurrentMenu(ui.getCompanyListMenu());
                    break;
                case CREATE_COMPANY:
                    companyDAO.addCompany(ui.getCompanyFromInput());
                    break;
                case COMPANY_MENU:
                    Company company = companyDAO.getCompanyById(ui.getCurrentCompanyId());
                    if (company != null) {
                        ui.setCurrentCompany(company);
                        ui.setCurrentMenu(ui.getCompanyMenu());
                    } else {
                        ui.setCurrentMenu(ui.getCompanyListMenu());
                    }
                    break;
                case CREATE_CAR:
                    carDAO.addCar(ui.getCarFromInput());
                    break;
                case CAR_LIST:
                    ui.showCarList(carDAO.getAllCompanyCars(ui.getCurrentCompanyId()));
                    break;
                case CREATE_CUSTOMER:
                    customerDAO.addCustomer(ui.getCustomerFromInput());
                    break;
                case BACK_TO_MAIN_MENU:
                    ui.setCurrentMenu(ui.getMainMenu());
                    break;
                case EXIT:
                    ui.exit();
                    break;
            }

        }

    }

}
