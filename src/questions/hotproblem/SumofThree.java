package questions.hotproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/7 15:03
 * @Author: Mr.Yang
 * @Description:
 */
public class SumofThree {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> tot = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);

        for(int i=0;i<len-2;i++){
            int left=i+1, right=len-1, cur = nums[i]+nums[left]+nums[right];

            if(nums[i] > 0)
                break;
            // 首位也不能重复
            if(i>0 && nums[i]==nums[i-1])
                continue;

            while(left<right){
                cur = nums[i]+nums[left]+nums[right];

                if(cur == 0){
                    tot.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 这个没想到（滤重）
                    while(left < right && nums[left] == nums[left+1])
                        left++;
                    while(right > left && nums[right] == nums[right-1])
                        right--;

                    // 注意跳出
                    left++;
                    right--;

                }else if(cur <0){
                    left++;
                }else
                    right--;
            }
        }
        return tot;
    }

    public static void main(String[] args) {
        SumofThree test = new SumofThree();

        test.threeSum(new int[]{-1,0,1,2,-1,-4});
    }
}
