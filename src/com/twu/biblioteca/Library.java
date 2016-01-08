package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Library {



    private List<Book> books = Arrays.asList(
                new Book("Book 1","Author 1", 1337),
                new Book("Book 2", "Author 2", 1976)
        );
    private List<Movie> movies = Arrays.asList(
                new Movie("Movie 1", 1984, "Director 1"),
                new Movie("Movie 2", 1985, "Director 2", 7)
        );

    List<Book> availableBooks(){
        return Util.filter(books, Book::isAvailable);
    }

    List<Book> unavailableBooks(){
        return Util.filter(books, b -> !b.isAvailable());
    }


    List<Movie> availableMovies() {
        return Util.filter(movies, Movie::isAvailable);
    }

    List<Movie> unavailableMovies(){
        return Util.filter(movies, b -> !b.isAvailable());
    }
}
