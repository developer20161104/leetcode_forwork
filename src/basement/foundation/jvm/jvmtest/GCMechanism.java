package basement.foundation.jvm.jvmtest;

import java.util.ArrayList;

/**
 * @program: leetcode
 * @Date: 2021-03-18 09:27
 * @Author: Lab
 * @Description: 观察分代回收机制
 *               需要设置虚拟机参数： -Xms20m -Xmx20m -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
 */
public class GCMechanism {
    private static  final int _512KB = 512*1024;
    private static  final int _1MB = 1024*1024;
    private static  final int _6MB = 6* 1024*1024;
    private static  final int _7MB = 7* 1024*1024;
    private static  final int _8MB = 8* 1024*1024;

    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[_7MB]);
        list.add(new byte[_512KB]);
        list.add(new byte[_512KB]);
    }
}
