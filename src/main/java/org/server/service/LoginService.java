package org.server.service;

import io.netty.channel.Channel;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.server.model.request.LoginRequest;
import org.server.model.Session;
import org.server.model.User;
import org.server.model.UserDetails;
import org.server.net.codec.decoders.PacketDecoder;
import org.server.net.codec.encoders.PacketEncoder;
import org.server.persistance.Database;
public class LoginService {
    private final Database database = new Database();
    public void validateLogin(LoginRequest lrr) {
        Channel channel = lrr.getChannel();

        UserDetails userDetails = lrr.getUserDetails();

        if (database.emailExists(userDetails.email())) {
            long uuid = database.getUUID(userDetails.email());
            String username = database.getUsername(uuid);
            String image = database.getImage(uuid);
            User user = new User(new Session(channel), username, image, userDetails, uuid);

            BCrypt.Result result = BCrypt.verifyer().verify(userDetails.password().toCharArray(), database.getPassword(uuid));
            if (result.verified) {
                finalizeLogin(user, channel);
            }
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
