package com.twu.biblioteca;

import java.util.regex.Pattern;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Book implements LibraryItem{
    private final String title;
    private final String author;
    private final int year;
    private final String uid;

    public Book(String title, String author, int year, String uid) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s",title, author, year, uid);
    }

    @Override
    public String getUID() {
        return uid;
    }

    @Override
    public boolean match(String description) {
        return Util.simplify(String.format("%s %s %d %s",title, author, year, uid)).startsWith(Util.simplify(description));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        return uid.equals(book.uid);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + year;
        result = 31 * result + uid.hashCode();
        return result;
    }
}
