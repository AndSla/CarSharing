package com.nauka.ui;

import java.util.ArrayList;
import java.util.List;

public abstract class ListMenu<T> extends Menu {

    List<T> items = new ArrayList<>();

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
