package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tsalzman on 1/8/16.
 */
public interface LibraryItem{

    String getUID();
    boolean match(String description);
    String getTitle();



    List<LibraryItem> books = Arrays.asList(
            new Book("Book 1","Author 1", 1337, "0"),
            new Book("Book 2", "Author 2", 1976, "1")
    );

    List<LibraryItem> movies = Arrays.asList(
            new Movie("Movie 1", 1984, "Director 1", "2"),
            new Movie("Movie 2", 1985, "Director 2", 7,"3")
    );
}
