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
        return Util.simplify(String.format("%s %d %s %s %s",title, year, director, rating, uid)).startsWith(Util.simplify(description));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (year != movie.year) return false;
        if (!title.equals(movie.title)) return false;
        if (!director.equals(movie.director)) return false;
        if (!rating.equals(movie.rating)) return false;
        return uid.equals(movie.uid);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + year;
        result = 31 * result + rating.hashCode();
        result = 31 * result + uid.hashCode();
        return result;
    }
}
