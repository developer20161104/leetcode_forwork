package basement.foundation.multithread;

/**
 * @program: leetcode
 * @Date: 2021-04-05 09:56
 * @Author: Lab
 * @Description: 死锁的样例
 */
class HoldLockThread implements Runnable{
    private String a;
    private String b;

    public HoldLockThread(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (a){
            System.out.println(Thread.currentThread().getName()+ " 持有A锁，想要获得B锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (b){
                System.out.println(Thread.currentThread().getName()+ " 持有B锁，想要获得A锁");
            }

        }
    }
}


public class DeadLockDemo {

    public static void main(String[] args) {
        String a = "lockA";
        String b = "lockB";

        // 线程1持有a锁，想要获取b锁
        new Thread(new HoldLockThread(a,b), "ThreadA").start();
        // 线程2持有b锁，想要获取a锁，此时会产生死锁问题
        new Thread(new HoldLockThread(b,a), "ThreadB").start();
    }
}
