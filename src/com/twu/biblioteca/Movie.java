package com.twu.biblioteca;

/**
 * Created by tsalzman on 1/8/16.
 */
public class Movie implements LibraryItem{
    private final String title;
    private final String director;
    private final int year;
    private final String rating;
    private final String uid;

    public boolean isAvailable() {
        return available;
    }

    public void checkOut() {
        this.available = false;
    }

    private boolean available = true;

    private Movie(String title, int year, String director, String rating, String uid) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.uid = uid;
    }

    public Movie(String title, int year, String director, String uid) {
        this(title, year, director, "unrated", uid);
    }

    public Movie(String title, int year, String director, int rating, String uid) {
        this(title, year, director, "" + rating, uid);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %s, %s",title, year, director, rating);
    }

    public void returnToLibrary() {
        this.available = true;
    }

    @Override
    public String getUID() {
        return uid;
    }

    @Override
    public boolean match(String description) {
        return false;
    }
}
