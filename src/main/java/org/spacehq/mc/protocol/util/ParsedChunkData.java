package org.spacehq.mc.protocol.util;

import org.spacehq.mc.protocol.data.game.NetChunk;

import java.util.Arrays;

public class ParsedChunkData {

    private NetChunk netChunks[];
    private byte biomes[];

    public ParsedChunkData(NetChunk chunks[], byte biomes[]) {
        this.netChunks = chunks;
        this.biomes = biomes;
    }

    public NetChunk[] getChunks() {
        return this.netChunks;
    }

    public byte[] getBiomes() {
        return this.biomes;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ParsedChunkData that = (ParsedChunkData) o;

        if(!Arrays.equals(biomes, that.biomes)) return false;
        if(!Arrays.equals(netChunks, that.netChunks)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(netChunks);
        result = 31 * result + (biomes != null ? Arrays.hashCode(biomes) : 0);
        return result;
    }

}
