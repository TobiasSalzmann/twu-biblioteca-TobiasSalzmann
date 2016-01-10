package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by tsalzman on 1/5/16.
 */
public class UserStories {


    @Test
    public void testWelcomeMessage(){
        Session session = Session.createTestSession();
        assertEquals(Constants.welcomeString,session.history().get(0));
    }

    @Test
    public void testListBooksDetails(){
        Session session = Session.createTestSession();
        session.listBooks();
        assertEquals("Book 1, Author 1, 1337\nBook 2, Author 2, 1976",session.history(-2));
    }

    @Test
    public void testMainMenu(){
        Session session = Session.createTestSession();
        String[] lines = session.history().get(1).split("\n");
        assertTrue(lines.length > 5);
        assertEquals(Constants.mainMenuString, lines[0]);
        assertTrue(session.history().get(1).contains(Constants.listBooksCommand + " - " + Constants.listBooksDescription));
        assertTrue(session.history().get(1).contains(Constants.checkoutBookCommand + " " + Constants.checkoutBookParamName + " - " + Constants.checkoutBookDescription));

    }

    @Test
    public void testValidOptionListBooks(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.listBooksCommand);
        assertEquals("Book 1, Author 1, 1337\nBook 2, Author 2, 1976",session.history(-2));
    }

    @Test
    public void testInvalidOption(){
        Session session = Session.createTestSession();
        session.handleInput("bogus");
        assertEquals(Constants.invalidOptionString,session.history(-2));
        assertTrue(session.lastMessage().startsWith(Constants.mainMenuString));
    }

    @Test
    public void testQuitOption(){
        Session session = Session.createTestSession();
        session.handleInput("quit");
        assertEquals(Constants.quitString, session.lastMessage());
    }

    @Test
    public void checkoutOptionSuccess(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutBookCommand + " Book 1");
        assertEquals("Book 1 " + Constants.checkoutSuccessString, session.history(-2));
    }

    @Test
    public void checkoutOptionRemoval(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutBookCommand + " Book 1");
        session.handleInput(Constants.listBooksCommand);
        assertEquals("Book 2, Author 2, 1976", session.history(-2));
    }

    @Test
    public void checkoutOptionFailure(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutBookCommand + " Book 3");
        assertEquals(Constants.checkoutFailureString, session.history(-2));
    }

    @Test
    public void checkoutOptionDoubleRemoval(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutBookCommand + " Book 1");
        session.handleInput(Constants.checkoutBookCommand + " Book 1");
        assertEquals(Constants.checkoutFailureString, session.history(-2));
    }

    @Test
    public void returnOptionSuccess(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutBookCommand + " Book 1");
        session.handleInput(Constants.returnBookCommand + " Book 1");
        assertEquals("Book 1 " + Constants.returnSuccessString, session.history(-2));
    }

    @Test
    public void returnOptionFailure(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.returnBookCommand + " Book 3");
        assertEquals(Constants.returnFailureString, session.history(-2));
    }








}
