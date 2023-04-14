package org.server.service;

import io.netty.channel.Channel;
import org.server.model.request.LoginRequest;
import org.server.model.Session;
import org.server.model.User;
import org.server.model.UserDetails;
import org.server.net.codec.decoders.PacketDecoder;
import org.server.net.codec.encoders.PacketEncoder;
import org.server.persistance.Database; //test
public class LoginService {
    private final Database database = new Database(); //test
    public void validateLogin(LoginRequest lrr) {
        Channel channel = lrr.getChannel();

        UserDetails userDetails = lrr.getUserDetails();

        if (database.EmailExists (userDetails.email())) //verify if the user exists at all first before importing the information
        {
            long uuid = database.GetUUID (userDetails.email());
            String username = database.GetUsername (uuid);
            String image = database.GetImage (uuid);
            User user = new User(new Session(channel), username, image, userDetails, uuid);
            finalizeLogin(user, channel);
        }
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
