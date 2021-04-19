package basement.foundation.jvm.jvmtest;

/**
 * 演示 StringTable 的垃圾回收机制
 * 通过数组加链表实现（哈希表） 拉链法
 * 限制堆内存  输出串池信息 输出垃圾回收信息
 * -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
 */
public class StringTableTest {
    public static void main(String[] args) {
        int i=0;
        try {
            // 此时会发生垃圾回收，将没有使用的String对象回收掉
            for(int j=0;j<10000;j++){
                String s = String.valueOf(j).intern();
//                System.out.println(s);
                i++;
            }
        }catch (Throwable r){
            r.printStackTrace();
        }finally {
            System.out.println(i);
        }
    }
}
