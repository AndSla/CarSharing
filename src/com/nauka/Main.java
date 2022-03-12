package com.nauka;

public class Main {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection(args);
        UserInterface ui = new UserInterface();
        int chosenMenuItem;

        while (ui.isRunning()) {
            ui.showMainMenu();
            ui.setMenuItemFromInput(ui.getMaxMenuItemNumber());
            chosenMenuItem = ui.getMenuItem();

            switch (chosenMenuItem) {
                case 1:
                    ui.showManagerMenu();
                    ui.setMenuItemFromInput(ui.getMaxMenuItemNumber());
                    chosenMenuItem = ui.getMenuItem();

                case 0:
                    ui.exit();
            }

        }


    }

}
