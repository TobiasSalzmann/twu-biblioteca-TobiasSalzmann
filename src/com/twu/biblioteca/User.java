package com.twu.biblioteca;

/**
 * Created by tsalzman on 1/11/16.
 */
public class User {
    private final String id;
    private final String personalInfo;
    private final String password;

    public User(String id, String password, String personalInfo) {
        this.id = id;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    public String getId() {
        return id;
    }
    
    public Message getPersonalInfo() {
        return Message.simpleMessage(personalInfo);
    }

    public boolean validate(String password) {
        return password.equals(this.password);
    }
}
