package basement.foundation.jvmtest;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 演示元空间内存溢出
 * 需要添加虚拟机运行参数 -XX:MaxMetaspaceSize=8m
 */

// 加载类的二进制字节码
public class MetaOverflow  extends ClassLoader{
    public static void main(String[] args) {
        int j =0;

        try {
            MetaOverflow test = new MetaOverflow();
            for(int i=0;i<10000;i++,j++){
                // 生成类的二进制字节码
                ClassWriter cw= new ClassWriter(0);
                // 版本号，public，类名，包名，父类，接口
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class"+i, null, "java/lang/Object", null);

                byte[] code = cw.toByteArray();
                test.defineClass("Class"+i, code, 0, code.length);
            }
        }finally {
            System.out.println(j);
        }
    }
}
