package com.sonic.nettydemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * ScatteringAndGathering
 *
 * @author Sonic
 * @since 2020/5/17
 */
public class ScatteringAndGathering {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress socketAddress = new InetSocketAddress(7000);

        serverSocketChannel.socket().bind(socketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 8;

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("read: " + byteRead);

                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "postiton=" + buffer.position() + ",limit=" + buffer.limit())
                        .forEach(System.out::println);

                Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

                long byteWrite = 0;
                if (byteWrite < messageLength) {
                    long l = socketChannel.write(byteBuffers);
                    byteWrite += l;
                }

                Arrays.asList(byteBuffers).forEach(buffer -> {
                    buffer.clear();
                });

                System.out.println("byteRead=" + byteRead + " byteWrite=" + byteWrite + " messageLength=" + messageLength);

            }
        }

    }

}
