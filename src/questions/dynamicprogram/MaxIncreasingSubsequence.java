package questions.dynamicprogram;

import java.util.Arrays;

public class MaxIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // dp[i]表示的是以nums[i]结尾的最大长度
        int len = nums.length;
        int[] dp = new int[len];

        // 单个数字也满足条件
        Arrays.fill(dp,1);
        int max = dp[0];
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxIncreasingSubsequence test = new MaxIncreasingSubsequence();

        System.out.println(test.lengthOfLIS(new int[]{
                1,3,6,7,9,4,10,5,6
        }));
    }
}
