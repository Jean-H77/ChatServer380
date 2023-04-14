package org.server.net.packet.encoders;

import org.server.net.packet.OutboundPacket;

public class GroupMessageEncoder extends OutboundPacket {

    private final String message, senderName, profileImage;
    private final long groupId;

    public GroupMessageEncoder(String message, String senderName, long groupId, String profileImage) {
        super(11);
        this.message = message;
        this.senderName = senderName;
        this.groupId = groupId;
        this.profileImage = profileImage;
    }

    @Override
    protected void encode() {
        builder.writeString(message);
        builder.writeString(senderName);
        builder.writeLong(groupId);
        builder.writeString(profileImage);
    }
}
