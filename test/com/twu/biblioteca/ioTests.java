package com.twu.biblioteca;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;


/**
 * Created by tsalzman on 1/7/16.
 */
public class IOTests {

    @Test
    public void connectInputStream(){
        InputStream in = new ByteArrayInputStream("quit".getBytes());
        Session session = Session.createSimpleSession(Library.createBookTestLibrary(), Library.createMovieTestLibrary(), in, null);
        assertEquals(Constants.quitString, session.lastMessage());
    }




    @Test
    public void connectPrintStream() {
        InputStream in = new ByteArrayInputStream("quit".getBytes());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bytes);
        Session session = Session.createSimpleSession(Library.createBookTestLibrary(), Library.createMovieTestLibrary(), in, out);
        assertEquals(Constants.quitString, session.lastMessage());
        String[] lines = bytes.toString().split("\n");
        assertEquals(Constants.welcomeString, lines[0]);
        assertEquals(Constants.quitString,lines[lines.length-1]);
    }
}
