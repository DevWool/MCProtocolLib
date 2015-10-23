package org.spacehq.mc.protocol.data.game;

public class NetPosition {

    private int x;
    private int y;
    private int z;

    public NetPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        NetPosition netPosition = (NetPosition) o;

        if(x != netPosition.x) return false;
        if(y != netPosition.y) return false;
        if(z != netPosition.z) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

}
