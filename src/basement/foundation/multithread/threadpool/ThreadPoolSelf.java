package basement.foundation.multithread.threadpool;

import java.util.concurrent.*;

/**
 * @program: leetcode
 * @Date: 2021-04-04 23:12
 * @Author: Lab
 * @Description: 自己实现线程池
 */
public class ThreadPoolSelf {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
               new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try{
            // 允许的最多任务数 线程池允许的最大线程数+阻塞队列容量
            // 出现数量爆炸时使用拒绝策略
            for(int i=0;i<10;i++){
                 threadPool.execute(() ->{
                     System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                 });

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }

}
