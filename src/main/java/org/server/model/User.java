package org.server.model;

public class User {

    private final Session session;
    private final String username;
    private final String password;

    public User(Session session, String username, String password) {
        this.session = session;
        this.username = username;
        this.password = password;
    }

    public Session getSession() {
        return session;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
