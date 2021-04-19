package basement.foundation.jvm;

/**
 * @program: leetcode
 * @Date: 2021-04-05 15:23
 * @Author: Lab
 * @Description: 查看
 */
public class HelloGC {

    public static void main(String[] args) {
//        System.out.println("ajhwd");
//        try {
//            Thread.sleep(40*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println(totalMemory/(double) 1024 / 1024);
        System.out.println(maxMemory/(double) 1024 / 1024);
    }
}
