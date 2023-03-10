package org.server.service;

import io.netty.channel.Channel;
import org.server.model.request.LoginRequest;
import org.server.model.Session;
import org.server.model.User;
import org.server.model.UserDetails;
import org.server.net.codec.decoders.PacketDecoder;
import org.server.net.codec.encoders.PacketEncoder;

public class LoginService {

    public void validateLogin(LoginRequest lrr) {
        Channel channel = lrr.getChannel();

        UserDetails userDetails = lrr.getUserDetails();

        // do password check from database here

        String username = "temp"; // grab from database
        long uuid = 5;// grab from database
        String image = ""; // grab from database

        User user = new User(new Session(channel), username, image, userDetails, uuid);
        finalizeLogin(user, channel);
    }

    public void addLoginRequest(LoginRequest lrr) {
        validateLogin(lrr);
    }

    private void finalizeLogin(User user, Channel channel) {
        System.out.println("Hey I logged in : " + user.getUsername());
        channel.write("Hey login encoder".getBytes());

        channel.pipeline().replace("LoginRegistrationE", "PacketE", new PacketEncoder());
        channel.pipeline().replace("LoginRegistrationD", "PacketD", new PacketDecoder());
    }
}
