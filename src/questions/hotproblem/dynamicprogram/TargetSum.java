package questions.hotproblem.dynamicprogram;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        // 状态转移方程没想出来
        // 可能会选择减号，也可能是加号
        // dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][j-nums[i]]

        int sum=0;
        for(int val: nums)
            sum += val;

        if(Math.abs(S) > Math.abs(sum))
            return 0;

        int len = nums.length;
        // 需要包含负数，0，正数三个部分
        int total = sum*2+1;

        int[][] dp = new int[len][total];

        // 当首位为0时，必存在两种满足的情况
        if(nums[0] == 0)
            dp[0][sum] = 2;
        else {
            dp[0][sum+nums[0]] = 1;
            dp[0][sum-nums[0]] = 1;
        }

        for(int i=1;i<len;i++){
            for(int j=0;j<total;j++){
                // 边界防止数组下标越界
                int left = Math.max((j - nums[i]), 0);
                int right = (j+nums[i]) < total ? j+nums[i]:0;

                dp[i][j] = dp[i-1][left] + dp[i-1][right];
            }
        }

        return dp[len-1][sum+S];
    }



    public static void main(String[] args) {
        TargetSum test = new TargetSum();

        System.out.println(test.findTargetSumWays(new int[]{
               0,1,1,1,1
        }, 4));
    }
}
