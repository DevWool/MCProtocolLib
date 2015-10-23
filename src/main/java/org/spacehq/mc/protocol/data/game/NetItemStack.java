package org.spacehq.mc.protocol.data.game;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

public class NetItemStack {

    private int id;
    private int amount;
    private int data;
    private CompoundTag nbt;

    public NetItemStack(int id) {
        this(id, 1);
    }

    public NetItemStack(int id, int amount) {
        this(id, amount, 0);
    }

    public NetItemStack(int id, int amount, int data) {
        this(id, amount, data, null);
    }

    public NetItemStack(int id, int amount, int data, CompoundTag nbt) {
        this.id = id;
        this.amount = amount;
        this.data = data;
        this.nbt = nbt;
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getData() {
        return this.data;
    }

    public CompoundTag getNBT() {
        return this.nbt;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        NetItemStack netItemStack = (NetItemStack) o;

        if(amount != netItemStack.amount) return false;
        if(data != netItemStack.data) return false;
        if(id != netItemStack.id) return false;
        if(nbt != null ? !nbt.equals(netItemStack.nbt) : netItemStack.nbt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        result = 31 * result + data;
        result = 31 * result + (nbt != null ? nbt.hashCode() : 0);
        return result;
    }

}
