package questions.nowcoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: leetcode
 * @Date: 2021-04-24 10:38
 * @Author: Lab
 * @Description:
 */
public class TimeOfNumber {
    public int singleNumber(int[] nums) {
//        HashSet<Long> set = new HashSet<>();
//        long sum =0;
//        for(int val: nums){
//            set.add((long) val);
//            sum+= val;
//        }
//
//        return (int) ((set.stream().reduce(Long::sum).get()*3-sum)/2);

        int[] count = new int[32];
        for(int val: nums){
            for(int i=0;i<32;i++){
                count[i] += val&1;
                val >>>= 1;
            }
        }

        int res =0, mul=1;
        for(int i=0;i< 32;i++){
            res += (count[i]%3)*mul;
            mul *=2;
        }

        return res;
    }
}
