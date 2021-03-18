package questions.hotproblem.string;

import java.util.*;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        // 暴力法
        int[] judge = new int[26];
        int count = p.length();
        for(char val:p.toCharArray())
            judge[val-'a']++;

        s += "*";
        List<Integer> res = new ArrayList<>();

        int i=0;
        // 先找到满足的位置
        while(i<s.length()){
            int[] cur = Arrays.copyOf(judge, 26);
            int pos = -1;
            int index = s.charAt(i+pos+1)-'a';
            // 再进行判断
            while(index > -1 && cur[index] > 0){
                cur[index]--;
                count--;
                pos++;

                if(cur[index] < 0)
                    break;
                index = s.charAt(i+pos+1)-'a';
            }

            if(pos >-1 && count ==0)
                res.add(i);
            i++;
            count = p.length();
        }

        return res;
    }

    public static void main(String[] args) {
        FindAnagrams test = new FindAnagrams();

        System.out.println(test.findAnagrams("cbaebabacd", "abc"));

        HashSet<Integer> set = new HashSet<>();
    }
}
