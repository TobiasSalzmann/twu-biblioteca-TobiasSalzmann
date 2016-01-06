package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by tsalzman on 1/6/16.
 */
public class MessageAbstractionTest {

    @Test
    public void welcomeMessage(){
        Message m = Message.welcomeMessage();
        assertEquals(Constants.welcomeString, m.toString());
    }

    @Test
    public void bookListMessage(){
        Message m = Message.bookListMessage();
        assertEquals("Book 1, Author 1, 1337\nBook 2, Author 2, 1976", m.toString());
    }




}
