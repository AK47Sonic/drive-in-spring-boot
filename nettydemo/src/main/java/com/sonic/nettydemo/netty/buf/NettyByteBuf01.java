package com.sonic.nettydemo.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * NettyByteBuf01
 *
 * @author Sonic
 * @since 2020/5/23
 */
public class NettyByteBuf01 {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        for (int i = 0; i < buffer.capacity(); i++) {
//            System.out.println(buffer.getByte(i));
            System.out.println(buffer.readByte());
        }

    }


}
