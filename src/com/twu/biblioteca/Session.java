package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by tsalzman on 1/6/16.
 */
public class Session {

    private PrintStream outStream = null;
    private Scanner scanner = null;

    private Library books = Library.createBookTestLibrary();
    private Library movies = Library.createMovieTestLibrary();


    private List<Command> commands = Arrays.asList(
            new Command(args -> listItems(books), Constants.listBooksCommand, Constants.listBooksDescription),
            new Command(args -> handleCheckout(args[0], books), Constants.checkoutBookCommand,Constants.checkoutBookDescription, Constants.checkoutBookParamName),
            new Command(args -> handleReturn(args[0], books), Constants.returnBookCommand, Constants.returnBookDescription, Constants.returnBookParamName),
            new Command(args -> listItems(movies), Constants.listMoviesCommand, Constants.listMoviesDescription),
            new Command(args -> handleCheckout(args[0], movies), Constants.checkoutMovieCommand,Constants.checkoutMovieDescription, Constants.checkoutMovieParamName),
            new Command(args -> handleReturn(args[0], movies), Constants.returnMovieCommand, Constants.returnMovieDescription, Constants.returnMovieParamName),
            new Command(args -> closeSession(), Constants.closeCommand, Constants.closeDescription)
    );

    public static Session createTestSession() {
        return new Session(null, null);
    }


    private final LinkedList<Message> history = new LinkedList<>();

    public Session(InputStream in, PrintStream out) {
        if(in != null)
            scanner = new Scanner(in);
        if(out != null)
            outStream = out;
        writeMessage(Message.welcomeMessage());
        showMainMenu();

    }

    public String lastMessage() {
        return history.get(history.size() - 1).toString();
    }

    public List<String> history() {
        return Util.map(history, Object::toString);
    }

    public void listItems(Library library) {
        writeMessage(library.listAvailableItems());
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
        String name = parseName(input);
        String[] args = parseArgs(input);
        for(Command c :commands)
            if(c.getName().equals(name) && args.length == c.getNumArgs()){
                c.apply(args);
                return;
            }

        handleInvalid();
    }

    private String[] parseArgs(String input) {
        int index = input.trim().indexOf(' ');
        if(index >= 0)
            return new String[]{input.substring(index, input.length())};
        else
            return new String[]{};

    }

    private String parseName(String input) {
        return input.split(" ")[0];

    }

    private void handleCheckout(String description, Library library){
        Message message = library.tryCheckOut(description.trim());
        writeMessage(message);
        showMainMenu();
    }

    private void handleReturn(String description, Library library){
        Message message = library.tryReturn(description.trim());
        writeMessage(message);
        showMainMenu();
    }


    private void closeSession(){
        writeMessage(Message.closeMessage());
    }

    private void handleInvalid() {
        writeMessage(Message.invalidMessage());
        showMainMenu();
    }


    public String history(int i) {
            return history.get(i < 0 ? history.size() + i : i).toString();

    }
}
