package basement.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: leetcode_forwork
 * @Date: 2021/1/17 21:27
 * @Author: Mr.Yang
 * @Description: first temptation
 */
public class HelloWorld {
    public static int classVariable;
    public int exampleVariable;

    public static void main(String[] args) {
        /*
        test multiple rows
         */
        // local variable need to be initialized
        int localVariable = 0;
        System.out.println("\uabcd\ubcdf");
    
        HelloWorld example = new HelloWorld();
        System.out.println(classVariable + " " +example.exampleVariable + " " + localVariable);


        // 当空间为2的指数次方才能转换
        System.out.println( 1078 & (16-1));
        System.out.println(1078%16);

        List<Integer> tes = new ArrayList<>(Arrays.asList(1,null,2));
        tes.add(123);

        tes.stream().forEach(System.out::println);
    }
}
