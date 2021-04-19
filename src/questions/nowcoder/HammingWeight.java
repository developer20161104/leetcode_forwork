package questions.nowcoder;

/**
 * @program: leetcode
 * @Date: 2021-04-08 10:49
 * @Author: Lab
 * @Description:
 */
public class HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            count += n%2;
            System.out.println(Integer.toBinaryString(n) + "\t\t" + count);
            n = n>>>1;
        }

        return count;
    }

    public static void main(String[] args) {
        HammingWeight test = new HammingWeight();
        int i = -31;
        String str = Integer.toBinaryString(i);

        System.out.println(test.hammingWeight(i));
    }
}
