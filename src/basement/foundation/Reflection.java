package basement.foundation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/3 20:56
 * @Author: Mr.Yang
 * @Description:
 */
public class Reflection {
    private int price;

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // general method
        Reflection test = new Reflection();
        test.setPrice(100);
        System.out.println(test.getPrice());

        // reflection
        // 先获取类对象实例
        Class clz = Class.forName("basement.foundation.Reflection");
        // 获取构造器对象实例
        Constructor constructor = clz.getConstructor();
        Object testObject = constructor.newInstance();

        // 获取方法对象实例并使用invoke来调用
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        setPriceMethod.invoke(testObject, 100);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println(getPriceMethod.invoke(testObject));
    }
}
