package org.server.net.codec.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.server.ServerConstants;
import org.server.model.ProfileImage;
import org.server.model.RegistrationDetails;
import org.server.model.request.LoginRequest;
import org.server.model.UserDetails;
import org.server.model.request.RegisterRequest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public final class LoginRegistrationDecoder extends ByteToMessageDecoder {

    private static final int REGISTRATION = 0;
    private static final int LOGIN = 1;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int type = in.readByte();
        switch (type) {
            case REGISTRATION -> decodeRegistration(in, out);
            case LOGIN -> decodeLogin(in, out);
            default -> in.release();
        }
    }

    public void decodeRegistration(ByteBuf in, List<Object> out) {
        String email = getString(in);
        String password = getString(in);
        String username = getString(in);
        String DOB = getString(in);
        int profileImageLength = in.readShort();
        byte[] bytes = in.readBytes(in.readableBytes()).array();
        out.add(new RegisterRequest(new RegistrationDetails(email, password, username, new ProfileImage(bytes, profileImageLength), DOB)));
    }

    public void decodeLogin(ByteBuf in, List<Object> out) {
        String email = getString(in);
        String password = getString(in);
        out.add(new LoginRequest(new UserDetails(email, password)));
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
