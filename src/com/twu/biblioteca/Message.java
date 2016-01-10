package com.twu.biblioteca;

import java.util.List;

/**
 * Created by tsalzman on 1/6/16.
 */
public abstract class Message{


    static Message checkOutSuccessMessage(LibraryItem item){
     return simpleMessage(item.getTitle() + " " + Constants.checkoutSuccessString);
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

    static Message itemListMessage(List<LibraryItem> items) {
        return simpleMessage(String.join("\n", Util.map(items, LibraryItem::toString)));
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

    static Message returnSuccessMessage(LibraryItem item) {
        return simpleMessage(item.getTitle() + " " + Constants.returnSuccessString);
    }

    static Message noMatchesMessage(String query){
        return simpleMessage(Constants.noMatchesString + " " + query);
    }

    static Message notUniqueMessage(String query) {
        return simpleMessage(Constants.notUniqueString + " " + query);
    }



}
