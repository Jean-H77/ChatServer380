package org.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.server.net.codec.decoders.LoginDecoder;
import org.server.net.codec.encoders.LoginEncoder;
import org.server.net.codec.handlers.ServerChannelHandler;

public class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();

        p.addLast(new LoginEncoder());
        p.addLast(new LoginDecoder());
        p.addLast(new ServerChannelHandler());
    }
}
