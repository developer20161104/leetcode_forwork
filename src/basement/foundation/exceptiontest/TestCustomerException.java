package basement.foundation.exceptiontest;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/13 10:36
 * @Author: Mr.Yang
 * @Description:
 */
public class TestCustomerException {
    public static void main(String[] args) {

        try{
            TestClass test = new TestClass();
            test.testException();
        }
        catch (CustomerException e){
            e.printStackTrace();
            System.out.println(e.getMsgDes());
            System.out.println(e.getRetCd());
        }
    }
}
