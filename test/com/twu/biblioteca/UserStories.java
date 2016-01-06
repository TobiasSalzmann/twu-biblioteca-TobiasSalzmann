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
        assertEquals("Welcome to Biblioteca!",session.lastMessage());
    }

    @Test
    public void testListBooksDetails(){
        Session session = new Session();
        session.listBooks();
        assertEquals(Arrays.asList("Welcome to Biblioteca!","Book 1, Author 1, 1337\nBook 2, Author 2, 1976"),session.history());
    }




}
