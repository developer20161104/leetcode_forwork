package basement.foundation.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/6 16:43
 * @Author: Mr.Yang
 * @Description:
 */
public class Generic {
    public static void main(String[] args) {
        List<String> t1 = new ArrayList<>();
        List<Integer> t2 = new ArrayList<>();

        Class classt1 = t1.getClass();
        Class classt2 = t1.getClass();

        // 系统输出true则表示两者在编译后是相同的
        if(classt1.equals(classt2))
            System.out.println("true");
        else
            System.out.println("false");
    }
}
