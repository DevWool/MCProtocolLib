package org.spacehq.mc.protocol.packet.ingame.server.world;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSpawnPositionPacket implements Packet {

    private NetPosition netPosition;

    @SuppressWarnings("unused")
    private ServerSpawnPositionPacket() {
    }

    public ServerSpawnPositionPacket(NetPosition netPosition) {
        this.netPosition = netPosition;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.netPosition = NetUtil.readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, this.netPosition);
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
