package org.spacehq.mc.protocol.packet.ingame.server.world;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ServerUpdateSignPacket implements Packet {
    private NetPosition netPosition;
    private Message lines[];

    @SuppressWarnings("unused")
    private ServerUpdateSignPacket() {
    }

    public ServerUpdateSignPacket(NetPosition netPosition, String lines[]) {
        this(netPosition, toMessages(lines));
    }

    public ServerUpdateSignPacket(NetPosition netPosition, Message lines[]) {
        if(lines.length != 4) {
            throw new IllegalArgumentException("Lines must contain exactly 4 strings!");
        }

        this.netPosition = netPosition;
        this.lines = lines;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    public Message[] getLines() {
        return this.lines;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.netPosition = NetUtil.readPosition(in);
        this.lines = new Message[4];
        for(int count = 0; count < this.lines.length; count++) {
            this.lines[count] = Message.fromString(in.readString());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, this.netPosition);
        for(Message line : this.lines) {
            out.writeString(line.toJsonString());
        }
    }

    @Override
    public boolean isPriority() {
        return false;
    }

    private static Message[] toMessages(String lines[]) {
        Message messages[] = new Message[lines.length];
        for(int index = 0; index < lines.length; index++) {
            messages[index] = Message.fromString(lines[index]);
        }

        return messages;
    }
}
