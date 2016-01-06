package com.twu.biblioteca;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


/**
 * Created by tsalzman on 1/5/16.
 */
public class UserStories {


    @Test
    public void testWelcomeMessage(){
        Session session = new Session();
        assertEquals(Constants.welcomeString,session.history().get(0));
    }

    @Test
    public void testListBooksDetails(){
        Session session = new Session();
        session.listBooks();
        assertEquals("Book 1, Author 1, 1337\nBook 2, Author 2, 1976",session.lastMessage());
    }

    @Test
    public void testMainMenu(){
        Session session = new Session();
        assertEquals(Constants.mainMenuString + "\n" + Constants.listBooksString + " - " + Constants.listBooksExplanation,session.history().get(1));
    }

    @Test
    public void testValidOptionListBooks(){
        Session session = new Session();
        session.handleInput("list");
        assertEquals("Book 1, Author 1, 1337\nBook 2, Author 2, 1976",session.history().get(session.history().size()-2));
    }

    @Test
    public void testInvalidOption(){
        Session session = new Session();
        session.handleInput("bogus");
        assertEquals(Constants.invalidOptionString,session.history().get(session.history().size()-2));
        assertEquals(Constants.mainMenuString + "\n" + Constants.listBooksString + " - " + Constants.listBooksExplanation,session.lastMessage());
    }






}
