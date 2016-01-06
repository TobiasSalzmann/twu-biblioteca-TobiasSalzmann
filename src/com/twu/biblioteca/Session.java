package com.twu.biblioteca;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tsalzman on 1/6/16.
 */
public class Session {

    public Session(){
        history.add(Message.welcomeMessage());
        history.add(Message.mainMenuMessage());
    }

    private final LinkedList<Message> history = new LinkedList<>();
    //private final LinkedList<String> books = new LinkedList<>(Arrays.asList("Book 1, Author 1, 1337", "Book 2, Author 2, 1976"));

    public String lastMessage() {
        return history.get(history.size() - 1).toString();
    }

    public List<String> history() {
        return history.stream().map(Object::toString).collect(Collectors.toList());
    }

    public void listBooks() {
        history.add(Message.bookListMessage());
    }


}
