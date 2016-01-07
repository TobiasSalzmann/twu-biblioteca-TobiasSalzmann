package com.twu.biblioteca;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Book{
    private final String title;
    private final String author;
    private final int year;

    public boolean isAvailable() {
        return available;
    }

    public void checkOut() {
        this.available = false;
    }

    private boolean available = true;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d",title, author, year);
    }

    public void returnToLibrary() {
        this.available = true;
    }
}
