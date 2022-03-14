package com.nauka;

import java.util.Scanner;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    private final Menu mainMenu = new MainMenu();
    private final Menu managerMenu = new ManagerMenu();
    private Menu currentMenu = mainMenu;
    private boolean running = true;

    void showMenu() {
        currentMenu.showMenu();
    }

    Command getMenuItemFromInput() {
        while (true) {
            String chosenNumber = sc.nextLine();
            if (chosenNumber.matches("[0-" + currentMenu.getMaxMenuItemNumber() + "]")) {
                System.out.println();
                return currentMenu.getCommands().get(Integer.parseInt(chosenNumber));
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

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

}
