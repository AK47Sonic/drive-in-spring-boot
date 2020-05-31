package com.sonic.nettydemo.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * NettyServerHandler
 *
 * @author Sonic
 * @since 2020/5/22
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        StudentPOJO.Student stu = (StudentPOJO.Student) msg;
        System.out.println("客户端发送的数据 name=" + stu.getName());

    }


    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //Write + Flush 将数据写入缓冲，并刷新（缓存写入管道）
        //对发送数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client2", CharsetUtil.UTF_8));
//        ctx.channel().writeAndFlush()
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
