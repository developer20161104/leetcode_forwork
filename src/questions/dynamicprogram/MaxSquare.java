package questions.dynamicprogram;

/**
 * 求解正方形的状态转移方程，使用的是右下角作为判断位
 */
public class MaxSquare {
    public int maximalSquare(char[][] matrix) {
        int rlen= matrix.length;

        if(rlen < 1)
            return 0;

        int clen = matrix[0].length;
        int maxlen = 0;

        int[][] dp = new int[rlen+1][clen+1];
        for(int i=1;i<=rlen;i++){
            for(int j=1;j<clen+1;j++){
                if(matrix[i-1][j-1] == '1')
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1])+1;
                maxlen = Math.max(maxlen, dp[i][j]);
            }
        }

        return maxlen*maxlen;
    }

    public static void main(String[] args) {
        MaxSquare test = new MaxSquare();

        char[][] arr = new char[][]{
                {'1','1','1','0','0'},
                {'1','1','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'},
        };

        System.out.println(test.maximalSquare(arr));
    }
}
