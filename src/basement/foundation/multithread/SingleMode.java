package basement.foundation.multithread;

/**
 * @program: leetcode
 * @Date: 2021-03-31 16:08
 * @Author: Lab
 * @Description:
 */
public class SingleMode {
    // 需要禁止指令重排，防止指令在未初始化完成就执行后续操作，返回空指针
    private static volatile SingleMode instance = null;

    private SingleMode(){
        System.out.println(Thread.currentThread().getName());
    }

    // 单线程下的单例模式
    public static SingleMode getInstance(){
        if( instance == null)
            instance = new SingleMode();

        return instance;
    }

    // 双端检锁机制（DCL）
    // 进入同步代码块前后都进行判断
    // 但是因为指令重排的机制，可能存在没有初始化完成的情况
    public static SingleMode getInstanceMul(){
        if(instance == null){
            synchronized (SingleMode.class){
                if(instance == null){
                    instance = new SingleMode();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 在单线程情况下是正确的，但是多线程会出现问题
//        System.out.println(SingleMode.getInstance() == SingleMode.getInstance());

        for(int i=0;i<10;i++){
            new Thread(() ->{
                SingleMode.getInstanceMul();
            }, String.valueOf(i)).start();
        }
    }
}
