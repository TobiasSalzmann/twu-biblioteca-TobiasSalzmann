package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tsalzman on 1/6/16.
 */
public class Constants {

    public static final String welcomeString = "Welcome to Biblioteca!";


    public static final String mainMenuString = "Main Menu: select an option";


    public static final String listBooksCommand = "books";
    public static final String listBooksDescription = "Show List of Books";


    public static final String invalidOptionString = "Select a valid option!";


    public static final String closeCommand = "quit";
    public static final String closeDescription = "Close current session";

    public static final String quitString = "Session Closed";


    public static final String checkoutBookCommand = "checkoutbook";
    public static final String checkoutBookDescription = "Checkout book";
    public static final String checkoutBookParamName = "<book>";

    public static final String checkoutSuccessString = "checked out successfully";
    public static final String checkoutFailureString = "Checkout failed";



    public static final String returnBookCommand = "returnbook";
    public static final String returnBookDescription = "Return book";
    public static final String returnBookParamName = "<book>";

    public static final String returnSuccessString = "returned successfully";
    public static final String returnFailureString = "Return failed";


    public static final String listMoviesCommand = "movies";
    public static final String listMoviesDescription = "Show List of Movies";

    public static final String checkoutMovieCommand = "checkoutmovie";
    public static final String checkoutMovieDescription = "Checkout movie";
    public static final String checkoutMovieParamName = "<movie>";

    public static final String returnMovieCommand = "returnmovie";
    public static final String returnMovieDescription = "Return movie";
    public static final String returnMovieParamName = "<movie>";

    public static final List<LibraryItem> books = Arrays.asList(
            new Book("Book 1","Author 1", 1337, "0"),
            new Book("Book 2", "Author 2", 1976, "1")
    );

    public static final List<LibraryItem> movies = Arrays.asList(
            new Movie("Movie 1", 1984, "Director 1", "2"),
            new Movie("Movie 2", 1985, "Director 2", 7,"3")
    );

    public static final String noMatchesString = "No matches for:";
    public static final String notUniqueString = "More than one match for:";
}
