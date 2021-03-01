package questions.dynamicprogram;

public class RobProblem {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len < 1)
            return 0;
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];

        for(int i=2;i<len+1;i++)
            // 关键点在于状态转移方程
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i-1]);

        return dp[len];
    }

    public static void main(String[] args) {
        RobProblem test = new RobProblem();

        System.out.println(test.rob(new int[]{0,2,1,1,10,3,5}));
    }
}
