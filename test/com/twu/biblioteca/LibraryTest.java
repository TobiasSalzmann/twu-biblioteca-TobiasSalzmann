package com.twu.biblioteca;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void libraryAvailableTest(){
        LibraryItem item1 = new Book("Book 1","Author 1", 1337, "0");
        LibraryItem item2 = new Book("Book 2", "Author 2", 1976, "1");
        Collection<LibraryItem> items = Arrays.asList(item1, item2);
        Library lib = new Library(items);
        assertThat(new HashSet<>(lib.availableItems()), is(new HashSet<>(items)));
    }

    @Test
    public void libraryRemovalTest(){
        LibraryItem item1 = new Book("Book 1","Author 1", 1337, "0");
        LibraryItem item2 = new Book("Book 2", "Author 2", 1976, "1");
        Collection<LibraryItem> items = Arrays.asList(item1, item2);
        Library lib = new Library(items);
        lib.checkOutItem(item1);
        assertThat(new HashSet<>(lib.availableItems()), is(new HashSet<>(Collections.singletonList(item2))));
        assertThat(new HashSet<>(lib.unavailableItems()), is(new HashSet<>(Collections.singletonList(item1))));
    }

    @Test
    public void libraryReturnTest(){
        LibraryItem item1 = new Book("Book 1","Author 1", 1337, "0");
        LibraryItem item2 = new Book("Book 2", "Author 2", 1976, "1");
        Collection<LibraryItem> items = Arrays.asList(item1, item2);
        Library lib = new Library(items);
        lib.checkOutItem(item1);
        lib.returnItem(item1);
        assertThat(new HashSet<>(lib.availableItems()), is(new HashSet<>(items)));
    }

    @Test
    public void libraryCheckoutByUIDTestSuccess(){
        Library lib = Library.createBookTestLibrary();
        Message m = lib.tryCheckOut("0");
        assertThat(m.toString(), is("Book 1 " + Constants.checkoutSuccessString));
    }

    @Test
    public void libraryCheckoutByUIDTestFailure1(){
        Library lib = Library.createBookTestLibrary();
        Message m = lib.tryCheckOut("67");
        assertThat(m.toString(), is(Constants.noMatchesString + " 67"));
    }

    @Test
    public void libraryCheckoutByUIDTestFailure2(){
        Library lib = Library.createBookTestLibrary();
        lib.checkOutItem(books.get(0));
        Message m = lib.tryCheckOut("0");
        assertThat(m.toString(), is(Constants.noMatchesString + " 0"));
    }

    @Test
    public void libraryCheckoutByDescriptionTestSuccess(){
        Library lib = Library.createBookTestLibrary();
        Message m = lib.tryCheckOut("Book 1");
        assertThat(m.toString(), is("Book 1 " + Constants.checkoutSuccessString));
    }

    @Test
    public void libraryCheckoutByDescriptionTestFailure1(){
        Library lib = Library.createBookTestLibrary();
        Message m = lib.tryCheckOut("Book 4");
        assertThat(m.toString(), is(Constants.noMatchesString + " Book 4"));
    }

    @Test
    public void libraryCheckoutByDescriptionTestFailure2(){
        Library lib = Library.createBookTestLibrary();
        Message m = lib.tryCheckOut("Book");
        assertThat(m.toString(), is(Constants.notUniqueString + " Book"));
    }

    @Test
    public void libraryCheckoutByDescriptionTestFailure3(){
        Library lib = Library.createBookTestLibrary();
        lib.checkOutItem(books.get(0));
        Message m = lib.tryCheckOut("Book 1");
        assertThat(m.toString(), is(Constants.noMatchesString + " Book 1"));
    }







}
