package org.server.net.packet.encoders;

import org.server.net.packet.OutboundPacket;

public class GroupMessageEncoder extends OutboundPacket {

    private final String message, senderName, profileImage;
    private final long groupId, senderId;

    public GroupMessageEncoder(String message, String senderName, long groupId, String profileImage, long senderId) {
        super(11);
        this.message = message;
        this.senderName = senderName;
        this.groupId = groupId;
        this.profileImage = profileImage;
        this.senderId = senderId;
    }

    @Override
    protected void encode() {
        builder.writeString(message);
        builder.writeString(senderName);
        builder.writeString(profileImage);
        builder.writeLong(senderId);
        builder.writeLong(groupId);
    }
}
