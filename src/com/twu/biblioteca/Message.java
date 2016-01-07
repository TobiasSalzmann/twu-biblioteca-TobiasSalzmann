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
        return simpleMessage(String.join("\n", Util.mapList(books, Book::toString)));
    }

    static Message mainMenuMessage() {
        return simpleMessage(Constants.mainMenuString + "\n" + Constants.listBooksCommand + " - " + Constants.listBooksDescription);
    }

    static Message invalidMessage() {
        return simpleMessage(Constants.invalidOptionString);
    }

    static Message closeMessage() {
        return simpleMessage(Constants.quitMessage);
    }

    public static Message checkOutFailureMessage() {
        return simpleMessage(Constants.checkoutFailureString);
    }
}
