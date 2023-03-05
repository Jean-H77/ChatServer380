package org.server.net.codec.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import org.server.ServerConstants;
import org.server.model.Session;
import org.server.model.User;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public final class LoginRegistrationDecoder extends ByteToMessageDecoder {

    private static final int REGISTRATION = 0;
    private static final int LOGIN = 1;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int type = in.readByte();

        switch (type) {
            case REGISTRATION -> {

            }
            case LOGIN -> {
                String username = getString(in);

                String password = getString(in);

                if(password.equals("password")) {
                    Session session = (Session)ctx.channel().attr(AttributeKey.valueOf("session.key")).get();
                    User user = new User(session, username, password, 351515);
                    session.setUser(user);
                    ctx.write(user);
                }
            }
            default -> in.release();
        }
    }


    public static String getString(ByteBuf payload) {
        byte[] bytes = new byte[payload.readableBytes()];
        byte b;
        int i = 0;
        while((b = payload.readByte()) != ServerConstants.STRING_TERMINATOR) {
            bytes[i] = b;
            i++;
        }
        return new String(Arrays.copyOfRange(bytes, 0, i), StandardCharsets.UTF_8);
    }

}
