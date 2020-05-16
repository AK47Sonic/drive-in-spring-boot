package com.sonic.nettydemo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SocketServerThree
 *
 * @author Sonic
 * @since 2020/5/16
 */
public class SocketServerThree {

    public static void main(String[] args) throws IOException {
        // 指定监听端口
        int port = 55533;
        ServerSocket server = new ServerSocket(port);

        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");

        // 如果使用多线程，那就需要建立线程池，防止高并发创建过多的线程导致资源耗尽
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while (true) {
            Socket socket = server.accept();

            Runnable runnable = () -> {
                try {
                    // 连接建立好之后，从socket中获取输入流，并建立缓冲区进行读取
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {
                        // 注意编码格式，发送方和接收方要统一编码：UTF-8
                        sb.append(new String(bytes, 0, len, "UTF-8"));
                    }
                    System.out.println("get message from client:" + sb);
                    inputStream.close();

                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(runnable);
        }
    }
}
