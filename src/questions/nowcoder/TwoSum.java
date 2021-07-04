package questions.nowcoder;

import java.util.Arrays;

/**
 * @program: leetcode
 * @Date: 2021-04-24 11:10
 * @Author: Lab
 * @Description:
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        if(len < 1){
            return new int[0];
        }

        for(int i=0;i<len-1;i++){
            int cur = getSide(nums, i+1, target-nums[i]);
            if(cur < len && nums[cur]+nums[i]==target){
                return new int[]{nums[i], nums[cur]};
            }
        }
        return new int[0];
    }

    public int getSide(int[] nums, int left, int target){
        int right = nums.length-1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] < target){
                left = mid+1;
            }else right = mid-1;
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum().twoSum(new int[]{
                14,15,16,22,53,60
        }, 76)));
    }
}
