package questions;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/5 10:55
 * @Author: Mr.Yang
 * @Description:
 */
public class MoveZero {
    public void moveZeroes(int[] nums) {
        // 第一想法：双指针法，主要以条件判断的选择为主
//        int zeroPos = -1, travel = 0;
//        for(; travel < nums.length; travel++){
//            if(zeroPos == -1 && nums[travel] == 0){
//                zeroPos = travel;
//            }
//            else if(zeroPos != -1 && nums[travel] != 0){
//                nums[zeroPos++] = nums[travel];
//                nums[travel] = 0;
//            }
//        }

        // 简化版本
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!= 0){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
        Arrays.stream(nums).forEach(System.out::println);
    }

    public static void main(String[] args) {
        MoveZero test = new MoveZero();
        test.moveZeroes(new int[]{1,1,0,3,12});
    }
}
