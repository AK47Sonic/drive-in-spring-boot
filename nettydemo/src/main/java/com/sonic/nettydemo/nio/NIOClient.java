package com.sonic.nettydemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIOClient
 *
 * @author Sonic
 * @since 2020/5/17
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 7000);

        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞");
            }
        }

        String str = "hello, world";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());

        socketChannel.write(byteBuffer);

        System.in.read();

    }

}
