package questions.problemperday;

/**
 * @program: leetcode
 * @Date: 2021-03-17 14:39
 * @Author: Lab
 * @Description: 不同的子序列数：动态规划问题，状态转移方程有点难想到
 */
public class NumofSubSort {
    public int numDistinct(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();

        // dp[i][j]表示s[:i+1]中匹配t[:j+1]字符串的数量
        int[][] dp = new int[lenS+1][lenT+1];
        // 空串匹配数量为1
        for(int i=0;i<lenS+1;i++)
            dp[i][0] = 1;

        for(int i=1;i<lenS+1;i++){
            for(int j=1;j<=lenT;j++){
//                if(j > i)
//                    continue;

                // 当出现的字符相同时，为同时减去的数量与余下的部分
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
                }else {
                    // 不相同则只能取余下的部分
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[lenS][lenT];
    }

    public static void main(String[] args) {
        NumofSubSort test = new NumofSubSort();

        System.out.println(test.numDistinct("babgbag","bag"));
    }
}
