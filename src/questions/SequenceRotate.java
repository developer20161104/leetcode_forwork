package questions;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/17 12:59
 * @Author: Mr.Yang
 * @Description:
 */
public class SequenceRotate {
    public void rotate(int[][] matrix) {
        // 对称翻转
//        int cLen = matrix[0].length, rLen = matrix.length;
//        int i=0, j=cLen-1;
//        for(int curRow=0; curRow<cLen;curRow++){
//            i =0;
//            j =cLen-1;
//            while(i<j)
//                swap(matrix, curRow, i++, curRow, j--);
//        }

        // 斜对角翻转
//        for(int incre=0;incre<cLen-1;incre++){
//            int l1 = 0, r1 = cLen-2-incre, l2 = 1+incre, r2 = cLen-1;
//            while(l1 < l2){
//                swap(matrix, l1++, r1++, l2--, r2--);
//            }
//        }
//
//        for(int incre=0;incre<rLen-2;incre++){
//            int l1 = rLen-2-incre, r1 = 0, l2 = rLen-1, r2 = 1+incre;
//            while(r1 < r2){
//                swap(matrix, l1++, r1++, l2--, r2--);
//            }
//        }

        // 两个翻转都可以简化
        // 先进行横轴翻转，再进行对角翻转
        int len = matrix.length;
        for(int i=0;i<len/2; i++)
            for(int j=0;j<len;j++)
                swap(matrix, i, j, len-i-1, j);

        for(int i=0;i<len;i++)
            for(int j=i;j<len;j++)
                swap(matrix, i, j, j,i);
    }

    private void swap(int[][] matrix, int l1, int r1, int l2, int r2){
        int tmp = matrix[l1][r1];
        matrix[l1][r1] = matrix[l2][r2];
        matrix[l2][r2] = tmp;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3}, {4,5,6},{7,8,9}};

        SequenceRotate test = new SequenceRotate();
        test.rotate(matrix);

        Arrays.stream(matrix).forEach(x -> Arrays.stream(x).forEach(System.out::print));
    }
}
