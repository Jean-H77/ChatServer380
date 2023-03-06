package org.server.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private final Session session;
    private final String username;
    private final UserDetails userDetails;
    private final long uuid;
    private ProfileImage profileImage;
    private final Set<Long> groupChatIds = new HashSet<>();

    public User(Session session, String username, UserDetails userDetails, long uuid) {
        this.session = session;
        this.username = username;
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

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}
