package com.sonic.nettydemo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * SocketClient
 *
 * @author Sonic
 * @since 2020/5/16
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        // 需要连接的服务端IP和PORT
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message = "Hello World!";
        outputStream.write(message.getBytes("UTF-8"));
        outputStream.close();
        socket.close();
    }

}
