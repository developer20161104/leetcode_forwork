package basement.foundation.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/13 22:27
 * @Author: Mr.Yang
 * @Description:
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1234;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable test = new MyCallable();
        // 返回值由该对象封装
        FutureTask<Integer> ft = new FutureTask<Integer>(test);
        Thread t1 = new Thread(ft);

        t1.start();
        System.out.println(ft.get());
    }
}
