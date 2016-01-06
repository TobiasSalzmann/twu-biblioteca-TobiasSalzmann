package com.twu.biblioteca;

/**
 * Created by tsalzman on 1/6/16.
 */
public abstract class Message{


    public static Message welcomeMessage(){
        return simpleMessage(Constants.welcomeString);
    }

    private static Message simpleMessage(String string){
        return new Message(){
            public String toString(){
                return string;
            }
        };
    }


    public static Message bookListMessage() {
        return simpleMessage("Book 1, Author 1, 1337\nBook 2, Author 2, 1976");
    }

    public static Message mainMenuMessage() {
        return simpleMessage(Constants.mainMenuString + "\n" + Constants.listBooksCommand + " - " + Constants.listBooksExplanation);
    }

    public static Message invalidMessage() {
        return simpleMessage(Constants.invalidOptionString);
    }

    public static Message closeMessage() {
        return simpleMessage(Constants.quitMessage);
    }
}
