package org.server.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private final Session session;
    private final String username;
    private String profileImage;
    private final UserDetails userDetails;
    private final long uuid;
    private final Set<Long> groupChatIds = new HashSet<>();
    private long currentGroupChat;

    public User(Session session, String username, String profileImage, UserDetails userDetails, long uuid) {
        this.session = session;
        this.username = username;
        this.profileImage = profileImage;
        this.userDetails = userDetails;
        this.uuid = uuid;
    }

    public Session getSession() {
        return session;
    }

    public String getUsername() {
        return username;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public Set<Long> getGroupChatIds() {
        return groupChatIds;
    }

    public long getUuid() {
        return uuid;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public long getCurrentGroupChatId() {
        return currentGroupChat;
    }
}
