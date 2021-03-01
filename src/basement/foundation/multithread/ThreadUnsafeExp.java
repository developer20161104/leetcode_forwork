package basement.foundation.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUnsafeExp {
    private  int cnt =0;

    public void add(){
        this.cnt++;
    }

    public int getCnt(){
        return this.cnt;
    }


    public static void main(String[] args) throws InterruptedException {
        final int threasize = 1000;
        ThreadUnsafeExp test = new ThreadUnsafeExp();

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 加入这个防止主线程在子线程没执行完成就返回结果了
        final CountDownLatch countDownLatch = new CountDownLatch(threasize);

        for(int i=0;i<threasize;i++){
            executorService.execute(() ->{
                test.add();
                countDownLatch.countDown();
            });
        }

        executorService.shutdown();
        countDownLatch.await();
        System.out.println(test.getCnt());
    }
}
