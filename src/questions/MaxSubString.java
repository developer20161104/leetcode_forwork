package questions;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/6 15:16
 * @Author: Mr.Yang
 * @Description:
 */
public class MaxSubString {
    public int lengthOfLongestSubstring(String s) {
        // 维护两个指针，一个遍历所有字符，一个保存当前子串长度
        // 同时维护一个set来保证内部元素的单一性
//        HashSet<Character> set =new HashSet<>();
//        int left=0;
//        int maxLen = 0;
//        for(int i=0;i<s.length();i++){
//            char curChar = s.charAt(i);
//            if(!set.contains(curChar)){
//                set.add(curChar);
//            }else{
//                while(s.charAt(left) != s.charAt(i)){
//                    // 需要除外左边所有元素
//                    set.remove(s.charAt(left));
//                    left ++;
//                }
//                // 最后一个相等的则进行保留
//                left ++;
//            }
//
//            maxLen = Math.max(maxLen, i-left+1);
//        }
//
//        return maxLen;

        // 网上做法：使用HashMap保存最近出现位置，
        // 减少删除操作以及左指针移位操作

        if(s.length() == 0)
            return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int max=0, left=0;
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i)))
                // 出现则直接置为重复元素的上一个位置与当前位置中的最大
                // 由于没有删除操作，map中保留的元素位置尚未更新，因此需要选最大值
                left = Math.max(left, map.get(s.charAt(i))+1);

            // 更新位置
            map.put(s.charAt(i), i);
            max = Math.max(max, i-left+1);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxSubString test = new MaxSubString();

        System.out.println(test.lengthOfLongestSubstring("abba"));
    }
}
