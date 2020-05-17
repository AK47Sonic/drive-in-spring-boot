package com.sonic.nettydemo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer
 *
 * @author Sonic
 * @since 2020/5/17
 */
public class MappedByteBuffer {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("file01.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 直接在堆外内存修改
         * size: 大小
         */
        java.nio.MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');

        randomAccessFile.close();
    }

}
