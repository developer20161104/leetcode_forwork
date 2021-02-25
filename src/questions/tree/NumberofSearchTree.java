package questions.tree;

public class NumberofSearchTree {
    public int numTrees(int n) {
        // 动态规划，关键是寻找状态转移方程
        // 注意到根节点也需要一个值，因此最后只剩下 i-1个点进行左右分配
        // 计算机求解数学归纳法
        // dp[i] = dp[i-1]*dp[0]+...+dp[0]*dp[i-1]

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        // 记录每个节点的数量
        for(int i=2;i<=n;i++){
            // 累加子状态
            for(int j=0;j<i;j++){
                dp[i] += dp[j]*dp[i-j-1];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        NumberofSearchTree test = new NumberofSearchTree();

        System.out.println(test.numTrees(4));
    }
}
