package basement.foundation.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @program: leetcode
 * @Date: 2021-04-15 15:29
 * @Author: Lab
 * @Description: 自定义类加载器测试
 */
public class CustomClassLoader  extends ClassLoader{
    private String classPath;

    public CustomClassLoader(String classPath){
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            data = DESInstance.deCode("1234567890qwertyuiopasdf".getBytes(), data);
            return defineClass(name, data, 0, data.length);
        }catch (Exception e){
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    /**
     * 用于获取.class 的字节流
     * @param name
     * @return
     * @throws Exception
     */
    private byte[] loadByte(String name) throws Exception{
        name = name.replaceAll("\\.","/");
        FileInputStream fis = new FileInputStream(classPath+ "/"+ name + ".class");

        int len = fis.available();
        byte[] data = new byte[len];

        fis.read(data);
        fis.close();

        data = DESInstance.enCode("1234567890qwertyuiopasdf".getBytes(), data);
        return data;
    }

    public static void main(String[] args) throws Exception{
        // 自定义加载的位置，使用自定义类加载器加载
        CustomClassLoader loader = new CustomClassLoader("G:/tmp");
        // 注意包名带来的影响
        Class clazz = Class.forName("Car", true, loader);
        Object o = clazz.newInstance();

        Method m = clazz.getMethod("Print",null);
        m.invoke(o, null);
    }
}
