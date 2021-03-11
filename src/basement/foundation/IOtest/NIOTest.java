package basement.foundation.IOtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {
    public static void fastCopy(String src, String dist) throws IOException {
        FileInputStream fin = new FileInputStream(src);
        // 获取输入通道
        FileChannel fcnl = fin.getChannel();

        FileOutputStream out = new FileOutputStream(dist);
        // 获取输出通道
        FileChannel fout = out.getChannel();


        // 缓冲区分配
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(true){
            // 将通道数据读取到缓冲区
            int r = fcnl.read(buffer);

            if(r == -1)
                break;

            // 切换读写
            buffer.flip();

            // 将缓冲区的内容写入文件
            fout.write(buffer);

            buffer.clear();
        }
    }

    public static void main(String[] args) {
        String src = "src\\basement\\foundation\\IOtest\\file\\test.txt";
        String out = "src\\basement\\foundation\\IOtest\\file\\dis.txt";


        try {
            fastCopy(src,out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
