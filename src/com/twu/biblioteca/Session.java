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
    private List<Command> commands = Arrays.asList(
            new Command(args -> listBooks(), Constants.listBooksCommand, Constants.listBooksDescription),
            new Command(args -> handleCheckout(args[0]), Constants.checkoutBookCommand,Constants.checkoutBookDescription, Constants.checkoutBookParamName),
            new Command(args -> handleReturn(args[0]), Constants.returnBookCommand, Constants.returnBookDescription, Constants.returnBookParamName),
            new Command(args -> listMovies(), Constants.listMoviesCommand, Constants.listMoviesDescription),
            new Command(args -> handleCheckoutMovie(args[0]), Constants.checkoutMovieCommand,Constants.checkoutMovieDescription, Constants.checkoutMovieParamName),
            new Command(args -> handleReturnMovie(args[0]), Constants.returnMovieCommand, Constants.returnMovieDescription, Constants.returnMovieParamName),
            new Command(args -> closeSession(), Constants.closeCommand, Constants.closeDescription)
    );

    private void listMovies() {
        List<Movie> availableMovies = library.availableMovies();
        writeMessage(Message.movieListMessage(availableMovies));
        showMainMenu();
    }

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
        writeMessage(Message.welcomeMessage());
        showMainMenu();

    }

    public String lastMessage() {
        return history.get(history.size() - 1).toString();
    }

    public List<String> history() {
        return Util.map(history, Object::toString);
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
        Optional<Book> bookOpt = Util.find(library.unavailableBooks(),b -> s.trim().equals(b.getTitle()));
        if(bookOpt.isPresent()){
            Book b = bookOpt.get();
            b.returnToLibrary();
            writeMessage(Message.returnSuccessMessage(b));
        }
        else
            writeMessage(Message.returnFailureMessage());

        showMainMenu();
    }



    private void handleCheckoutMovie(String s) {
        for (Movie b: library.availableMovies()){
            if(s.trim().equals(b.getTitle())){
                b.checkOut();
                writeMessage(Message.checkOutMovieSuccessMessage(b));
                showMainMenu();
                return;
            }
        }
        writeMessage(Message.checkOutMovieFailureMessage());
        showMainMenu();

    }

    private void handleReturnMovie(String s) {
        Optional<Movie> bookOpt = Util.find(library.unavailableMovies(),b -> s.trim().equals(b.getTitle()));
        if(bookOpt.isPresent()){
            Movie b = bookOpt.get();
            b.returnToLibrary();
            writeMessage(Message.returnMovieSuccessMessage(b));
        }
        else
            writeMessage(Message.returnMovieFailureMessage());

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
