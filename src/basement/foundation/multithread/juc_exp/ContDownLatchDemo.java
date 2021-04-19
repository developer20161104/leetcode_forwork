package basement.foundation.multithread.juc_exp;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @program: leetcode
 * @Date: 2021-04-02 10:16
 * @Author: Lab
 * @Description:
 */
public class ContDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i=1;i<7;i++){
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + " 国破灭");
                countDownLatch.countDown();
                // 使用枚举将数字转化为对应的字符串
            }, Objects.requireNonNull(CountyEnum.foreach_CountyEnum(i)).getRetMessage()).start();
        }

        // 等待计数为0
        countDownLatch.await();
        System.out.println("秦一统天下");
    }

}
