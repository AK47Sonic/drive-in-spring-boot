package com.sonic.nettydemo.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * NettyByteBuf02
 *
 * @author Sonic
 * @since 2020/5/23
 */
public class NettyByteBuf02 {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,world", CharsetUtil.UTF_8);

        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            System.out.println(new String(array, CharsetUtil.UTF_8));
        }
    }
}
