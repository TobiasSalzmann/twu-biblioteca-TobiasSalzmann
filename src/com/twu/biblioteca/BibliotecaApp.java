package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        //Session.createSimpleSession(Library.createBookTestLibrary(), Library.createMovieTestLibrary(), System.in, System.out);
        Session.createUserSession(Library.createBookTestLibrary(), Library.createMovieTestLibrary(), UserDB.createTestDB(), System.in, System.out);
    }
}
