package com.sonic.nettydemo.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * TestServerInitalizer
 *
 * @author Sonic
 * @since 2020/5/23
 */
public class TestServerInitalizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());

    }
}
