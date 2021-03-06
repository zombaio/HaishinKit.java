package com.haishinkit.rtmp.messages;

import java.nio.ByteBuffer;

import com.haishinkit.rtmp.RTMPConnection;
import com.haishinkit.rtmp.RTMPSocket;

public final class RTMPSetChunkSizeMessage extends RTMPMessage {
    private static final int CAPACITY = 4;

    private int size = 0;

    public RTMPSetChunkSizeMessage() {
        super(Type.CHUNK_SIZE);
    }

    public int getSize() {
        return size;
    }

    public RTMPSetChunkSizeMessage setSize(final int size) {
        this.size = size;
        return this;
    }

    @Override
    public ByteBuffer encode(final RTMPSocket socket) {
        if (socket == null) {
            throw new IllegalArgumentException();
        }
        ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
        buffer.putInt(getSize());
        return buffer;
    }

    @Override
    public RTMPMessage decode(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new IllegalArgumentException();
        }
        return setSize(buffer.getInt());
    }

    @Override
    public RTMPMessage execute(final RTMPConnection connection) {
        if (connection == null) {
            throw new IllegalArgumentException();
        }
        connection.getSocket().setChunkSizeC(size);
        return this;
    }
}
