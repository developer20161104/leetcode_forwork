package questions.dynamicprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SplitWord {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        // 初始态需要设置，否则无法进入条件语句
        dp[0] = true;
        HashSet<String> set = new HashSet<>(wordDict);

        for(int i=1;i<len+1;i++){
            for(int j=0;j<i;j++){
                // 实际上只需要比较两个，多余的可以用状态来整体替换
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }

            }
        }

        return dp[len];
    }

    public static void main(String[] args) {
        SplitWord test = new SplitWord();

        List<String> dicts = new ArrayList<>(Arrays.asList("leet", "code"));
        System.out.println(test.wordBreak("leetcode", dicts));
    }
}
