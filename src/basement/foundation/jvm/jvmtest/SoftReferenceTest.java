package basement.foundation.jvm.jvmtest;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: leetcode
 * @Date: 2021-03-17 10:25
 * @Author: Lab
 * @Description: 软引用案例 使用 -Xmx20m -XX:+PrintGCDetails -verbose:gc
 */
public class SoftReferenceTest {
    private static final int _4MB = 4*1024*1024;

    public static void main(String[] args) throws IOException{
//        List<byte[]> list = new ArrayList<>();
//
//        for(int i=0;i<5;i++){
//            // 创建强引用不会被回收，此时需要引入软引用
//            list.add(new byte[_4MB]);
//        }

        List<SoftReference<byte[]>> list = new ArrayList<>();

        // 通过引用队列来将弱引用对象回收
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        for (int i=0;i<5;i++){
            SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB]);
            System.out.println(ref.get());
            list.add(ref);

            System.out.println(list.size());
        }

        // 先将队列中获取的引用提取出来，再进行判断
        Reference<? extends byte[]> poll = queue.poll();
        while(poll!=null){
            list.remove(poll);
            poll = queue.poll();
        }

        // 使用软引用，前面的3条都会被回收掉，但是软引用对象本身不会被
        for(SoftReference<byte[]> ref : list)
            System.out.println(ref.get());
    }
}
