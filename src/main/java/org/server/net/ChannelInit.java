package org.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.server.net.codec.decoders.LoginRegistrationDecoder;
import org.server.net.codec.encoders.LoginRegistrationEncoder;
import org.server.net.codec.handlers.ServerChannelHandler;

public final class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();

        p.addLast(new LoginRegistrationEncoder());
        p.addLast(new LoginRegistrationDecoder());
        p.addLast(new ServerChannelHandler());
    }
}
