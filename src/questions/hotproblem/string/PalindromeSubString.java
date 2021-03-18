package questions.hotproblem.string;

public class PalindromeSubString {
    public int countSubstrings(String s) {
        // 中心扩散法：时间复杂度为O(n2)
        int len = s.length();
        if(len < 1)
            return 0;

        int total = 0;
        for(int i=0;i<len;i++){
            total+= judge(s,i,i+1)+judge(s, i, i);
        }

        return total;
    }

    private  int judge(String s, int left, int right){
        int offset = 0, count = 0;
        while(left-offset >=0 && right+offset<s.length() && s.charAt(left-offset) == s.charAt(right+offset)){
            count++;
            offset++;
        }

        return count;
    }

    public int countSubstringsDP(String s){
        // 状态转移方程还是没有想出来
        int len = s.length();
        boolean[][] dp = new boolean[len+1][len+1];

        int count = 0;
        // 遍历的下标有些细节
        for(int j=1;j<=len;j++){
            for(int i=1;i<=j;i++){
                if(s.charAt(i-1) == s.charAt(j-1) && (j-i < 2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    count++;
                }

            }
        }

        return count;
    }

    public static void main(String[] args) {
        PalindromeSubString test = new PalindromeSubString();

        System.out.println(test.countSubstringsDP("aaa"));
    }
}
