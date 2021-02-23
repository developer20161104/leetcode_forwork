package questions;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/23 10:02
 * @Author: Mr.Yang
 * @Description:
 */
public class ColorClassification {
    public void sortColors(int[] nums) {
        // 由于一共只有三种类型，因此可以使用双指针进行分类
        int len = nums.length;
        if(len < 2)
            return;

        int left = 0,right = len-1, i=0;
        // 关键点：循环不变量
        while(i<=right){
           if(nums[i] == 2)
               swap(nums, right--, i);
           else {
               if(nums[i] == 0)
                   swap(nums, left++, i);
               i++;
           }
        }
    }

    private void swap(int[] nums, int left, int right){
         int tmp = nums[left];
         nums[left] = nums[right];
         nums[right] = tmp;
    }

    public static void main(String[] args) {
        ColorClassification test = new ColorClassification();
        int[] arr = new int[]{2,0,1,1};
        test.sortColors(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
