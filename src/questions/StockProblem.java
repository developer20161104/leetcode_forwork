package questions;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/1/26 19:21
 * @Author: Mr.Yang
 * @Description:
 */
public class StockProblem {
    // 只能购买一次
    public int maxProfit(int[] prices) {
        // dp[i][0] 表示第i天不持有股票时的最佳收益 = max(dp[i-1][0], dp[i-1][1]+price[i]
        // dp[i][1] 表示第i天持有股票时的最佳收益 = max(dp[i-1][0], -price[i])

        // 时间复杂度为O(n), 空间复杂度为O(n)
        int length = prices.length;
        if(length < 2)
            return 0;

//        int[][] dp = new int[length][2];
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//
//        int i=1;
//        for(;i<length;i++){
//            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
//            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
//        }
//
//        return dp[i-1][0];

        int notOwn = 0, own = -prices[0];
        for(int i=1;i<length;++i){
            notOwn = Math.max(notOwn, own+prices[i]);
            own = Math.max(own, -prices[i]);
        }

        return notOwn;
    }

    // 可以买卖多次，取最后的最大即可
    public int maxProfitWithoutTimesLimit(int[] prices) {
        // 与只能买一次相比，此处需要进行累加，因此状态转移方程有些许变化
        int length = prices.length;
        if(length < 2)
            return 0;

//        int[][] dp = new int[length][2];
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//
//        int i=1;
//        for(; i<length;++i){
//            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
//            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
//        }
//
//        return dp[i-1][0];

        int notOwn = 0, own = -prices[0];
        for(int i=1;i<length;++i){
            notOwn = Math.max(notOwn, own+prices[i]);
            own = Math.max(own, notOwn-prices[i]);
        }

        return notOwn;
    }

    // 限制只能购买两次
    public int maxProfitTwoTimes(int[] prices) {
        // 大体思想没错，就是状态数量没有把握好
        // 此时有三个状态，分别为  天数，是否持有，购买次数
        int length = prices.length;
        if(length < 2)
            return 0;
//
//        int[][][] dp = new int[length][2][2];
//        dp[0][0][0] = 0;
//        dp[0][0][1] = -prices[0];
//        dp[0][1][0] = 0;
//        dp[0][1][1] = 0;
//
//        int i = 1;
//        for(;i<length;i++){
//            dp[i][0][0] = dp[i-1][0][0];
//            dp[i][0][1] = Math.max(dp[i-1][0][1], -prices[i]);
//            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][0][1]+prices[i]);
//            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][1][0]-prices[i]);
//        }
//
//        return Math.max(dp[i-1][1][0], dp[i-1][0][0]);

        // 一共包含5中情况： 不动，买1，卖1，买2，卖2
        // 可通过
//        int[][] dp = new int[length][5];
//        dp[0][1] = -prices[0];
//        dp[0][3] = -prices[0];
//
//        int i=1;
//        for(;i<length;i++){
//            // 第一个状态保持不变
//            dp[i][0] = dp[i-1][0];
//            // 第一次买入比较的是不变的情况与买入的情况，后面依次类推
//            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
//            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]);
//            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i]);
//            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]);
//        }
//
//        return dp[i-1][4];

        int firstBuy = -prices[0], firstSell = 0;
        int secondBuy = -prices[0], secondSell = 0;

        for(int i=1;i<length;++i){
            firstBuy = Math.max(firstBuy, -prices[i]);
            firstSell = Math.max(firstSell, firstBuy+prices[i]);
            secondBuy = Math.max(secondBuy, firstSell-prices[i]);
            secondSell = Math.max(secondSell, secondBuy+prices[i]);
        }

        return secondSell;
    }

    public int maxProfitKTimes(int k, int[] prices) {
        // 需要注意的地方：
        // 购买的次数约束，第一次购买的特殊情况，最后最大的收益不一定得用完所有的次数
//        int length = prices.length;
//        if(length < 2)
//            return 0;
//
//        // 购买的次数也需要进行约束
//        k = Math.min(k, length/2);
//        int[][][] dp = new int[length][k+1][2];
//
//        dp[0][0][0] = 0;
//        for(int p=1;p<=k;p++)
////            dp[0][p][1] = -prices[0];
//            dp[0][p][1] = dp[0][p][0] = Integer.MIN_VALUE/2;
//
//        int i=1;
//        for(;i<length;++i){
//            dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][0][0]-prices[i]);
//            for(int j=1;j<=k;j++){
//                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j][0] - prices[i]);
//                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j-1][1] + prices[i]);
//            }
//        }
//
//        // 不一定是全部用完的最大
//        int maxProfit = 0;
//        for(int p=0;p<=k;p++)
//            maxProfit = Math.max(maxProfit, dp[i-1][p][0]);
//
//        return maxProfit;

        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        // 流操作值得看看
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    public int maxProfitWithFreezing(int[] prices) {
        int n = prices.length;
        if(n < 2)
            return 0;

//        // 一共会有三种状态，本来不持有，进入冷冻期不持有和持有股票
//        int[][] dp = new int[n][3];
//        dp[0][2] = -prices[0];
//
//        for(int i=1;i<n;i++){
//            // 本来不持有会有两种情况， 原来不持有或者前面一天是冷冻期不持有
//            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
//            // 进入冷冻期不持有只可能是前面一天为持有，今天卖出去
//            dp[i][1] = dp[i-1][2] + prices[i];
//            // 当前持有则可能是前面一天也持有，或者前面一天买入（不能从冷冻期买入）
//            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][0] - prices[i]);
//        }
//
//        // 最后判断两种情况的最大值
//        return Math.max(dp[n-1][0], dp[n-1][1]);

        // 两种状态的解法: 此为最初的想法
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);

        for(int i=2;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }

        return dp[n-1][0];
    }

    public int maxProfitWithFee(int[] prices, int fee){
        int n = prices.length;
        if(n < 2)
            return 0;

        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];

        for(int i=1; i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] +prices[i] -fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] -prices[i]);
        }

        return dp[n-1][0];
    }

    public static void main(String[] args) {
        StockProblem test = new StockProblem();

        System.out.println(test.maxProfit(new int[]{7,6,4,3,1}));

        System.out.println(test.maxProfitWithoutTimesLimit(new int[]{7,1,5,3,6,4}));

        System.out.println(test.maxProfitTwoTimes(new int[]{3,3,5,0,0,3,1,4}));

        int k = 2;
        System.out.println(test.maxProfitKTimes(2, new int[]{3,2,6,5,0,3}));

        System.out.println(test.maxProfitWithFreezing(new int[]{1,2,3,0,2}));

        System.out.println(test.maxProfitWithFee(new int[]{1,3,2,8,4,9}, 2));
    }
}
