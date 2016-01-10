package com.twu.biblioteca;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
/**
 * Created by tsalzman on 1/8/16.
 */
public class LibraryTest {

    private final List<LibraryItem> books = Arrays.asList(
            new Book("Book 1","Author 1", 1337, "0"),
            new Book("Book 2", "Author 2", 1976, "1")
    );

    private final List<LibraryItem> movies = Arrays.asList(
            new Movie("Movie 1", 1984, "Director 1", "2"),
            new Movie("Movie 2", 1985, "Director 2", 7, "3")
    );

    /*
    public void bookLibraryAvailableTest(){
        Collection<LibraryItem> items = books;
        Library lib = new Library(items);
        assertThat(lib.availableItems(), hasItems(items));
        assertThat(items, hasItems(lib.availableItems()));
    }
    */

}
