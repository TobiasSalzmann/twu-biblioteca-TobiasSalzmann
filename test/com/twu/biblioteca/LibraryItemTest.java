package com.twu.biblioteca;

import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by tsalzman on 1/8/16.
 */
public class LibraryItemTest{

    private final Book book = new Book("Title1","Author",0,"someUniqueIdentifier");
    private final Movie movie = new Movie("Title2",0,"Director",7,"someOtherUniqueIdentifier");

    @Test
    public void bookTypeTest(){
        assertThat(book, instanceOf(LibraryItem.class));
    }

    @Test
    public void movieTypeTest(){
        assertThat(movie, instanceOf(LibraryItem.class));
    }

    @Test
    public void UIDTest(){
        assertThat(book.getUID(), is("someUniqueIdentifier"));
        assertThat(movie.getUID(), is("someOtherUniqueIdentifier"));
    }

    @Test
    public void matchBookTestTrue(){
        LibraryItem item = new Book("Book 1", "Author 1", 1900, "7");
        assertTrue(item.match(""));
        assertTrue(item.match("B"));
        assertTrue(item.match("Book 1"));
        assertTrue(item.match("book 1"));
        assertTrue(item.match("BOOK 1"));
        assertTrue(item.match("book 1 author 1 1900 7"));
        assertTrue(item.match("book 1  \n     author 1"));
    }

    @Test
    public void matchBookTestFalse(){
        LibraryItem item = new Book("Book 1", "Author 1", 1900, "7");
        assertFalse(item.match("C"));
        assertFalse(item.match("Book 2"));
        assertFalse(item.match("boook 1"));
        assertFalse(item.match("book 1 author 1 1900 8"));
        assertFalse(item.match("book 1  \n hello    author 1"));
    }

    @Test
    public void matchMovieTestTrue(){
        LibraryItem item = new Movie("Movie 1", 1900, "Director 1", "7");
        assertTrue(item.match(""));
        assertTrue(item.match("M"));
        assertTrue(item.match("Movie 1"));
        assertTrue(item.match("movie 1"));
        assertTrue(item.match("MOVIE 1"));
        assertTrue(item.match("movie 1 1900 director 1 unrated 7"));
        assertTrue(item.match("movie 1  \n     190"));
    }

    @Test
    public void matchMovieTestFalse(){
        LibraryItem item = new Movie("Movie 1", 1900, "Director 1", "7");
        assertFalse(item.match("C"));
        assertFalse(item.match("Movie 2"));
        assertFalse(item.match("moovie 1"));
        assertFalse(item.match("movie 1 1900 director 1 unrated 8"));
        assertFalse(item.match("movie 1  \n hello    author 1"));
    }






}
