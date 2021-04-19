package questions.nowcoder;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @program: leetcode
 * @Date: 2021-04-19 14:18
 * @Author: Lab
 * @Description:
 */
public class MaxSubsort {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len < 1){
            return 0;
        }

        int maxSize = 0;
        int left=0, right=0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(;right < len;right++){
            if(map.containsKey(s.charAt(right))){
                maxSize = Math.max(maxSize, right-left);
                left = map.get(s.charAt(right))+1;
            }
            map.put(s.charAt(right), right);

        }
        return Math.max(maxSize, right-left);
    }

    public char firstUniqChar(String s) {
        HashMap<Character, Integer[]> map = new HashMap<>();
        int len = s.length();
        if(len < 1){
            return ' ';
        }

        for(int i=0;i<len;i++){
            char val = s.charAt(i);
            if(map.containsKey(val)){
                Integer[] arr= map.get(val);
                arr[1]++;
                map.put(val, arr);
            }else{
                map.put(val, new Integer[]{i, 1});
            }

        }

        return map.entrySet().stream().filter(a -> a.getValue()[1]==1).sorted((a,b)-> a.getValue()[0]-b.getValue()[0]).map(a->a.getKey())
                .collect(Collectors.toList()).get(0);
    }

    public static void main(String[] args) {
        System.out.println(new MaxSubsort().lengthOfLongestSubstring("abba"));
    }
}
