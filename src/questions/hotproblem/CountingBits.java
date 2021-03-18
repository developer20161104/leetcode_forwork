package questions.hotproblem;

import java.util.Arrays;

public class CountingBits {
    public int[] countBits(int num) {
        // if i is even , we have dp[i] = dp[i/2]
        // else we have dp[i+1] = dp[i]+1
        int[] res = new int[num+1];
        for(int i=1;i<num+1;i++){
//            if((i&1) == 0){
//                res[i] = res[i/2];
//            }else
//                res[i] = res[i-1]+1;
            res[i] = res[i >> 1] + (i&1);
        }

        return res;
    }

    public static void main(String[] args) {
        CountingBits test = new CountingBits();

        Arrays.stream(test.countBits(5)).forEach(System.out::println);
    }
}
