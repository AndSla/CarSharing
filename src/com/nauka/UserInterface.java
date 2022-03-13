package com.nauka;

import java.util.Scanner;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    private int menuLevel = 0;
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

    void showMenu() {
        switch (menuLevel) {
            case 0:
                showMainMenu();
                break;
            case 1:
                showManagerMenu();
        }
    }

    int getMenuItemFromInput() {
        while (true) {
            String chosenNumber = sc.nextLine();
            if (chosenNumber.matches("[0-" + maxMenuItemNumber + "]")) {
                System.out.println();
                // you can only use one-digit numbers for menu items: 0-9
                return menuLevel * 10 + Integer.parseInt(chosenNumber);
            } else {
                System.out.print("> ");
            }
        }
    }

    void exit() {
        setRunning(false);
        System.out.println("Bye!");
    }

    public void setMenuLevel(int menuLevel) {
        this.menuLevel = menuLevel;
    }

    public void setMaxMenuItemNumber(int maxMenuItemNumber) {
        this.maxMenuItemNumber = maxMenuItemNumber;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

}
