package com.twu.biblioteca;

import java.util.List;

/**
 * Created by tsalzman on 1/6/16.
 */
public abstract class Message{


    static Message checkOutSuccessMessage(Book b){
     return simpleMessage(b.getTitle() + " " + Constants.checkoutSuccessString);
    }

    static Message welcomeMessage(){
        return simpleMessage(Constants.welcomeString);
    }

    private static Message simpleMessage(String string){
        return new Message(){
            public String toString(){
                return string;
            }
        };
    }

    static Message bookListMessage(List<Book> books) {
        return simpleMessage(String.join("\n", Util.map(books, Book::toString)));
    }

    static Message mainMenuMessage(List<Command> commands) {
        String options = String.join("\n",Util.map(commands,Command::toString));
        return simpleMessage(Constants.mainMenuString + "\n" + options);
    }

    static Message invalidMessage() {
        return simpleMessage(Constants.invalidOptionString);
    }

    static Message closeMessage() {
        return simpleMessage(Constants.quitString);
    }

    static Message checkOutFailureMessage() {
        return simpleMessage(Constants.checkoutFailureString);
    }

    static Message returnSuccessMessage(Book b) {
        return simpleMessage(b.getTitle() + " " + Constants.returnSuccessString);
    }

    static Message returnFailureMessage() {
        return simpleMessage(Constants.returnFailureString);
    }
}
