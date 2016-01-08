package com.twu.biblioteca;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by tsalzman on 1/8/16.
 */
public class UserStories2 {

    @Test
    public void mainMenuMoviesEntry(){
        Session session = new Session();
        assertTrue(session.lastMessage().contains(Constants.listMoviesCommand + " - " + Constants.listMoviesDescription));
    }

    @Test
    public void listMovies(){
        Session session = new Session();
        session.handleInput(Constants.listMoviesCommand);
        assertEquals("Movie 1, 1984, Director 1, unrated\nMovie 2, 1985, Director 2, 7",session.history(-2));
    }

    @Test
    public void checkoutMovieSuccess(){
        Session session = new Session();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        assertEquals("Movie 1 " + Constants.checkoutSuccessString, session.history(-2));
    }

    @Test
    public void checkoutMovieRemoval(){
        Session session = new Session();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        session.handleInput(Constants.listMoviesCommand);
        assertEquals("Movie 2, 1985, Director 2, 7", session.history(-2));
    }

    @Test
    public void checkoutMovieFailure(){
        Session session = new Session();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 3");
        assertEquals(Constants.checkoutFailureString, session.history(-2));
    }

    @Test
    public void checkoutMovieDoubleRemoval(){
        Session session = new Session();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        assertEquals(Constants.checkoutFailureString, session.history(-2));
    }

    @Test
    public void returnMovieSuccess(){
        Session session = new Session();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        session.handleInput(Constants.returnMovieCommand + " Movie 1");
        assertEquals("Movie 1 " + Constants.returnSuccessString, session.history(-2));
    }

    @Test
    public void returnMovieFailure(){
        Session session = new Session();
        session.handleInput(Constants.returnMovieCommand + " Movie 3");
        assertEquals(Constants.returnFailureString, session.history(-2));
    }
}
