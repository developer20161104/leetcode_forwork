package basement.foundation.sockettest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: leetcode
 * @Date: 2021-03-24 09:11
 * @Author: Lab
 * @Description:
 */
public class SocketServer{
    public static void main(String[] args) throws IOException {
        int port = 55533;

        ServerSocket server = new ServerSocket(port);

        System.out.println("阻塞式会一直等待连接的到来");
        // 开始监听
        Socket socket = server.accept();

        // 连接建立后，从socket中获取输入流，建立连接缓冲区
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        StringBuilder str = new StringBuilder();

        // 通过buffer接收字节流
        while((len = inputStream.read(buffer)) != -1){
            str.append(new String(buffer,0, len, "UTF-8"));
        }

        System.out.println("Get messages from client: " + str);
        inputStream.close();
        socket.close();
        server.close();
    }
}
