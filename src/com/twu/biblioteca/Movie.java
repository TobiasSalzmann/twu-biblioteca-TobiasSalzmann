package com.twu.biblioteca;

/**
 * Created by tsalzman on 1/8/16.
 */
public class Movie {
    private final String title;
    private final String director;
    private final int year;
    private final String rating;

    public boolean isAvailable() {
        return available;
    }

    public void checkOut() {
        this.available = false;
    }

    private boolean available = true;

    private Movie(String title, int year, String director, String rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public Movie(String title, int year, String director) {
        this(title, year, director, "unrated");
    }

    public Movie(String title, int year, String director, int rating) {
        this(title, year, director, "" + rating);
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
}
