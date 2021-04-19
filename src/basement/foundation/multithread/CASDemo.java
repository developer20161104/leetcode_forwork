package basement.foundation.multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: leetcode
 * @Date: 2021-03-31 16:49
 * @Author: Lab
 * @Description: CAS 比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // Java 中的实现方法
        System.out.println(atomicInteger.compareAndSet(5, 2021));
        System.out.println(atomicInteger.get());

        // 期望值与真实值不一致，因此修改失败
        System.out.println(atomicInteger.compareAndSet(5, 1024));
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
