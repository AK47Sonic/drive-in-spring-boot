package com.sonic.nettydemo.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * BIOServer
 *
 * @author Sonic
 * @since 2020/5/19
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readInt = 0;
            while (readInt != -1) {

                try {
                    readInt = socketChannel.read(byteBuffer);
                } catch (Exception e) {
                    break;
                }

                byteBuffer.rewind();

            }
        }


    }

}
