package questions.problemperday;

/**
 * @program: leetcode
 * @Date: 2021-06-07 09:16
 * @Author: Lab
 * @Description:
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int val:nums){
            sum += val;
        }

        // 进行问题的转化
        int diff = sum-target;
        if(sum < target || diff%2 == 1){
            return 0;
        }

        int n = nums.length, neg = diff/2;
        // 在数组的前i个数中选取，使得元素和为j的方案数
        int[][] dp = new int[n+1][neg+1];
        dp[0][0] = 1;

        for(int i=1;i<=n;i++){
            int num = nums[i-1];

            for(int j=0;j<=neg;j++){
                // 一种做法是不选择当前位置的元素，因此方案数等于之前的数量
                dp[i][j] = dp[i-1][j];

                // 第二种则是选择当前位置元素，则方案数需要进行累加
                if(j >= num){
                    dp[i][j] += dp[i-1][j-num];
                }
            }

        }

        return dp[n][neg];
    }
}
