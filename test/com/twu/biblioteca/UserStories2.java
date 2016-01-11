package com.twu.biblioteca;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by tsalzman on 1/8/16.
 */
public class UserStories2 {

    @Test
    public void mainMenuMoviesEntry(){
        Session session = Session.createTestSession();
        assertTrue(session.lastMessage().contains(Constants.listMoviesCommand + " - " + Constants.listMoviesDescription));
    }

    @Test
    public void listMovies(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.listMoviesCommand);
        assertThat(session.history(-2).contains("Movie 1"),is(true));
        assertThat(session.history(-2).contains("Movie 2"),is(true));
    }

    @Test
    public void checkoutMovieSuccess(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        assertEquals("Movie 1 " + Constants.checkoutSuccessString, session.history(-2));
    }

    @Test
    public void checkoutMovieRemoval(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        session.handleInput(Constants.listMoviesCommand);
        String[] lines = session.history(-2).split("\n");
        assertEquals(String.format(Constants.movieFormatString, "Movie 2", 1985, "Director 2", "7", "3"), lines[1]);
        assertEquals(2, lines.length);
    }

    @Test
    public void checkoutMovieFailure(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 3");
        assertEquals(Constants.noMatchesString + " Movie 3", session.history(-2));
    }

    @Test
    public void checkoutMovieDoubleRemoval(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        assertEquals(Constants.noMatchesString + " Movie 1", session.history(-2));
    }

    @Test
    public void returnMovieSuccess(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.checkoutMovieCommand + " Movie 1");
        session.handleInput(Constants.returnMovieCommand + " Movie 1");
        assertEquals("Movie 1 " + Constants.returnSuccessString, session.history(-2));
    }

    @Test
    public void returnMovieFailure(){
        Session session = Session.createTestSession();
        session.handleInput(Constants.returnMovieCommand + " Movie 3");
        assertEquals(Constants.noMatchesString + " Movie 3", session.history(-2));
    }
}
