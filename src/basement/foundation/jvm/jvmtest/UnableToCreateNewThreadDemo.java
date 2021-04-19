package basement.foundation.jvm.jvmtest;

/**
 * @program: leetcode
 * @Date: 2021-04-12 09:20
 * @Author: Lab
 * @Description: OutOfMemoryError : unable to create new native thread
 *               一个进程创建多个线程，超过系统承载极限时，就会出现这个问题
 *               只能在linux下看到，windows会报栈溢出
 */
public class UnableToCreateNewThreadDemo {
    public static void main(String[] args) {

        for(int i=1; ;i++){
            System.out.println("*********** i= "+ i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }, ""+i).start();
        }
    }

}
