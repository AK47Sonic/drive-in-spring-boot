package com.sonic.nettydemo.nio;



import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIOFileChannel02
 *
 * @author Sonic
 * @since 2020/5/16
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("file01.txt");
        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        channel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();


    }

}
