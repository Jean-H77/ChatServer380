package org.server.net.packet.encoders;

import org.server.net.packet.AbstractOutboundPacket;

public class GroupMessageEncoder extends AbstractOutboundPacket {

    private final String message;
    private final String senderName;
    private final long groupId;
    private final byte[] profilePictureData;

    public GroupMessageEncoder(String message, String senderName, long groupId, byte[] profilePictureData) {
        super(11);
        this.message = message;
        this.senderName = senderName;
        this.groupId = groupId;
        this.profilePictureData = profilePictureData;
    }

    @Override
    protected void encode() {
        builder.writeString(message);
        builder.writeString(senderName);
        builder.writeLong(groupId);
        builder.writeBytes(profilePictureData);
    }
}
