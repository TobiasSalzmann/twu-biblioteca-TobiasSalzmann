package com.twu.biblioteca;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Library {

    public static Library createBookTestLibrary(){
        return new Library(Constants.books);
    }

    public static Library createMovieTestLibrary(){
        return new Library(Constants.books);
    }






    private final Map<String, LibraryItem> itemsByUID;

    private final Set<LibraryItem> availableItems;

    private final Set<LibraryItem> unavailableItems;

    public final List<Book> books = Arrays.asList(
            new Book("Book 1","Author 1", 1337, "0"),
            new Book("Book 2", "Author 2", 1976, "1")
    );

    public final List<Movie> movies = Arrays.asList(
            new Movie("Movie 1", 1984, "Director 1", "2"),
            new Movie("Movie 2", 1985, "Director 2", 7,"3")
    );


    public Library(){
        this(new LinkedList<>());
    }

    public Library(Collection<LibraryItem> items) {
        itemsByUID = new HashMap<>();
        items.forEach(item -> itemsByUID.put(item.getUID(), item));
        availableItems = new HashSet<>(items);
        unavailableItems = new HashSet<>();
    }

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


    List<LibraryItem> availableItems() {
        return new LinkedList<>(availableItems);
    }

    public synchronized void checkOutItem(LibraryItem item) {
        availableItems.remove(item);
        unavailableItems.add(item);
    }

    public List<LibraryItem> unavailableItems() {
        return new LinkedList<>(unavailableItems);
    }

    public synchronized void returnItem(LibraryItem item) {
        unavailableItems.remove(item);
        availableItems.add(item);
    }

    public Message tryCheckOut(String description) {
        if(itemsByUID.containsKey(description) && availableItems.contains(itemsByUID.get(description)))
            return Message.checkOutSuccessMessage(itemsByUID.get(description));
        else {
            List<LibraryItem> matches = Util.filter(availableItems, item -> item.match(description));
            if(matches.size() == 1)
                return Message.checkOutSuccessMessage(matches.get(0));
            else if (matches.size() > 1)
                return Message.notUniqueMessage(description);
            else
                return Message.noMatchesMessage(description);
        }


    }
}
