package org.server.model;

import io.netty.channel.Channel;
import org.server.net.packet.encoders.OutgoingPacket;
import org.server.net.packet.Packet;

/**
 * @// TODO: 3/2/2023 documentation
 * @param channel
 */
public record Session(Channel channel) {

    public void send(OutgoingPacket packet) {
        packet.send(this);
    }

    public void readPacket(Packet packet) {
        int opcode = packet.opcode();
    }
}
