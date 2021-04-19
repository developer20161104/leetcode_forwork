package basement.foundation.jvm.jvmtest;

// 禁用显式回收的方式 -XX:+DisableExplicitGC
public class HeapOverflow {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello world");
        System.out.println("1..");
        Thread.sleep(10000);
        byte[] arr = new byte[1024*1024*10];

        System.out.println("2..");
        Thread.sleep(10000);
        arr = null;

        // 系统的显式回收 Full GC，比较影响性能
        System.gc();
        System.out.println("3...");
        Thread.sleep(100000L);
    }
}
