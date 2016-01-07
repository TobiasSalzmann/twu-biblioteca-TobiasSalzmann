package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tsalzman on 1/6/16.
 */
public class Session {

    private PrintStream outStream = null;
    private Scanner scanner = null;
    private List<Command> commands = Arrays.asList(
            new Command(args -> listBooks(), Constants.listBooksCommand, Constants.listBooksDescription),
            new Command(args -> handleCheckout(args[0]), Constants.checkoutCommand,Constants.checkoutDescription, Constants.checkoutParamName),
            new Command(args -> handleReturn(args[0]), Constants.returnCommand, Constants.returnDescription, Constants.returnParamName),
            new Command(args -> closeSession(), Constants.closeCommand, Constants.closeDescription)
    );

    private Library library = new Library();

    public Session(){
        this(null,null);

    }

    private final LinkedList<Message> history = new LinkedList<>();

    public Session(InputStream in, PrintStream out) {
        if(in != null)
            scanner = new Scanner(in);
        if(out != null)
            outStream = out;
        history.add(Message.welcomeMessage());
        showMainMenu();

    }

    public String lastMessage() {
        return history.get(history.size() - 1).toString();
    }

    public List<String> history() {
        return history.stream().map(Object::toString).collect(Collectors.toList());
    }

    public void listBooks() {
        List<Book> availableBooks = library.availableBooks();
        writeMessage(Message.bookListMessage(availableBooks));
        showMainMenu();
    }

    private void writeMessage(Message message) {
        history.add(message);
        if(outStream != null)
            outStream.println(message);
    }

    private void showMainMenu(){
        writeMessage(Message.mainMenuMessage(commands));
        if(scanner != null){
            String input = scanner.nextLine();
            handleInput(input);
        }
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
        for (Book b: library.availableBooks()){
            if(s.trim().equals(b.getTitle())){
                b.checkOut();
                writeMessage(Message.checkOutSuccessMessage(b));
                showMainMenu();
                return;
            }
        }
        writeMessage(Message.checkOutFailureMessage());
        showMainMenu();

    }

    private void handleReturn(String s) {
        for (Book b: library.unavailableBooks()){
            if(s.trim().equals(b.getTitle())){
                b.returnToLibrary();
                writeMessage(Message.returnSuccessMessage(b));
                showMainMenu();
                return;
            }
        }
        writeMessage(Message.returnFailureMessage());
        showMainMenu();

    }

    private void closeSession(){
        writeMessage(Message.closeMessage());
    }

    private void handleInvalid() {
        writeMessage(Message.invalidMessage());
        showMainMenu();
    }


}
