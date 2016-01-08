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

    List<Book> availableBooks(){
        return Util.filter(books, Book::isAvailable);
    }

    List<Book> unavailableBooks(){
        return Util.filter(books, b -> !b.isAvailable());
    }



}
