package com.wizmacau.androidrecipes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llitfkitfk on 6/19/15.
 */
public class Feed implements Serializable {
    public static final Feed NONE = new Feed();

    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}