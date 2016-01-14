package com.twu.biblioteca;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
/**
 * Created by tsalzman on 1/11/16.
 */
public class UserStoriesLogin {

    @Test
    public void restrictedMainMenu(){
        Session session = Session.createUserTestSession();
        assertThat(session.history(-1).contains(Constants.listBooksCommand), is(true));
        assertThat(session.history(-1).contains(Constants.listMoviesCommand), is(true));
        assertThat(session.history(-1).contains(Constants.closeCommand), is(true));
        assertThat(session.history(-1).contains(Constants.checkoutBookCommand), is(false));
        assertThat(session.history(-1).contains(Constants.checkoutMovieCommand), is(false));
        assertThat(session.history(-1).contains(Constants.returnBookCommand), is(false));
        assertThat(session.history(-1).contains(Constants.returnMovieCommand), is(false));
    }

    @Test
    public void loginCommand(){
        Session session = Session.createUserTestSession();
        String command = Constants.loginCommand + " " + Constants.loginParamName + " - " + Constants.loginDescription;
        assertThat(session.history(-1).contains(command), is(true));
    }

    @Test
    public void loginSuccess(){
        Session session = Session.createUserTestSession();
        session.handleInput("login user1 password1");
        assertThat(session.history(-2), is(Constants.loginSuccessString));
    }

    @Test
    public void loginFailure1(){
        Session session = Session.createUserTestSession();
        session.handleInput("login user1 password2");
        assertThat(session.history(-2), is(Constants.loginFailureString));
    }

    @Test
    public void loginFailure2(){
        Session session = Session.createUserTestSession();
        session.handleInput("login user3 password2");
        assertThat(session.history(-2), is(Constants.loginFailureString));
    }

    @Test
    public void fullMainMenu(){
        Session session = Session.createUserTestSession();
        session.handleInput("login user1 password1");
        assertThat(session.history(-1).contains(Constants.listBooksCommand), is(true));
        assertThat(session.history(-1).contains(Constants.listMoviesCommand), is(true));
        assertThat(session.history(-1).contains(Constants.closeCommand), is(true));
        assertThat(session.history(-1).contains(Constants.checkoutBookCommand), is(true));
        assertThat(session.history(-1).contains(Constants.checkoutMovieCommand), is(true));
        assertThat(session.history(-1).contains(Constants.returnBookCommand), is(true));
        assertThat(session.history(-1).contains(Constants.returnMovieCommand), is(true));
    }

    @Test
    public void ShowPersonalInfoCommand(){
        Session session = Session.createUserTestSession();
        session.handleInput("login user1 password1");
        String command = Constants.userInfoCommand + " - " + Constants.userInfoDescription;
        assertThat(session.history(-1).contains(command), is(true));
    }

    @Test
    public void userInfoMessage(){
        Session session = Session.createUserTestSession();
        session.handleInput("login user1 password1");
        session.handleInput("userinfo");
        assertThat(session.history(-2), is("name1\nmail1\nphone1"));
    }


}
