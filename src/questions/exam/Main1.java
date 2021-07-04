package questions.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: leetcode
 * @Date: 2021-04-11 10:28
 * @Author: Lab
 * @Description:  对M队与T队进行正序排序，然后对T队的每个元素值k遍历，查找其在M队的位置，从而计算以k为界限两队的成绩差，
 *                剪枝： 对T队采用从后向前遍历，遇到总成绩比原来低了直接返回
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNextInt()){
            int n = in.nextInt(), m = in.nextInt();
            int[] arrN = new int[n];
            int[] arrM = new int[m];

            for(int i=0;i<n;i++){
                arrN[i] = in.nextInt();
            }
            for(int i=0;i<m;i++){
                arrM[i] = in.nextInt();
            }

            Arrays.sort(arrN);
            Arrays.sort(arrM);

            int maxProfit = Integer.MIN_VALUE;
            for(int i=m-1;i>=0;i--){
                int pos = BinarySearch(arrN, arrM[i]);
                int curProfit;

                curProfit = (m-i)*2+i-2*(n-pos)-pos;
                if(maxProfit != Integer.MIN_VALUE && maxProfit > curProfit){
                    break;
                }else maxProfit = Math.max(maxProfit, curProfit);
            }
            System.out.println(maxProfit);
        }
    }

    private static int BinarySearch(int[] arrN, int value){
        int left=0, right=arrN.length-1;
        while(left<=right){
            int mid = left + (right-left) / 2;
            // 右查找只需要将判断条件增加等号判断，可以表示越过左侧相等元素
            if(arrN[mid] < value){
                left = mid+1;
            }
            else right = mid-1;
        }

        return left;
    }
}
