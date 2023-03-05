package org.server.net.codec.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.ServerLauncher;
import org.server.model.User;
import org.server.net.codec.decoders.LoginRegistrationDecoder;
import org.server.net.codec.decoders.PacketDecoder;
import org.server.net.packet.encoders.DirectMessageEncoder;

public final class LoginRegistrationEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        if(msg instanceof User) {
            ctx.pipeline().replace("LoginRegistrationE", "PacketE", new PacketEncoder());
            ctx.pipeline().replace("LoginRegistrationD", "PacketD", new PacketDecoder());
            ServerLauncher.server.addNewUser((User) msg);
        }
    }
}
