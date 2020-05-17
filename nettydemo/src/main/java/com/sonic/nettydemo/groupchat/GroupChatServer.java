package com.sonic.nettydemo.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * GroupChatServer
 *
 * @author Sonic
 * @since 2020/5/17
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    private Map<String, SocketChannel> clientMap = new HashMap();

    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {

        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线");
//                            clientMap.put(getClientName(socketChannel), socketChannel);

                        } else if (selectionKey.isReadable()) {
                            readData(selectionKey);
                        }
                    }

                    //移除key
                    iterator.remove();
                } else {
                    System.out.println("等待。。。");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void readData(SelectionKey selectionKey) {

        SocketChannel channel = null;
        try {
            channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 当客户端离线时候，read会抛远程客户端连接中断异常
            int count = channel.read(buffer);

            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("From 客户端：" + msg);

                // 向其他客户端转发消息
                sendInfoToOtherClients(msg, channel);

            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了。。。");
                System.out.println("Before cancel:" + selector.keys().size());
                selectionKey.cancel();
                System.out.println("After cancel:" + selector.keys().size());
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {

        System.out.println("服务器转发消息...");
        for (SelectionKey key : selector.keys()) {
            System.out.println("-----------" + key.channel().getClass());
            SelectableChannel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(byteBuffer);
            }
        }

//            SocketChannel targetChannel = (SocketChannel) key.channel();
//            if (targetChannel instanceof SocketChannel && targetChannel != self) {
//        Iterator<SocketChannel> iterator = clientMap.values().iterator();
//        while (iterator.hasNext()) {
//            SocketChannel dest = iterator.next();
//            ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
//            dest.write(byteBuffer);
//        }
//                SocketChannel dest = (SocketChannel) targetChannel;
//                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
//                dest.write(byteBuffer);
//            }
//        }

    }

    private String getClientName(SocketChannel client) {
        Socket socket = client.socket();
        return "[" + socket.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }

}
