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
        return new Library(Constants.movies);
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

    List<LibraryItem> availableItems() {
        return new LinkedList<>(availableItems);
    }

    public void checkOutItem(LibraryItem item) {
        availableItems.remove(item);
        unavailableItems.add(item);
    }

    public List<LibraryItem> unavailableItems() {
        return new LinkedList<>(unavailableItems);
    }

    public void returnItem(LibraryItem item) {
        unavailableItems.remove(item);
        availableItems.add(item);
    }

    public Message tryCheckOut(String description) {
        if(itemsByUID.containsKey(description) && availableItems.contains(itemsByUID.get(description))){
            checkOutItem(itemsByUID.get(description));
            return Message.checkOutSuccessMessage(itemsByUID.get(description));
        }

        return tryCheckOutByDescription(description);
    }

    private Message tryCheckOutByDescription(String description) {
        List<LibraryItem> matches = Util.filter(availableItems, item -> item.match(description));
        if(matches.size() == 1){
            checkOutItem(matches.get(0));
            return Message.checkOutSuccessMessage(matches.get(0));
        }
        else return reportFailure(description, matches);
    }

    private Message reportFailure(String description, List<LibraryItem> matches) {
        if (matches.size() > 1)
            return Message.notUniqueMessage(description);
        else
            return Message.noMatchesMessage(description);
    }


    public Message tryReturn(String description) {
        if(itemsByUID.containsKey(description) && unavailableItems.contains(itemsByUID.get(description))){
            checkOutItem(itemsByUID.get(description));
            return Message.returnSuccessMessage(itemsByUID.get(description));
        }

        return tryReturnByDescription(description);
    }

    private Message tryReturnByDescription(String description) {
        List<LibraryItem> matches = Util.filter(unavailableItems, item -> item.match(description));
        if(matches.size() == 1){
            checkOutItem(matches.get(0));
            return Message.returnSuccessMessage(matches.get(0));
        }
        else return reportFailure(description, matches);
    }

    public Message listAvailableItems() {
        return Message.itemListMessage(new LinkedList<>(availableItems));
    }
}
