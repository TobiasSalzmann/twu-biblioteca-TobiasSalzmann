package com.twu.biblioteca;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tsalzman on 1/11/16.
 */
public class UserDB {
    private final HashMap<String, User> usersByID;

    public UserDB(Collection<User> users) {
        usersByID = new HashMap<>();
        users.forEach(user -> usersByID.put(user.getId(), user));
    }

    public boolean validate(String id, String password) {
        return usersByID.containsKey(id) && usersByID.get(id).validate(password);
    }

    public static UserDB createTestDB() {
        return new UserDB(Arrays.asList(
                new User("user1", "password1", "name1\nmail1\nphone1"),
                new User("user2", "password2", "name2\nmail2\nphone2")
        ));
    }

    public Message getUserInfo(String user) {
        return usersByID.get(user).getPersonalInfo();
    }
}
