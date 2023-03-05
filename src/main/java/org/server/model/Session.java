package org.server.model;

import io.netty.channel.Channel;
import org.server.net.packet.AbstractOutboundPacket;
import org.server.net.packet.Packet;
import org.server.net.packet.PacketConstants;

/**
 * @// TODO: 3/2/2023 documentation
 * @param channel
 */
public record Session(Channel channel) {

    public void send(AbstractOutboundPacket packet) {
        packet.send(this);
    }

    public void read(Packet packet) {
        int opcode = packet.opcode();
        PacketConstants.PACKET_DECODERS.get(opcode).handleMessage(packet);
    }
}
