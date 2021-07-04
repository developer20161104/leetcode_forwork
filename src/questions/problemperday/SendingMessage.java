package questions.problemperday;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @program: leetcode
 * @Date: 2021-07-01 20:45
 * @Author: Lab
 * @Description:
 */
public class SendingMessage {
    public int numWays(int n, int[][] relation, int k) {
        int[][] mat = new int[n][n];
        int[][] res = new int[n][n];

        for(int[] r:relation){
            mat[r[0]][r[1]] = 1;
            res[r[0]][r[1]] = 1;
        }

        for(int p=0;p<k-1;p++){
            res = multiply(res, mat, n);
        }

        return res[0][n-1];

    }

    public int[][] multiply(int[][] m1, int[][] m2,int n){
        int[][] nw = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int tmp = 0;
                for(int k =0;k<n;k++){
                    tmp += m1[i][k]*m2[k][j];
                }
                nw[i][j] = tmp;
            }
        }

        return nw;
    }


    public static void main(String[] args) {
        SendingMessage test = new SendingMessage();
        System.out.println(test.numWays(5,
                new int[][]{{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}},
        3));
    }
}
