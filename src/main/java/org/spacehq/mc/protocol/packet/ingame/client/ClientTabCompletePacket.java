package org.spacehq.mc.protocol.packet.ingame.client;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ClientTabCompletePacket implements Packet {

    private String text;
    private NetPosition netPosition;

    @SuppressWarnings("unused")
    private ClientTabCompletePacket() {
    }

    public ClientTabCompletePacket(String text) {
        this(text, null);
    }

    public ClientTabCompletePacket(String text, NetPosition netPosition) {
        this.text = text;
        this.netPosition = netPosition;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.text = in.readString();
        this.netPosition = in.readBoolean() ? NetUtil.readPosition(in) : null;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.text);
        out.writeBoolean(this.netPosition != null);
        if(this.netPosition != null) {
            NetUtil.writePosition(out, this.netPosition);
        }
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
