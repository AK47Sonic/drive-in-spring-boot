package com.sonic.nettydemo.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * NettyClient
 *
 * @author Sonic
 * @since 2020/5/22
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("Client is OK...");

            //启动客户端连接服务器端
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6668).sync();
            cf.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }

    }

}
