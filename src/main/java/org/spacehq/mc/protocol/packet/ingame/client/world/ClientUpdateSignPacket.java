package org.spacehq.mc.protocol.packet.ingame.client.world;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ClientUpdateSignPacket implements Packet {

    private NetPosition netPosition;
    private String lines[];

    @SuppressWarnings("unused")
    private ClientUpdateSignPacket() {
    }

    public ClientUpdateSignPacket(NetPosition netPosition, String lines[]) {
        if(lines.length != 4) {
            throw new IllegalArgumentException("Lines must contain exactly 4 strings!");
        }

        this.netPosition = netPosition;
        this.lines = lines;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    public String[] getLines() {
        return this.lines;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.netPosition = NetUtil.readPosition(in);
        this.lines = new String[4];
        for(int count = 0; count < this.lines.length; count++) {
            this.lines[count] = in.readString();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, this.netPosition);
        for(String line : this.lines) {
            out.writeString(line);
        }
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
