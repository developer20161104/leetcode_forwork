package basement.foundation.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: leetcode
 * @Date: 2021-04-02 10:53
 * @Author: Lab
 * @Description:
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 模拟10个用户参与业务
        try{
            for(int i=1;i<=10;i++){
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + " execute task");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
