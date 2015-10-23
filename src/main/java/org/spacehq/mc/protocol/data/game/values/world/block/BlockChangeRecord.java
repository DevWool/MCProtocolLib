package org.spacehq.mc.protocol.data.game.values.world.block;

import org.spacehq.mc.protocol.data.game.NetPosition;

public class BlockChangeRecord {

    private NetPosition netPosition;
    private int id;
    private int data;

    public BlockChangeRecord(NetPosition netPosition, int id, int data) {
        this.netPosition = netPosition;
        this.id = id;
        this.data = data;
    }

    public NetPosition getPosition() {
        return this.netPosition;
    }

    public int getId() {
        return this.id;
    }

    public int getData() {
        return this.data;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        BlockChangeRecord record = (BlockChangeRecord) o;

        if(id != record.id) return false;
        if(data != record.data) return false;
        if(!netPosition.equals(record.netPosition)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = netPosition.hashCode();
        result = 31 * result + id;
        result = 31 * result + data;
        return result;
    }

}
