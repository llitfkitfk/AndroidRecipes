package com.wizmacau.androidrecipes.model;

import android.util.SparseArray;

/**
 * Created by llitfkitfk on 6/19/15.
 */

public class Article {
    private final String title;
    private final SparseArray<Item> parts;

    public static Article newInstance(String title) {
        SparseArray<Item> parts = new SparseArray<>();
        return new Article(title, parts);
    }

    public Article(String title, SparseArray<Item> parts) {
        this.title = title;
        this.parts = parts;
    }

    public String getTitle() {
        return title;
    }

    public void addPart(int partNumber, Item part) {
        parts.put(partNumber, part);
    }

    public Item getPart(int partNumber) {
        return parts.get(partNumber);
    }
}
