package questions.hotproblem.dynamicprogram;

import java.util.Arrays;

public class CoinExchange {
    public int coinChange(int[] coins, int amount) {
        // 动态规划版本 自底向上
        if(amount < 1)
            return 0;

        Arrays.sort(coins);
        int[] dp = new int[amount+1];

        for(int i=1;i<=amount;i++){
            int min = Integer.MAX_VALUE;
            for(int coin: coins){
                // 出现不满足的直接结束内部循环
                if(coin > amount || i < coin)
                    break;
                // 此处会将溢出情况过滤掉
                if(dp[i-coin] < min)
                    min = Math.min(min, dp[i-coin]+1);
            }
            dp[i] = min;
        }

        // 最后判断会不会出现硬币比总值大的情况
        return dp[amount] == Integer.MAX_VALUE ? -1: dp[amount];
    }

    int[] memo;
    public int coinChangeDFS(int[] coins, int amount) {
        // 深度优先遍历版本，自顶向下
        if(coins.length == 0)
            return -1;

        memo = new int[amount+1];
        return dfs(coins, amount);

    }

    // 找到 amount数量的零钱可以兑换的最少硬币数
    private int dfs(int[] coins, int amount){
        if(amount <0)
            return -1;

        if(amount == 0){
            return 0;
        }

        if(memo[amount] != 0)
            return memo[amount];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, amount - coin);
            if (res >= 0 && res < min)
                min = res + 1;
        }

        memo[amount] = (min == Integer.MAX_VALUE ? -1:min);
        return memo[amount];
    }

    // 剪枝版本：甚至好于动态规划
    int ans=Integer.MAX_VALUE;
    public int coinChangeDFSCut(int[] coins, int amount){
        if(amount == 0)
            return 0;

        Arrays.sort(coins);
        dfsCut(coins, amount, coins.length-1, 0);
        return ans== Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfsCut(int[] coins, int amount,int index, int count){
        if(amount == 0)
        {
            ans = Math.min(ans, count);
            return;
        }
        if(index < 0)
            return;

        // 利用乘法加速，以及 k+count < ans 条件来剪枝
        for(int k=amount/coins[index]; k>=0 && k+count < ans; k--){
            dfsCut(coins, amount-k*coins[index], index-1, count+k);
        }

    }

    public static void main(String[] args) {
        CoinExchange test = new CoinExchange();

        System.out.println(test.coinChangeDFSCut(new int[]{
                1,2,5
        }, 11));
    }
}
