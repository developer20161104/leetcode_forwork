package questions.hotproblem.dynamicprogram;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/18 11:32
 * @Author: Mr.Yang
 * @Description:
 */
public class DifferentPath {
    public int uniquePaths(int m, int n) {
        // 使用DP
        int[][] dp = new int[m][n];

        for(int i=0;i<m;i++)
            dp[i][0] = 1;
        for(int j=0;j<n;j++)
            dp[0][j] = 1;

        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];


        return dp[m-1][n-1];
    }

    public int uniquePathsOpt(int m, int n){
        int[] pre = new int[n];
        int[] cur = new int[n];

        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                cur[j] = cur[j-1] + pre[j];
            }
        }

        return pre[n-1];
    }

    public static void main(String[] args) {
        DifferentPath test = new DifferentPath();
        System.out.println(test.uniquePathsOpt(3,3));
    }
}
