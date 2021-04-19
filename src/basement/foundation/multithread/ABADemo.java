package basement.foundation.multithread;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: leetcode
 * @Date: 2021-03-31 18:05
 * @Author: Lab
 * @Description: 解决ABA问题的方式
 *               使用 AtomicStampedReference类
 */
public class ABADemo {

    // 不加时间戳，无法防止ABA问题
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    // 增加时间戳（版本号），防止ABA问题
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);

            },"t1").start();


        new Thread(()->{
            // t1不一定先执行（代码重组）
            try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }

            // 此时t2会认为100没有被修改，因此会继续执行CAS操作，因此输出 true
            System.out.println(atomicReference.compareAndSet(100, 2019));
        },"t2").start();

        new Thread(()->{
            // 先睡眠一秒保证t4获取初始版本号
            try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }

            atomicStampedReference.compareAndSet(100, 101,atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println("当前版本号" + atomicStampedReference.getStamp()); // 2

            atomicStampedReference.compareAndSet(101, 100,atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println("当前版本号" + atomicStampedReference.getStamp()); // 3
        },"t3").start();


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获取初始版本号 1
            try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){ e.printStackTrace(); }

            // 此时由于时间戳不一致，因此不会执行CAS操作，因此输出 false
            System.out.println(atomicStampedReference.compareAndSet(100, 2021, stamp, stamp+1));
            System.out.println("当前版本号" + atomicStampedReference.getStamp()); // 3
            },"t4").start();

//        CopyOnWriteArraySet
    }
}
