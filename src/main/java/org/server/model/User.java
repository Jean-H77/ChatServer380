package org.server.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private final Session session;
    private final String username;
    private final String password;
    private final Set<Long> groupChatIds = new HashSet<>();

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

    public Set<Long> getGroupChatIds() {
        return groupChatIds;
    }
}
