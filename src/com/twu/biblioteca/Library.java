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

    List<Book> books(){
        return booksFiltered(e -> true);
    }

    List<Book> booksFiltered(Predicate<Book> p){
        return Util.filterList(books, p);
    }


}
