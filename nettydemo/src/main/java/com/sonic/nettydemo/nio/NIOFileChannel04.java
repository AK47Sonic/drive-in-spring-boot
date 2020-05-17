package com.sonic.nettydemo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * NIOFileChannel04
 *
 * @author Sonic
 * @since 2020/5/17
 */
public class NIOFileChannel04 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("file01.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("file04.txt");

        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
