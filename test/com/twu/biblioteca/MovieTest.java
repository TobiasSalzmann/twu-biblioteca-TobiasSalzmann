package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by tsalzman on 1/11/16.
 */
public class MovieTest {
    @Test
    public void toStringTest(){
        Movie m = new Movie("title", 1989, "director", "uid");
        assertThat(m.toString(), is(String.format(Constants.movieFormatString, "title", 1989, "director", "unrated", "uid")));
    }
}
