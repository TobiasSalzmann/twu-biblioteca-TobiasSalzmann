package com.twu.biblioteca;


import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void test2(){
        assertArrayEquals(new String[]{"hello", "new", "world"},"hello new world".split(" "));
    }
}
