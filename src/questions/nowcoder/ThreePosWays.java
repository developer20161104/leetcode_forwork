package questions.nowcoder;

/**
 * @program: leetcode
 * @Date: 2021-03-21 21:41
 * @Author: Lab
 * @Description:
 */
import java.util.Scanner;
public class ThreePosWays {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            int num = in.nextInt(), maxDis = in.nextInt();
            int[] arr = new int[num];
            for(int i=0;i<num;i++)
                arr[i] = in.nextInt();

            int left = 0, tot =0;
            int right = 0;
            while(left < num){
                right = binarySearch(arr, num, arr[left]+maxDis);
                tot += cal(left, right);
                left = right+1;
            }

            System.out.println(tot%99997867);
        }
    }

    private static int cal(int left, int right){
        int res = 0;
        for(int i=left;i<= right-2;i++)
            res += (right-i)*(right-i-1)/2;
        return res%99997867;
    }

    private static int binarySearch(int[] arr, int n,int val){
        int left=0, right=n-1;
        while(left < right){
            int mid = left+ (right-left)/2;
            if(arr[mid] < val){
                left = mid+1;
            }else
                right = mid;
        }

        return left;
    }
}
