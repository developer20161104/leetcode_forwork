package questions.hotproblem;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/1 14:12
 * @Author: Mr.Yang
 * @Description:
 */
public class OneTimeNumber {
    public int singleNumber(int[] nums) {
        // 第一思路，set存储，然后求和相减
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums)
            set.add(num);

        int sumSet = set.stream().mapToInt(Integer::intValue).sum()*2;
        int sumTot = Arrays.stream(nums).sum();

        return sumSet-sumTot;
    }

    public int singleNumber2(int[] nums){
        // 对于出现偶数次数的数字使用异或即可变成0
        // 有点难以想到
        // map 负责对每一个元素定义操作
        // reduce 负责对前后元素定义操作
        return Arrays.stream(nums).reduce(0, (finalValue, n)-> finalValue^n);
    }


    public static void main(String[] args) {
        OneTimeNumber test = new OneTimeNumber();

        System.out.println(test.singleNumber(new int[]{1,1,2}));

        System.out.println(test.singleNumber2(new int[]{1,1,2,3,3,4,4}));
    }
}
