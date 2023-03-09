package org.server.model.request;

import io.netty.channel.Channel;
import org.server.model.RegistrationDetails;

public class RegisterRequest  {
    private Channel channel;
    private final RegistrationDetails registrationDetails;

    public RegisterRequest(RegistrationDetails registrationDetails) {
        this.registrationDetails = registrationDetails;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public RegistrationDetails getRegistrationDetails() {
        return registrationDetails;
    }
}
