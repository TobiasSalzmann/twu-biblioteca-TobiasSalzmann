package com.twu.biblioteca;

/**
 * Created by tsalzman on 1/8/16.
 */
public interface LibraryItem{

    String getUID();
    boolean match(String description);
    String getTitle();
}
