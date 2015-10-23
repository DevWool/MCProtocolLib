package org.spacehq.mc.protocol.packet.ingame.server.world;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.player.BlockBreakStage;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ServerBlockBreakAnimPacket implements Packet {

    private int breakerEntityId;
    private NetPosition netPosition;
    private BlockBreakStage stage;

    @SuppressWarnings("unused")
    private ServerBlockBreakAnimPacket() {
    }

    public ServerBlockBreakAnimPacket(int breakerEntityId, NetPosition netPosition, BlockBreakStage stage) {
        this.breakerEntityId = breakerEntityId;
        this.netPosition = netPosition;
        this.stage = stage;
    }

    public int getBreakerEntityId() {
        return this.breakerEntityId;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    public BlockBreakStage getStage() {
        return this.stage;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.breakerEntityId = in.readVarInt();
        this.netPosition = NetUtil.readPosition(in);
        this.stage = MagicValues.key(BlockBreakStage.class, in.readUnsignedByte());
        if(this.stage == null) {
            this.stage = BlockBreakStage.RESET;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.breakerEntityId);
        NetUtil.writePosition(out, this.netPosition);
        out.writeByte(MagicValues.value(Integer.class, this.stage));
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
