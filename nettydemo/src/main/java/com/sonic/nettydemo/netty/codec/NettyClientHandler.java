package com.sonic.nettydemo.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * NettyClientHandler
 *
 * @author Sonic
 * @since 2020/5/22
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    // 当通道就绪就会触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(1).setName("baozitou").build();
        ctx.writeAndFlush(student);
    }

    // 当通道有读取事件时，会触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Receive msg: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Server address: " +  ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
