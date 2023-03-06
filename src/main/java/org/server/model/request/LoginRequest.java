package org.server.model.request;

import io.netty.channel.Channel;
import org.server.model.UserDetails;

public class LoginRequest {

    private Channel channel;
    private final UserDetails userDetails;

    public LoginRequest(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

}
