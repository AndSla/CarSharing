package com.nauka;

public class Main {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection(args);
        CompanyDAO companyDAO = new CompanyDAOImpl(dbConnection.getConnection());
        UserInterface ui = new UserInterface();
        Command chosenMenuItem;

        while (ui.isRunning()) {
            ui.showMenu();
            chosenMenuItem = ui.getMenuItemFromInput();

            switch (chosenMenuItem) {
                case LOGIN:
                    ui.setCurrentMenu(ui.getManagerMenu());
                    break;
                case COMPANY_LIST:
                    ui.showCompanyList(companyDAO.getAllCompanies());
                    break;
                case CREATE_COMPANY:
                    companyDAO.addCompany(ui.getCompanyFromInput());
                    break;
                case BACK:
                    ui.setCurrentMenu(ui.getMainMenu());
                    break;
                case EXIT:
                    ui.exit();
                    break;
            }

        }

    }

}
