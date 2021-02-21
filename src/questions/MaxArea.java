package questions;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/7 14:54
 * @Author: Mr.Yang
 * @Description:
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int left=0, right=height.length-1;
        int max = 0;

        while(left < right){
            // 双指针贪心即可
            max = Math.max(max, Math.min(height[left], height[right])*(right-left));
            if(height[left] < height[right])
                left++;
            else
                right--;
        }

        return max;
    }
}
