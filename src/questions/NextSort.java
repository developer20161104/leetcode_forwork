package questions;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/14 11:16
 * @Author: Mr.Yang
 * @Description:
 */
public class NextSort {
    public void nextPermutation(int[] nums) {
        // 字典序法
        int len = nums.length;
        int i=len-2, j= len-1;
        // 边界条件
        if(i > j)
            return;

        // 从右向左找
        for(;i>-1;i--){
            if(nums[i] < nums[i+1])
                break;
        }

        // 没找到时为逆序有序情况，特殊考虑
        if(i == -1){
            Arrays.sort(nums);
            return;
        }

        for(;j>i;j--){
            if(nums[j] > nums[i])
                break;
        }

        swap(nums, i, j);
        i++;
        j = len-1;
        while(i < j){
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        NextSort test = new NextSort();

        int[] arr = new int[]{1,5,1,1,1,1,5};
        test.nextPermutation(arr);

        Arrays.stream(arr).forEach(System.out::println);
    }
}
