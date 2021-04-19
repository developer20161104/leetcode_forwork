package basement.foundation.jvm.jvmtest;

//  StringTable位于堆中，使用哈希表实现 []
public class StringPool {
    // 常量池中的信息，都会被加载到运行时常量池中，这时 a b ab 都是常量池中的符号，还没变为 java字符串对象

    // ldc #2 会把 a 符号变为 a 字符串对象
    // ldc #3 会把 b 符号变为 b 字符串对象
    // ldc #4 会把 ab 符号变为 ab 字符串对象

    public static void main(String[] args) {
        String s1 = "a"; // 运行到改行才会将该字符转换为String对象，并存入StringTable中
        String s2 = "b";
        String s3 = "ab";

        // 创建新对象，进行对象的引用（字符串变量）
        String s4 = s1 + s2; // new StringBuilder().append("a").append("b").toString();  new String("ab")

        // 字符串常量
        String s5 = "a" + "b"; // javac在编译期间的优化，结果以及在编译期确定为ab

        // 此时串池中只有 "a" 与 "b"，没有 "ab"（动态拼接，字符串变量）
        String s6 = new String("a") + new String("b");

        // 使用 intern方法将字符串变量主动放入串池中
        // 返回串池中的对象（而不是堆中的字符串变量）
        // 因此如果串池中已经包含了该对象，获取的结果为之前的放入的对象
        String s = s6.intern();

        System.out.println(s4 == s5); // false
        System.out.println(s5 == s);  // true
        System.out.println(s == s3);  // true
        System.out.println(s == s6);  // false
    }
}
