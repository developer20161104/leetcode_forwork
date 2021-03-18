package questions.hotproblem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/3 11:05
 * @Author: Mr.Yang
 * @Description:
 */
public class MultiNumber {
    public int majorityElement(int[] nums) {
        // 首先想到的是哈希表法
        HashMap<Integer, Integer> maps = new HashMap<>();
        for(int num : nums) {
            if(maps.containsKey(num))
                maps.put(num, maps.get(num)+1);
            else
                maps.put(num, 1);
        }

        // 通过链式调用，首先得到的是一个Optional对象，封装的是 Map.entry<Integer,Integer>对象，然后从中返回其中的key值
        return maps.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    public int majorityElement2(int[] nums){
        // 摩尔投票法
        // 初始化被选举人，后续出现相同时增一，不同减一，
        // 如果被选举人票数为0时，更换并置一

        int canNum = nums[0], number = 1;
        for(int i=1;i<nums.length;i++){
            number += canNum == nums[i] ? 1:-1;

            if(number == 0){
                canNum = nums[i];
                number = 1;
            }
        }

        return canNum;
    }

    public static void main(String[] args) {
        MultiNumber test = new MultiNumber();

        System.out.println(test.majorityElement(new int[]{2,2,1,1,1,2,2}));

        System.out.println(test.majorityElement2(new int[]{2,2,1,1,1,2,2}));
    }
}
