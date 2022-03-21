package com.nauka.ui;

import java.util.Map;

public abstract class Menu {

    protected Map<Integer, Command> commands;
    protected int maxMenuItemNumber;

    abstract void showMenu();

    public Map<Integer, Command> getCommands() {
        return commands;
    }

    public void setCommands(Map<Integer, Command> commands) {
        this.commands = commands;
    }

    public int getMaxMenuItemNumber() {
        return maxMenuItemNumber;
    }

    public void setMaxMenuItemNumber(int maxMenuItemNumber) {
        this.maxMenuItemNumber = maxMenuItemNumber;
    }
}
