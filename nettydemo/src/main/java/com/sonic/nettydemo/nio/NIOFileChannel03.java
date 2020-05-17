package com.sonic.nettydemo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIOFileChannel03
 *
 * @author Sonic
 * @since 2020/5/16
 */
public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("file01.txt");
        FileChannel inChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        FileOutputStream fileOutputStream = new FileOutputStream("file02.txt");
        FileChannel outChannel = fileOutputStream.getChannel();

        while (true) {

            byteBuffer.clear();
            int size = inChannel.read(byteBuffer);
            if (size == -1) {
                break;
            }
            byteBuffer.flip();

            outChannel.write(byteBuffer);

        }

        fileInputStream.close();
        fileOutputStream.close();

    }

}
