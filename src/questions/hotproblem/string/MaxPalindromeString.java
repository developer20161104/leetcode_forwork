package questions.hotproblem.string;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/7 10:27
 * @Author: Mr.Yang
 * @Description:
 */
public class MaxPalindromeString {
    public String longestPalindromeBruteforce(String s) {
        // 暴力法：遇到长度大于2的直接进行回文判断
        int len = s.length();
        if(len<2)
            return s;

        int maxlen = 1, begin = 0;
        char[] charArray = s.toCharArray();

        for(int i=0; i<len-1;i++){
            for(int j=i+1;j<len;j++){
                if(j-i+1 >maxlen && validPalindromic(charArray, i, j)){
                    maxlen = j-i+1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin+maxlen);
    }

    private boolean validPalindromic(char[] charArray, int left, int right){
        while(left < right){
            if(charArray[left++] != charArray[right--])
                return false;
        }

        return true;
    }

    public String longestPalindromeDP(String s){
        // 状态转移方程 dp[i][j] 表示i到j之间是否为回文
        // dp[i][j] = dp[i+1][j-1]
        // dp[i][j] = true if (s[i]==s[j]) and j-i<3

        int len = s.length();
        if(len < 2)
            return s;

        boolean[][] dp = new boolean[len][len];
        int maxlen = 0, begin=0;
        char[] arr = s.toCharArray();
        for(int i=0;i<len;i++)
            dp[i][i] = true;

        // 需要注意填入的顺序，得满足无后效性
        // 待计算的位置所需要的前面位置需要先算好，才能进行后续计算
        for(int j=1;j<len;j++){
            for(int i=0;i<j;i++){
                if(arr[i]!=arr[j])
                    // 左右不等
                    dp[i][j] = false;
                else if(j-i<3)
                    // 左右相等
                    dp[i][j] = true;
                else
                    dp[i][j] = dp[i+1][j-1];

                // 保存位置即可
                if(dp[i][j] && j-i+1 > maxlen){
                    maxlen = j-i+1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin+maxlen);
    }

    public String longestPalindromeSpread(String s){
        // 中心扩散的思想
        // 对于每一个位置进行两边扩散，考虑奇偶的情况
        if(s.length() <2)
            return s;
        String max = "";

        for(int i=0;i<s.length()-1;i++){
            String oddstr = centerSpread(s, i, i);
            String evenstr = centerSpread(s, i, i+1);

            String curmaxstr = oddstr.length() > evenstr.length() ? oddstr:evenstr;
            max = curmaxstr.length() > max.length() ? curmaxstr:max;
         }

        return max;
    }

    private String centerSpread(String s, int left, int right){
        // 当left == right，此时为奇数
        // 当left == right-1，此时为间隙（偶数）

        int len = s.length();
        int i=left, j=right;
        while(i>=0 && j<len){
            if(s.charAt(i) == s.charAt(j)){
                i--;
                j++;
            }else break;
        }

        // [i+1, j)
        return s.substring(i+1, j);
    }

    public static void main(String[] args) {
        MaxPalindromeString test = new MaxPalindromeString();

        System.out.println(test.longestPalindromeBruteforce("abbaa"));

        System.out.println(test.longestPalindromeDP("abbaa"));

        System.out.println(test.longestPalindromeSpread("babad"));
    }
}
