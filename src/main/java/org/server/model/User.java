package org.server.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private final Session session;
    private final String username;
    private final String password;
    private final long uuid;
    private byte[] profilePictureData;
    private final Set<Long> groupChatIds = new HashSet<>();

    public User(Session session, String username, String password, long uuid) {
        this.session = session;
        this.username = username;
        this.password = password;
        this.uuid = uuid;
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

    public long getUuid() {
        return uuid;
    }

    public byte[] getProfilePictureData() {
        return profilePictureData;
    }

    public void setProfilePictureData(byte[] profilePictureData) {
        this.profilePictureData = profilePictureData;
    }
}
