package com.twu.biblioteca;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by tsalzman on 1/11/16.
 */
public class UserTest {

    @Test
    public void user(){
        User user1 = new User("id1", "password1", "name1\nmail1\nphone1");
        assertThat(user1.validate("password1"),is(true));
        assertThat(user1.validate("password2"),is(false));
    }

    @Test
    public void userdb(){
        User user1 = new User("id1", "password1", "name1\nmail1\nphone1");
        User user2 = new User("id2","password2", "name2\nmail2\nphone2");
        UserDB db = new UserDB(Arrays.asList(user1,user2));
        assertThat(db.validate("id1", "password1"),is(true));
        assertThat(db.validate("id2", "password1"),is(false));
    }
}
