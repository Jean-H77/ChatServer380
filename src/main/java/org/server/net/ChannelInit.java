package org.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;
import org.server.model.Session;
import org.server.net.codec.decoders.LoginRegistrationDecoder;
import org.server.net.codec.encoders.LoginRegistrationEncoder;
import org.server.net.codec.handlers.ServerChannelHandler;

public final class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        ch.attr(AttributeKey.valueOf("session.key")).setIfAbsent(new Session(ch));

        p.addLast("LoginRegistrationE", new LoginRegistrationEncoder());
        p.addLast("LoginRegistrationD", new LoginRegistrationDecoder());
        p.addLast(new ServerChannelHandler());
    }
}
