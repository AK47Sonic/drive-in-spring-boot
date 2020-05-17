package com.sonic.nettydemo.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIOFileChannel01
 *
 * @author Sonic
 * @since 2020/5/16
 */
public class NIOFileChannel01 {

    public static void main(String[] args) throws IOException {
        String str = "hello, world";
        FileOutputStream fileOutputStream = new FileOutputStream("file01.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();

        channel.write(byteBuffer);
        fileOutputStream.close();
    }

}
