package questions.hotproblem.dynamicprogram;

/**
 * 这里需要用到一个定理：
 * 四平方数定理：任何一个整数都可以表示为不超过四个数的平方和
 */
public class SquareNumber {
    public int numSquares(int n) {
        // 相当于是带记忆的暴力破解了
        // dp[i] = min(dp[i-1],dp[i-4], ..., dp[i-k^2])+1;
        // 时间复杂度为 O(n^1.5)

        int[] dp =new int[n+1];
        for(int j=1; j<=n;j++){
            int curMin = Integer.MAX_VALUE;
            for(int k=1;k*k<=j;k++){
                curMin = Math.min(curMin, dp[j-k*k]);
            }

            dp[j] = curMin+1;
        }
        return dp[n];
    }

    public int numSquaresMath(int n){
        // 由4个数的平方和组成必满足条件 n = (4^k)*(8*m+7)
        while(n%4 == 0)
            n /=4;

        if(n%8 == 7)
            return 4;

        // 直接被开根号
        if(isSquare(n))
            return 1;

        // 拆分为两个数的情况
        for(int i=1;i*i <= n;i++){
            if(isSquare(n-i*i))
                return 2;
        }

        // 剩下的就只有三个数组成了
        return 3;
    }

    private boolean isSquare(int n){
        int sq = (int) Math.sqrt(n);
        return sq*sq == n;
    }

    public static void main(String[] args) {
        SquareNumber test = new SquareNumber();

        System.out.println(test.numSquares(12));

        System.out.println(test.numSquaresMath(12));
    }
}
