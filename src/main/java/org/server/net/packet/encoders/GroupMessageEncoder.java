package org.server.net.packet.encoders;

import org.server.net.packet.AbstractOutboundPacket;

public class GroupMessageEncoder extends AbstractOutboundPacket {

    private final String message;
    private final long groupId;

    public GroupMessageEncoder(String message, long groupId) {
        super(11);
        this.message = message;
        this.groupId = groupId;
    }

    @Override
    protected void encode() {
        builder.writeString(message);
        builder.writeLong(groupId);
    }
}
