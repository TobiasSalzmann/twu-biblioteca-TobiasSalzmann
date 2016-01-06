package com.twu.biblioteca;

import com.sun.tools.javac.code.Attribute;

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
        return simpleMessage(Constants.mainMenuString + "\n" + Constants.listBooksString + " - " + Constants.listBooksExplanation);
    }
}
