package org.spacehq.mc.protocol.packet.ingame.server.entity.spawn;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.Art;
import org.spacehq.mc.protocol.data.game.values.entity.HangingDirection;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSpawnPaintingPacket implements Packet {

    private int entityId;
    private Art art;
    private NetPosition netPosition;
    private HangingDirection direction;

    @SuppressWarnings("unused")
    private ServerSpawnPaintingPacket() {
    }

    public ServerSpawnPaintingPacket(int entityId, Art art, NetPosition netPosition, HangingDirection direction) {
        this.entityId = entityId;
        this.art = art;
        this.netPosition = netPosition;
        this.direction = direction;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public Art getArt() {
        return this.art;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    public HangingDirection getDirection() {
        return this.direction;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.art = MagicValues.key(Art.class, in.readString());
        this.netPosition = NetUtil.readPosition(in);
        this.direction = MagicValues.key(HangingDirection.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeString(MagicValues.value(String.class, this.art));
        NetUtil.writePosition(out, this.netPosition);
        out.writeByte(MagicValues.value(Integer.class, this.direction));
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
