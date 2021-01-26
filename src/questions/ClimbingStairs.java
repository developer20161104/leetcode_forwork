package questions;

/**
 * @program: leetcode_forwork
 * @Date: 2021/1/26 11:31
 * @Author: Mr.Yang
 * @Description:
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        // 动态规划，状态转移方程为 A[i] = A[i-1] + A[i-2]
        int fir = 0, sec = 1;
        for(int i=1;i<=n;i++){
            int temp = fir;
            fir = sec;
            sec = temp+fir;
        }

        return sec;
    }

    private int Recursive(int n){
        // 递归法会超时
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;
        return Recursive(n-1)+Recursive(n-2);
    }

    public static void main(String[] args) {
        ClimbingStairs test = new ClimbingStairs();

        System.out.println(test.climbStairs(45));
    }
}
