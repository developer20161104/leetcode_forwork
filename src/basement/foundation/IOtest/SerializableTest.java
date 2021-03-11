package basement.foundation.IOtest;

import java.io.*;

public class SerializableTest {
    private static class A implements Serializable{
        private int x;
        private String y;

        A(int x, String y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "A{" +
                    "x=" + x +
                    ", y='" + y + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        A a1 = new A(12, "ba");
        String objectFile = "src\\basement\\foundation\\IOtest\\file\\a1_object";

        // 将对象序列化并写入文件（顺序相反？）
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectFile));
        objectOutputStream.writeObject(a1);
        objectOutputStream.close();

        // 从文件读取并反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(objectFile));
        A a2 = (A) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(a2);

    }
}
