package com.sonic.nettydemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIOServer
 *
 * @author Sonic
 * @since 2020/5/17
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.socket().bind(inetSocketAddress);

        Selector selector = Selector.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) == 0) { // 没有时间发生
                System.out.println("服务器等待1秒，无连接");
                continue;
            }
            // 获取到关注的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) { // 对应事件
                    // 当有事件驱动时，此时accept已经不会阻塞，因为一定有一个连接存在
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } else if (key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    channel.read(byteBuffer);
                    System.out.println("from 客户端 " + new String(byteBuffer.array()));
                } else if (key.isConnectable()){
                    System.out.println("----------Connect----------------");
                }
                // 要移除，否则会保留到下一次循环, selector.select时候会重复获得
                keyIterator.remove();
            }
        }







//        socketChannel.register(selector, );

    }

}
