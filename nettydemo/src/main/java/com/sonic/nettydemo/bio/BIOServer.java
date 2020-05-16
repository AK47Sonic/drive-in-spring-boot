package com.sonic.nettydemo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIOServer
 *
 * @author Sonic
 * @since 2020/5/16
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动");

        while (true) {
            System.out.println("server等待客户端。。。。。");
            final Socket socket = serverSocket.accept();
            ExecutorService executorService = Executors.newCachedThreadPool();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start a thread: " + Thread.currentThread().getName());
                    handler(socket);
                    System.out.println("end a thread: " + Thread.currentThread().getName());
                }
            });

            executorService.shutdown();
//            break;

        }

    }

    private static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                 if(read != -1) {
                     System.out.println("数据：" + new String(bytes, 0, read));
                 } else {
                     System.out.println("break");
                     break;
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
