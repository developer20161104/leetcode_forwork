package basement.foundation.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/14 22:29
 * @Author: Mr.Yang
 * @Description:
 */
public class ReentranLockExample {
    private Lock lock = new ReentrantLock();

    public void func(){
        lock.lock();
        try{
            for(int i=0;i<10;i++)
                System.out.println(i + " ");
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ReentranLockExample test = new ReentranLockExample();
        ReentranLockExample test2 = new ReentranLockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();


        executorService.execute(()->test.func());
        executorService.execute(()->test2.func());
    }
}
