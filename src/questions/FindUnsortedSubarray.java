package questions;

import java.util.Arrays;

public class FindUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        // 排序后选择不同之处
        // 时间复杂度为O(n log n)
        int[] newNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newNums);

        int left=0, right= nums.length-1;
        while(left < nums.length && newNums[left] == nums[left])
            left++;

        while (right >=0 && newNums[right] == nums[right])
            right--;

        return Math.max(right - left + 1, 0);
    }

    public int findUnsortedSubarrayPro(int[] nums){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        // 先找到无序最小元素
        for(int i=1;i<nums.length;i++){
            if(nums[i] < nums[i-1])
                min = Math.min(min, nums[i]);
        }

        // 再找到无序最大元素
        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]>nums[i+1])
                max = Math.max(max, nums[i]);
        }

        // 定位最小元素有序下标
        int left=0, right=nums.length-1;
        while(left<nums.length && nums[left] <= min)
            left++;

        // 定位最大元素有序下标
        while(right >=0 && nums[right] >= max)
            right--;

        return right-left <0? 0: right-left+1;
    }

    public static void main(String[] args) {
        FindUnsortedSubarray test = new FindUnsortedSubarray();

        int[] nums = new int[]{2,1};
        System.out.println(test.findUnsortedSubarray(nums));
        System.out.println(test.findUnsortedSubarrayPro(nums));
    }
}
