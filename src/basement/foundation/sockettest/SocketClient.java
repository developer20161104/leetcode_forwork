package basement.foundation.sockettest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @program: leetcode
 * @Date: 2021-03-24 09:17
 * @Author: Lab
 * @Description:
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55533;


        // 建立与服务器的连接
        Socket socket = new Socket(host, port);

        OutputStream outputStream = socket.getOutputStream();

        String msg = "hello world 这个东西";
        outputStream.write(msg.getBytes(StandardCharsets.UTF_8));

        outputStream.close();
        socket.close();
    }

}
