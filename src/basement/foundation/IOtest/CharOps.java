package basement.foundation.IOtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CharOps {

    public static void copyFile(String src, String dist) throws IOException{
        // 不接收File对象也行
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out  = new FileOutputStream(dist);

        byte[] buffer  = new byte[20*1024];
        int cnt;

        while((cnt = in.read(buffer, 0, buffer.length)) != -1){
            out.write(buffer, 0, cnt);
        }

        in.close();
        out.close();
    }

    public static void main(String[] args) {
        String src = "src\\basement\\foundation\\IOtest\\file\\test.txt";
        String out = "src\\basement\\foundation\\IOtest\\file\\dis.txt";
        try {
            copyFile(src, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
