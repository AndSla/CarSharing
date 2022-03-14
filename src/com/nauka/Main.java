package com.nauka;

public class Main {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection(args);
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
                    System.out.println("Showing company list");
                    System.out.println();
                    break;
                case CREATE_COMPANY:
                    System.out.println("Creating company");
                    System.out.println();
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
