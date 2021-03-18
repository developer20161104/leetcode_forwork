package questions.hotproblem.dynamicprogram;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/17 15:35
 * @Author: Mr.Yang
 * @Description:
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        // 贪心即可
        // 需要一个获取当前最大长度的值
        int len = nums.length;
        if(len < 1)
            return true;

        // 当前的这一点很重要
        int maxDis = nums[0];
        for(int i=1;i<len;i++){
            // 只计算满足要求的部分
            if(i <= maxDis)
                maxDis = Math.max(maxDis, nums[i]+i);
        }

        // 如果最大长度比总长要长，则一定能达到
        return maxDis >= len-1;
    }

    public boolean canJump1(int[] nums) {
        int maxDis = 0;
        for(int i=0;i<nums.length;i++){
            if(maxDis < i)
                return false;
            maxDis = Math.max(maxDis, nums[i]+i);
        }

        return true;
    }


        public static void main(String[] args) {
        JumpGame test = new JumpGame();
        System.out.println(test.canJump1(new int[]{3,2,1,0,4}));
    }
}
