package com.twu.biblioteca;

/**
 * Created by tsalzman on 1/6/16.
 */
public abstract class Message{


    public static Message welcomeMessage(){
        return new Message(){
            public String toString(){
                return Constants.welcomeString;
            }
        };
    }


    public static Message bookListMessage() {
        return new Message(){
            public String toString(){
                return "Book 1, Author 1, 1337\nBook 2, Author 2, 1976";
            }
        };
    }
}
