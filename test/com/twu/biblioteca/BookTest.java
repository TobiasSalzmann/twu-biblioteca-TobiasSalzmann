package com.twu.biblioteca;

import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by tsalzman on 1/10/16.
 */
public class BookTest {

    @Test
    public void toStringTest(){
        Book b = new Book("title", "author", 1989, "uid");
        assertThat(b.toString(), is(String.format(Constants.bookFormatString, "title", "author", 1989, "uid")));
    }
}
