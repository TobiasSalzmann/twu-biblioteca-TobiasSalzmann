package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Library {



    private List<Book> books = Arrays.asList(
                new Book("Book 1","Author 1", 1337),
                new Book("Book 2", "Author 2", 1976)
        );

    List<Book> availableBooks(){
        return Util.filterList(books, Book::isAvailable);
    }

    List<Book> unavailableBooks(){
        return Util.filterList(books, b -> !b.isAvailable());
    }



}
