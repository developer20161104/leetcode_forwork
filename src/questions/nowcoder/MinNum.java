package questions.nowcoder;

import java.util.Arrays;

/**
 * @program: leetcode
 * @Date: 2021-04-19 13:15
 * @Author: Lab
 * @Description:
 */
public class MinNum {
    public String minNumber(int[] nums) {
        int len = nums.length;
        if(len < 1){
            return "";
        }
        String[] strs = new String[len];
        for(int i=0;i<len;i++){
            strs[i] = nums[i]+"";
        }

        Arrays.sort(strs, (a, b)->{
            String str1 = a+b;
            String str2 = b+a;

            for(int i=0;i<str1.length();i++){
                if(str1.charAt(i) != str2.charAt(i)){
                    return str1.charAt(i)-str2.charAt(i);
                }
            }
            return 0;
        });

        StringBuilder res =new StringBuilder();
        for(String val: strs){
            res.append(val);
        }

        return res.toString();
    }
}
