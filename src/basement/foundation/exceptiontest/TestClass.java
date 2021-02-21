package basement.foundation.exceptiontest;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/13 10:33
 * @Author: Mr.Yang
 * @Description:
 */
public class TestClass {
    public void testException(){
        try {
            //..some code that throws <span style="font-family:SimSun;">CustomerException</span></p>
            throw new CustomerException("14000001", "String[] strs's length < 4");
        } finally {
        }
    }
}

