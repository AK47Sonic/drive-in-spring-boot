package com.sonic.nettydemo.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * BIOClient
 *
 * @author Sonic
 * @since 2020/5/19
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        // 文件大小
        long size = fileChannel.size();
        long start = 0L;
        while (size > 0) {
            long completedByte = fileChannel.transferTo(start, size, socketChannel);
            start += completedByte;
            size -= completedByte;
        }
        fileChannel.close();

    }

}
