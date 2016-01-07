package com.twu.biblioteca;

import org.junit.Test;

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
        assertEquals("Book 1, Author 1, 1337\nBook 2, Author 2, 1976",session.history().get(session.history().size()-2));
    }

    @Test
    public void testMainMenu(){
        Session session = new Session();
        String[] lines = session.history().get(1).split("\n");
        assertEquals(Constants.mainMenuString, lines[0]);
        assertEquals(Constants.listBooksCommand + " - " + Constants.listBooksDescription, lines[1]);
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
        assertEquals(Constants.mainMenuString + "\n" + Constants.listBooksCommand + " - " + Constants.listBooksDescription,session.lastMessage());
    }

    @Test
    public void testQuitOption(){
        Session session = new Session();
        session.handleInput("quit");
        assertEquals(Constants.quitMessage, session.lastMessage());
    }

    @Test
    public void checkoutOptionSuccess(){
        Session session = new Session();
        session.handleInput("checkout Book 1");
        assertEquals("Book 1 " + Constants.checkoutSuccessString, session.history().get(session.history().size()-2));
    }

    @Test
    public void checkoutOptionRemoval(){
        Session session = new Session();
        session.handleInput("checkout Book 1");
        session.handleInput("list");
        assertEquals("Book 2, Author 2, 1976", session.history().get(session.history().size()-2));
    }









}
