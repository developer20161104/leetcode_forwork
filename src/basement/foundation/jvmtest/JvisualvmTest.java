package basement.foundation.jvmtest;

import java.util.ArrayList;
import java.util.List;

public class JvisualvmTest {
    // 使用jvisualvm工具来进行代码的分析，找到问题的来源
    public static void main(String[] args) throws InterruptedException {
        List<Student> arr = new ArrayList<>();

        for(int i=0;i<200;i++){
            arr.add(new Student());
        }
        Thread.sleep(1000000L);
    }

}


class Student{
    private byte[] big = new byte[1024*1024];
}