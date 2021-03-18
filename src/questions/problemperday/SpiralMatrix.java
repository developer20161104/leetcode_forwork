/**
 * @program: leetcode
 * @Date: 2021-03-16 11:41
 * @Author: Lab
 * @Description: 输出矩阵的顺时针旋转顺序，采用的是逐一遍历的方式，
 *               关键点在于停止条件的判断
 */
package questions.problemperday;
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    int[][] move = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    List<Integer> res;

    public List<Integer> spiralOrder(int[][] matrix) {
        res = new ArrayList<>();

        int row = matrix.length;
        int col = matrix[0].length;
        int x=0,y=0, i=0;
        while(matrix[x][y] != -101){
            res.add(matrix[x][y]);
            matrix[x][y] = -101;
            int newx = x+move[i][0];
            int newy = y+move[i][1];

            // 关键点：停止条件没有考虑好
            if(res.size() == row*col)
                break;

            if(!(newx>=0 && newx < row && newy>=0 && newy<col) || matrix[newx][newy] == -101){
                i = (i+1)%4;
                newx = x+move[i][0];
                newy = y+move[i][1];
            }

            x = newx;
            y = newy;
        }

        return res;
    }

    // 通过给定一个数字来创建一个矩阵
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int curVal = 1;
        int x=0,y=0, i=0;
        while(res[x][y] == 0){
            res[x][y] = curVal;

            if(curVal == n*n)
                break;
            curVal++;

            int newx = x+move[i][0], newy = y+move[i][1];
            if(!(newx >=0 && newx < n&& newy>=0 && newy<n) || res[newx][newy]!=0){
                i = (i+1)%4;
                newx = x+move[i][0];
                newy = y + move[i][1];
            }

            x = newx;
            y = newy;
        }

        return res;
    }

    public static void main(String[] args) {
        questions.problemperday.SpiralMatrix test = new questions.problemperday.SpiralMatrix();
        List<Integer> res = test.spiralOrder(new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
        });

        for(Integer x : res)
            System.out.println(x);
    }
}

