package org.spacehq.mc.protocol.packet.ingame.server.entity.player;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ServerPlayerUseBedPacket implements Packet {

    private int entityId;
    private NetPosition netPosition;

    @SuppressWarnings("unused")
    private ServerPlayerUseBedPacket() {
    }

    public ServerPlayerUseBedPacket(int entityId, NetPosition netPosition) {
        this.entityId = entityId;
        this.netPosition = netPosition;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.netPosition = NetUtil.readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        NetUtil.writePosition(out, this.netPosition);
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
