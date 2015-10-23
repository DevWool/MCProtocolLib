package org.spacehq.mc.protocol.packet.ingame.server.world;

import org.spacehq.mc.protocol.data.game.NetChunk;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.mc.protocol.util.NetworkChunkData;
import org.spacehq.mc.protocol.util.ParsedChunkData;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

public class ServerChunkDataPacket implements Packet {

    private int x;
    private int z;
    private NetChunk netChunks[];
    private byte biomeData[];

    @SuppressWarnings("unused")
    private ServerChunkDataPacket() {
    }

    /**
     * Convenience constructor for creating a packet to unload netChunks.
     *
     * @param x X of the chunk column.
     * @param z Z of the chunk column.
     */
    public ServerChunkDataPacket(int x, int z) {
        this(x, z, new NetChunk[16], new byte[256]);
    }

    /**
     * Constructs a ServerChunkDataPacket for updating netChunks.
     *
     * @param x      X of the chunk column.
     * @param z      Z of the chunk column.
     * @param netChunks Array of netChunks in the column. Length must be 16 but can contain null values.
     * @throws IllegalArgumentException If the chunk array length is not 16 or skylight arrays exist in some but not all netChunks.
     */
    public ServerChunkDataPacket(int x, int z, NetChunk chunks[]) {
        this(x, z, chunks, null);
    }

    /**
     * Constructs a ServerChunkDataPacket for updating a full column of netChunks.
     *
     * @param x         X of the chunk column.
     * @param z         Z of the chunk column.
     * @param netChunks    Array of netChunks in the column. Length must be 16 but can contain null values.
     * @param biomeData Array of biome data for the column.
     * @throws IllegalArgumentException If the chunk array length is not 16 or skylight arrays exist in some but not all netChunks.
     */
    public ServerChunkDataPacket(int x, int z, NetChunk chunks[], byte biomeData[]) {
        if(chunks.length != 16) {
            throw new IllegalArgumentException("Chunks length must be 16.");
        }

        boolean noSkylight = false;
        boolean skylight = false;
        for(int index = 0; index < chunks.length; index++) {
            if(chunks[index] != null) {
                if(chunks[index].getSkyLight() == null) {
                    noSkylight = true;
                } else {
                    skylight = true;
                }
            }
        }

        if(noSkylight && skylight) {
            throw new IllegalArgumentException("Either all netChunks must have skylight values or none must have them.");
        }

        this.x = x;
        this.z = z;
        this.netChunks = chunks;
        this.biomeData = biomeData;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public NetChunk[] getChunks() {
        return this.netChunks;
    }

    public byte[] getBiomeData() {
        return this.biomeData;
    }

    public boolean isFullChunk() {
        return this.biomeData != null;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.x = in.readInt();
        this.z = in.readInt();
        boolean fullChunk = in.readBoolean();
        int chunkMask = in.readUnsignedShort();
        byte data[] = in.readBytes(in.readVarInt());
        ParsedChunkData chunkData = NetUtil.dataToChunks(new NetworkChunkData(chunkMask, fullChunk, false, data), true);
        this.netChunks = chunkData.getChunks();
        this.biomeData = chunkData.getBiomes();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetworkChunkData data = NetUtil.chunksToData(new ParsedChunkData(this.netChunks, this.biomeData));
        out.writeInt(this.x);
        out.writeInt(this.z);
        out.writeBoolean(data.isFullChunk());
        out.writeShort(data.getMask());
        out.writeVarInt(data.getData().length);
        out.writeBytes(data.getData(), data.getData().length);
    }

    @Override
    public boolean isPriority() {
        return false;
    }

}
