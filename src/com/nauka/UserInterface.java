package com.nauka;

import java.util.Scanner;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    private int menuItem;
    private int maxMenuItemNumber;
    private boolean running = true;

    void showMainMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
        System.out.print("> ");
        setMaxMenuItemNumber(1);
    }

    void showManagerMenu() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        System.out.print("> ");
        setMaxMenuItemNumber(2);
    }

    void setMenuItemFromInput(int maxMenuItemNumber) {
        while (true) {
            String chosenNumber = sc.nextLine();
            if (chosenNumber.matches("[0-" + maxMenuItemNumber + "]")) {
                setMenuItem(Integer.parseInt(chosenNumber));
                break;
            } else {
                System.out.print("> ");
            }
        }
    }

    void exit() {
        setRunning(false);
        System.out.println("Bye!");
    }

    public void setMenuItem(int menuItem) {
        this.menuItem = menuItem;
    }

    public int getMenuItem() {
        return menuItem;
    }

    public void setMaxMenuItemNumber(int maxMenuItemNumber) {
        this.maxMenuItemNumber = maxMenuItemNumber;
    }

    public int getMaxMenuItemNumber() {
        return maxMenuItemNumber;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

}
