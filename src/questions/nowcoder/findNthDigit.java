package questions.nowcoder;

/**
 * @program: leetcode
 * @Date: 2021-04-17 11:30
 * @Author: Lab
 * @Description:
 */
public class findNthDigit {
    public int findNthDigits(int n) {
        if(n < 10){
            return n;
        }

        long count = 9;
        long tail = 1;
        int sort = 1;
        while(n > count){
            n -= count;
            tail *=10; sort++;

            count = 9*tail*sort;
        }

        int index = (n-1)%sort;
        String val = (tail+(n-1)/sort)+"";

        return Integer.parseInt(val.charAt(index)+"");
    }

    public static void main(String[] args) {
        findNthDigit test =new findNthDigit();

        System.out.println(test.findNthDigits(190));
    }
}
