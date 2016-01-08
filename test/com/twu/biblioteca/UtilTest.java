package com.twu.biblioteca;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by tsalzman on 1/8/16.
 */
public class UtilTest {
    @Test
    public void mapTest(){
        assertEquals(Arrays.asList("2","3","4"),Util.map(Arrays.asList(1,2,3),n -> "" + (n+1)));
    }

    @Test
    public void filterTest(){
        assertEquals(Arrays.asList(1,3),Util.filter(Arrays.asList(1,2,3),n -> n % 2 != 0));
    }

    @Test
    public void findTest(){
        assertEquals(Optional.of(3),Util.find(Arrays.asList(1,2,3), n -> n % 3 == 0));
        assertEquals(Optional.empty(),Util.find(Arrays.asList(1,2,3), n -> n > 5));
    }
}
