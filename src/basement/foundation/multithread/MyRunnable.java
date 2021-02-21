package basement.foundation.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/13 21:38
 * @Author: Mr.Yang
 * @Description:
 */
public class MyRunnable implements Runnable{
    private int i;

    // 该接口无返回值
    @Override
    public void run() {
        System.out.println("this time "+ (i+1));
        this.i++;
    }

    public static void main(String[] args) {
//        MyRunnable test = new MyRunnable();
//        Thread thread = new Thread(test);
//        Thread thread1 = new Thread(test);
//
//        thread.start();
//        thread1.start();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<5;i++){
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
    }
}
