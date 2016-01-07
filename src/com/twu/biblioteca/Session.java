package com.twu.biblioteca;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tsalzman on 1/6/16.
 */
public class Session {

    private List<Command> commands = Arrays.asList(
            new Command(args -> listBooks(), Constants.listBooksCommand, Constants.listBooksDescription),
            new Command(args -> handleCheckout(args[0]), Constants.checkoutCommand,Constants.checkoutDescription, Constants.checkoutParamName),
            new Command(args -> closeSession(), Constants.closeCommand, Constants.closeDescription)
    );

    private Library library = new Library();

    public Session(){
        history.add(Message.welcomeMessage());
        showMainMenu();

    }

    private final LinkedList<Message> history = new LinkedList<>();

    public String lastMessage() {
        return history.get(history.size() - 1).toString();
    }

    public List<String> history() {
        return history.stream().map(Object::toString).collect(Collectors.toList());
    }

    public void listBooks() {
        List<Book> availableBooks = library.booksFiltered(Book::isAvailable);
        writeMessage(Message.bookListMessage(availableBooks));
        showMainMenu();
    }

    private void writeMessage(Message message) {
        history.add(message);
    }

    private void showMainMenu(){
        writeMessage(Message.mainMenuMessage());
    }

    public void handleInput(String input) {
        int separatingIndex = input.indexOf(' ');
        String name = input;
        String[] args = {};

        if(separatingIndex >= 0) {
            name = input.substring(0, separatingIndex);
            args = new String[]{input.substring(separatingIndex)};//fails for longer argument lists, but doesn't matter for now
        }

        for(Command c :commands)
            if(c.getName().equals(name) && args.length == c.getNumArgs()){
                c.apply(args);
                return;
            }

        handleInvalid();
    }

    private void handleCheckout(String s) {
        for (Book b: library.books()){
            if(s.trim().equals(b.getTitle())){
                b.checkOut();
                writeMessage(Message.checkOutSuccessMessage(b));
                showMainMenu();
                return;
            }
        }
        writeMessage(Message.checkOutFailureMessage());

    }

    private void closeSession(){
        writeMessage(Message.closeMessage());
    }

    private void handleInvalid() {
        writeMessage(Message.invalidMessage());
        showMainMenu();
    }


}
