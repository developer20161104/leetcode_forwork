package basement.foundation.multithread;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/14 22:02
 * @Author: Mr.Yang
 * @Description:
 */
public class Interrupt {

    private static class MyThread extends Thread{
        @Override
        public void run(){
            try{
                Thread.sleep(2000);
                System.out.println("Thread run");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        // 由于线程进入了阻塞态，此时中断则会出现异常
        t1.start();
        t1.interrupt();

        System.out.println("Main run");
    }
}
