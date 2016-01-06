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
        assertEquals("Welcome to Biblioteca!",session.lastMessage());

    }


}
