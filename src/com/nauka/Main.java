package com.nauka;

public class Main {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection(args);
        UserInterface ui = new UserInterface();
        int chosenMenuItem;

        while (ui.isRunning()) {
            ui.showMenu();
            chosenMenuItem = ui.getMenuItemFromInput(ui.getMaxMenuItemNumber());

            switch (chosenMenuItem) {
                case 1:
                    ui.setMenuLevel(1);
                    break;
                case 0:
                    ui.exit();
                    break;
                case 11:
                    System.out.println("Showing company list");
                    System.out.println();
                    break;
                case 12:
                    System.out.println("Creating company");
                    System.out.println();
                    break;
                case 10:
                    ui.setMenuLevel(0);
                    break;
            }

        }

    }

}
