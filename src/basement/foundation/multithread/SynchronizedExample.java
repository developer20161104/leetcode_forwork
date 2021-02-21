package basement.foundation.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/14 22:18
 * @Author: Mr.Yang
 * @Description:
 */
public class SynchronizedExample {

    // 同步代码块
    public void func1(){
        synchronized (this){
            for(int i=0;i<10;i++)
                System.out.println(i + " ");
        }
    }

    // 同步类
    public void func2(){
        synchronized (SynchronizedExample.class){
            for(int i=0;i<10;i++)
                System.out.println(i + " ");
        }
    }

    public static void main(String[] args) {
        SynchronizedExample test = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 正确调用
        executorService.execute(() -> test.func1());
        executorService.execute(() -> test.func1());

        SynchronizedExample testNew = new SynchronizedExample();

        // 由于为不同对象，因此同步关键字失效
        executorService.execute(() -> test.func1());
        executorService.execute(() -> testNew.func1());

        // 由于同步了该类，因此该类不同的实例对象依然同步
        executorService.execute(() -> test.func2());
        executorService.execute(() -> testNew.func2());


    }
}
