package com.twu.biblioteca;

import java.util.*;
import java.util.function.Function;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Library {

    public static Library createBookTestLibrary(){
        return new Library(LibraryItem.books);
    }

    public static Library createMovieTestLibrary(){
        return new Library(LibraryItem.movies);
    }







    private final Map<String, LibraryItem> itemsByUID;

    private final Set<LibraryItem> availableItems;

    private final HashMap<String, Set<LibraryItem>> unavailableItems;



    public Library(Collection<LibraryItem> items) {
        itemsByUID = new HashMap<>();
        items.forEach(item -> itemsByUID.put(item.getUID(), item));
        availableItems = new HashSet<>(items);
        unavailableItems = new HashMap<>();
    }

    List<LibraryItem> availableItems() {
        return new LinkedList<>(availableItems);
    }

    public void moveItem(LibraryItem item, Set<LibraryItem> from, Set<LibraryItem> to){
        from.remove(item);
        to.add(item);
    }

    public Message tryMove(String description, Set<LibraryItem> from, Set<LibraryItem> to, Function<LibraryItem, Message> successMessageGen){
        if(itemsByUID.containsKey(description) && from.contains(itemsByUID.get(description))){
            moveItem(itemsByUID.get(description), from, to);
            return successMessageGen.apply(itemsByUID.get(description));
        }

        return tryMoveByDescription(description, from, to, successMessageGen);
    }

    private Message tryMoveByDescription(String description, Set<LibraryItem> from, Set<LibraryItem> to, Function<LibraryItem, Message> successMessageGen) {
        List<LibraryItem> matches = Util.filter(from, item -> item.match(description));
        if(matches.size() == 1){
            moveItem(matches.get(0), from, to);
            return successMessageGen.apply(matches.get(0));
        }
        else return reportFailure(description, matches);
    }

    private Message reportFailure(String description, List<LibraryItem> matches) {
        if (matches.size() > 1)
            return Message.notUniqueMessage(description);
        else
            return Message.noMatchesMessage(description);
    }

    public List<LibraryItem> unavailableItems(String user) {
        return new LinkedList<>(unavailableItems.get(user));
    }

    public void returnItem(LibraryItem item) {
        unavailableItems.remove(item);
        availableItems.add(item);
    }

    public Message tryCheckOut(String description, String user) {
        unavailableItems.putIfAbsent(user, new HashSet<>());
        return tryMove(description, availableItems, unavailableItems.get(user), Message::checkOutSuccessMessage);
    }

    public Message tryReturn(String description, String user) {
        unavailableItems.putIfAbsent(user, new HashSet<>());
        return tryMove(description, unavailableItems.get(user), availableItems, Message::returnSuccessMessage);
    }

    public Message listAvailableItems() {
        return Message.itemListMessage(new LinkedList<>(availableItems));
    }


}
