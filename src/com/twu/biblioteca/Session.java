package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import static com.twu.biblioteca.Constants.*;
/**
 * Created by tsalzman on 1/6/16.
 */
public class Session {

    private List<Command> commands;
    private final PrintStream outStream;
    private final Scanner scanner;


    public static Session createTestSession(){
        return createSimpleSession(Library.createBookTestLibrary(), Library.createMovieTestLibrary(), null, null);
    }

    public static Session createSimpleSession(Library books, Library movies, InputStream in, PrintStream out) {
        Function<Session, List<Command>> commandFun = session -> Arrays.asList(
                Command.createCommand(args -> session.listItems(books, bookHeaderString), listBooksCommand, listBooksDescription),
                Command.createCommand(args -> session.handleCheckout(args[0], books, "user"), checkoutBookCommand,checkoutBookDescription, checkoutBookParamName),
                Command.createCommand(args -> session.handleReturn(args[0], books, "user"), returnBookCommand, returnBookDescription, returnBookParamName),
                Command.createCommand(args -> session.listItems(movies, movieHeaderString), listMoviesCommand, listMoviesDescription),
                Command.createCommand(args -> session.handleCheckout(args[0], movies, "user"), checkoutMovieCommand,checkoutMovieDescription, checkoutMovieParamName),
                Command.createCommand(args -> session.handleReturn(args[0], movies, "user"), returnMovieCommand, returnMovieDescription, returnMovieParamName),
                Command.createCommand(args -> session.closeSession(), closeCommand, closeDescription)
        );
        return new Session(commandFun, in, out);
    }








    private final LinkedList<Message> history = new LinkedList<>();
    public Session(Function<Session, List<Command>> commandFun, InputStream in, PrintStream out) {
        commands = commandFun.apply(this);
        scanner = (in == null) ? null : new Scanner(in);
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

    public void listItems(Library library, String headerString) {
        writeMessage(Message.addPrefix(library.listAvailableItems(), headerString+"\n" ));
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
                c.accept(args);
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

    private void handleCheckout(String description, Library library, String user){
        Message message = library.tryCheckOut(description.trim(), user);
        writeMessage(message);
        showMainMenu();
    }

    private void handleReturn(String description, Library library, String user){
        Message message = library.tryReturn(description.trim(), user);
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



    public static Session createUserTestSession(){
        return createUserSession(Library.createBookTestLibrary(), Library.createMovieTestLibrary(), UserDB.createTestDB(), null, null);
    }


    public static Session createUserSession(Library books, Library movies, UserDB db, InputStream in, PrintStream out) {


        BiFunction<Session, String, List<Command>> commandFunFull = (session, user) -> Arrays.asList(
                Command.createCommand(args -> session.listItems(books, bookHeaderString), listBooksCommand, listBooksDescription),
                Command.createCommand(args -> session.handleCheckout(args[0], books, user), checkoutBookCommand,checkoutBookDescription, checkoutBookParamName),
                Command.createCommand(args -> session.handleReturn(args[0], books, user), returnBookCommand, returnBookDescription, returnBookParamName),
                Command.createCommand(args -> session.listItems(movies, movieHeaderString), listMoviesCommand, listMoviesDescription),
                Command.createCommand(args -> session.handleCheckout(args[0], movies, user), checkoutMovieCommand,checkoutMovieDescription, checkoutMovieParamName),
                Command.createCommand(args -> session.handleReturn(args[0], movies, user), returnMovieCommand, returnMovieDescription, returnMovieParamName),
                Command.createCommand(args -> session.handleUserInfo(user, db), userInfoCommand, userInfoDescription),
                Command.createCommand(args -> session.closeSession(), closeCommand, closeDescription)
        );

        Function<Session, List<Command>> commandFun = session -> Arrays.asList(
                Command.createCommand(args -> session.listItems(books, bookHeaderString), listBooksCommand, listBooksDescription),
                Command.createCommand(args -> session.listItems(movies, movieHeaderString), listMoviesCommand, listMoviesDescription),
                Command.createCommand(args -> session.closeSession(), closeCommand, closeDescription),
                Command.createCommand(args -> session.loginUser(args[0], db, commandFunFull), loginCommand, loginDescription, loginParamName)
        );


        return new Session(commandFun, in, out);
    }

    private void handleUserInfo(String user, UserDB db) {
        writeMessage(db.getUserInfo(user));
        showMainMenu();
    }

    private void loginUser(String arg, UserDB users, BiFunction<Session, String, List<Command>> commandFunFull) {
        if (!arg.contains(" "))
            writeMessage(Message.loginFailureMessage());
        String[] credentials = arg.trim().split(" ");
        if (users.validate(credentials[0], credentials[1])) {
            commands = commandFunFull.apply(this, credentials[0]);
            writeMessage(Message.loginSuccessMessage());
        }
        else
            writeMessage(Message.loginFailureMessage());
        showMainMenu();
    }
}
