package questions;

import java.util.Arrays;

public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        // 可以省空间
//        boolean[] travel = new boolean[len+1];
//
//        for(int num:nums){
//            if(!travel[num])
//                travel[num] = true;
//            else
//                return num;
//        }
//        return 0;
        // 时间复杂度为O(n)，空间为O(1)
        // 但是会修改原始数组
        int index=-1;
        for(int i=0;i<len;i++){
            index = Math.abs(nums[i]);
            if(nums[index-1] > 0)
                nums[index-1] *=-1;
            else break;
        }
        return index;
    }

    public int findDuplicateBinarySearch(int[] nums){
        int len = nums.length;
        // 双闭区间
        int left = 1, right=len-1;

        while(left < right){
            int mid = left+(right-left)/2;

            int cnt=0;
            // 统计当前小于等于 mid 的数量
            for(int num:nums)
                if(num <= mid)
                    cnt++;

            // 出现统计数量严格大于mid时，说明重复元素在左边
            if(cnt > mid){
                right = mid;
            }else
                left = mid+1;
        }

        return left;
    }

    public static void main(String[] args) {
        FindDuplicate test = new FindDuplicate();

        System.out.println(test.findDuplicateBinarySearch(new int[]{
                1,3,3,3
        }));
    }
}
