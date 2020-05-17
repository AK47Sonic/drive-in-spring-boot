package com.sonic.nettydemo.nio;

import java.nio.IntBuffer;

/**
 * BasicBuffer
 *
 * @author Sonic
 * @since 2020/5/16
 */
public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5); // 可以存放5个字节

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);

        }

        intBuffer.flip(); // 读写切换

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

}
