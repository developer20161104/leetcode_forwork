package basement.foundation.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/24 22:20
 * @Author: Mr.Yang
 * @Description:
 */
public class Cooperation {
    /**
     *  join() 会等待被调用的线程完成才会继续进行后续的操作
     */
    private class A extends Thread{
        @Override
        public void run(){
            System.out.println("A");
        }
    }

    private class B extends Thread{
        private A a;

        B(A a){
            this.a = a;
        }

        @Override
        public void run(){
            try{
                // 等待a执行完成才能进行后续步骤
                a.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("B");
        }
    }


    public void test(){
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }


    /**
     *  wait() 将线程挂起等待满足条件
     *  notify() / notifyAll() 唤醒挂起的进程
     */
    public synchronized void before(){
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after(){
        try {
            wait();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("After");
    }


    public static void main(String[] args) {
        Cooperation test = new Cooperation();
//        test.test();

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 由于wait()将先运行的线程挂起，所以先执行后续的方法
        executorService.execute(()->test.after());
        executorService.execute(()->test.before());
    }
}
