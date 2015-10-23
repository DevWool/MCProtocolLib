package org.spacehq.mc.protocol.packet.ingame.client.player;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.data.game.values.Face;
import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.entity.player.PlayerAction;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerActionPacket implements Packet {

    private PlayerAction action;
    private NetPosition netPosition;
    private Face face;

    @SuppressWarnings("unused")
    private ClientPlayerActionPacket() {
    }

    public ClientPlayerActionPacket(PlayerAction action, NetPosition netPosition, Face face) {
        this.action = action;
        this.netPosition = netPosition;
        this.face = face;
    }

    public PlayerAction getAction() {
        return this.action;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    public Face getFace() {
        return this.face;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = MagicValues.key(PlayerAction.class, in.readUnsignedByte());
        this.netPosition = NetUtil.readPosition(in);
        this.face = MagicValues.key(Face.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(MagicValues.value(Integer.class, this.action));
        NetUtil.writePosition(out, this.netPosition);
        out.writeByte(MagicValues.value(Integer.class, this.face));
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
