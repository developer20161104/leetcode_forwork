package questions.hotproblem;

import java.util.HashMap;

public class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        // 先尝试前缀和
        int[] prefix= new int[nums.length+1];

        for(int i=1;i<nums.length+1;i++)
            prefix[i] = prefix[i-1]+nums[i-1];

        int count = 0;
        for(int i=0;i<nums.length+1;i++){
            for(int j=i+1;j<nums.length+1;j++){
                if(prefix[j]-prefix[i] == k)
                    count++;
            }
        }

        return count;
    }

    public int subarraySumPro(int[] nums, int k){
        // 使用map来优化查找, 只要满足 preSum-（preSum-k）==k 的区间都是符合要求的
        // 因此将所有前缀和存入map中，再在求取前缀和的过程中进行查找，获取满足要求的子数组数量
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        int prefix = 0;
        int count = 0;
        for(int num:nums){
            prefix += num;

            // preSum-（preSum-k）==k
            if(preSum.containsKey(prefix-k))
                count += preSum.get(prefix-k);

            preSum.put(prefix, preSum.getOrDefault(prefix, 0)+1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraySum test = new SubarraySum();

        System.out.println(test.subarraySumPro(new int[]{
               2
        }, 2));
    }
}
