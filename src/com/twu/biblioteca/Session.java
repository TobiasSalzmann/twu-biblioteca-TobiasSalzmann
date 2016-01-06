package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tsalzman on 1/6/16.
 */
public class Session {

    private final LinkedList<String> history = new LinkedList<>(Arrays.asList("Welcome to Biblioteca!"));
    private final LinkedList<String> books = new LinkedList<>(Arrays.asList("Book 1, Author 1, 1337", "Book 2, Author 2, 1976"));

    public String lastMessage() {
        return history.get(history.size() - 1);
    }

    public List<String> history() {
        return history;
    }

    public void listBooks() {
        for (String e: books)
            history.add(e);
    }

}
