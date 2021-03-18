package questions.hotproblem;

import java.util.Arrays;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        // 利用动态规划的思想，从右到左遍历，查找结果数组的中的元素
        int len  = T.length;
        if(len < 1)
            return null;

        int[] res = new int[len];
        for(int i=len-2;i>=0;i--){
            if(T[i] < T[i+1]){
                res[i] = 1;
            } else{
                int index = i+1;
                // 注意需要等号移位，考虑下一位相同的情况
                while(res[index] > 0 && T[i] >= T[index]){
                    res[i] += res[index];
                    index = index+res[index];
                }

                // 这个边界判断条件有点难得到
                if(T[i] < T[index])
                    res[i]++;
                    // 考虑最后的情况
                else
                    res[i] =0;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures test = new DailyTemperatures();

        System.out.println(Arrays.toString(test.dailyTemperatures(new int[]{
                34,80,80,34,34,80,80,80,80,34
        })));
    }
}
