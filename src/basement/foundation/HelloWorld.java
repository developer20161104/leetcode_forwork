package basement.foundation;

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
    }
}
