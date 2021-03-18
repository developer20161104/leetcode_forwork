package questions.hotproblem;

import java.util.Arrays;
import java.util.HashMap;

public class CanPartition {
    // 以空间换时间
    HashMap<String, Boolean> map = new HashMap<>();
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();

        if((sum &1) != 0)
            return false;

        sum /= 2;
        return dfs(nums, 0, sum);
    }

    private boolean dfs(int[] nums, int index, int curSum){
        String key = String.valueOf(curSum)+'&'+String.valueOf(index);


        // 提供map进行记忆化搜索，减少分支的产生
        if(map.containsKey(key))
            return map.get(key);

        if(curSum == 0)
            return true;

        if(index >= nums.length || curSum < 0 )
            return false;

        // 包含累加与不进行累加的情况
        boolean res = dfs(nums, index+1, curSum-nums[index]) || dfs(nums, index+1, curSum);
        map.put(key, res);
        return res;
    }

    public boolean canPartitionDP(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if((sum &1) != 0)
            return false;

        sum /= 2;

        // 定义dp为数字nums[i]时能否存在内部元素和为j的情况
        boolean[][] dp = new boolean[nums.length][sum+1];
        if(nums[0] < sum){
            dp[0][nums[0]] = true;
        }

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<=sum;j++){
                // 先复制后面进行修正
//                dp[i][j] = dp[i-1][j];

                // 直接相等置为true
                if(nums[i] == j){
                    dp[i][j] = true;
                    continue;
                }

                // 当前数字较小时，可以进行选择与不选择两个操作
                if(nums[i] < j){
                    // 包含选择与不选择两个部分
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }

        return dp[nums.length-1][sum];
    }


    public static void main(String[] args) {
        CanPartition test = new CanPartition();

        System.out.println(test.canPartitionDP(new int[]{
            1,1,2,2
        }));
    }
}
