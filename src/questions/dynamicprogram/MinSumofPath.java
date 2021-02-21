package questions.dynamicprogram;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/18 12:04
 * @Author: Mr.Yang
 * @Description:
 */
public class MinSumofPath {
    public int minPathSum(int[][] grid) {
        // dp原始版本
        // 未进行空间优化
        int rlen= grid.length, clen=grid[0].length;
        int[][] curSum = new int[rlen][clen];

        curSum[0][0] = grid[0][0];
        for(int i=1;i<rlen;i++)
            curSum[i][0] = curSum[i-1][0] + grid[i][0];
        for(int i=1;i<clen;i++)
            curSum[0][i] = curSum[0][i-1] + grid[0][i];

        for(int i=1;i<rlen;i++)
            for(int j=1;j<clen;j++)
                curSum[i][j] = grid[i][j] + Math.min(curSum[i-1][j], curSum[i][j-1]);

        return curSum[rlen-1][clen-1];
    }

    public static void main(String[] args) {
        MinSumofPath test = new MinSumofPath();
        System.out.println(test.minPathSum(new int[][]{
                {1,3,1},{1,5,1},{4,2,1}
        }));
    }
}
