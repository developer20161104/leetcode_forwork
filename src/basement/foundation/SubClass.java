package basement.foundation;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/3 20:27
 * @Author: Mr.Yang
 * @Description:
 */
class SuperClass{
    protected List<Integer> func() throws Throwable{
        System.out.println("hello");
        return new ArrayList<>();
    }
}

public class SubClass extends SuperClass{
    @Override
    public ArrayList<Integer> func() throws Exception{
        System.out.println("test");
        return new ArrayList<>();
    }

    public static void main(String[] args) throws Throwable {
        SubClass test = new SubClass();

        List<Integer> list = test.func();
        System.out.println();
    }
}
