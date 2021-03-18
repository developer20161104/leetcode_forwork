package questions.hotproblem.dynamicprogram;

/**
 * @program: leetcode_forwork
 * @Date: 2021/1/24 14:59
 * @Author: Mr.Yang
 * @Description:
 */
public class MaxSumofSubsort {

    public int maxSubArray(int[] nums) {
        // 状态转移方程为 cur = A[i] + max(0, cur)

        int maxSubSum= Integer.MIN_VALUE, curSubsum = 0;
        for(int value: nums){
            // 当结果比0大时，说明对后续结果还是会产生增益
            if(curSubsum > 0)
                curSubsum += value;
            else
                // 由于需要的是子序列，
                // 所以一旦前面的结果为负，则直接取当前的值作为求和的起点
                // 而不是进行取0操作
                curSubsum = value;

            maxSubSum = Math.max(curSubsum, maxSubSum);
        }

        return maxSubSum;
    }

    public int maxSubArrayDivide(int[] nums){
        // 分而治之，将整体切分为左边，中间和右边三个部分进行递归处理
        return DivideandConquer(nums, 0, nums.length-1);
    }

    private int DivideandConquer(int[] nums, int start, int end){
        // 递归截止条件：只剩下一个元素时，即为该元素
        if(start == end){
            return nums[start];
        }

        // 一分为三
        int center = (end+start)/2;
        int leftMax = DivideandConquer(nums, start, center);
        int rightMax = DivideandConquer(nums, center+1, end);

        // 计算横跨序列中的最大值
        // 此处注意中间两个位置是一定会出现在最终的子序列中的
        // 右边与左边两个部分实际都是可以使用递归来进行中间处理的，因此直接递归即可
        int leftCross = Integer.MIN_VALUE, leftSum = 0;
        for(int i=center;i>=start; --i){
            leftSum += nums[i];
            leftCross = Math.max(leftCross, leftSum);
        }

        int rightCross = Integer.MIN_VALUE, rightSum = 0;
        for(int i=center+1;i<=end;++i){
            rightSum += nums[i];
            rightCross = Math.max(rightCross, rightSum);
        }

        // 计算三者中的最大值
        return Math.max(leftCross+rightCross, Math.max(leftMax, rightMax));
    }

    public static void main(String[] args) {
        MaxSumofSubsort test = new MaxSumofSubsort();

        System.out.println(test.maxSubArray(new int[]{-2,-3,-1}));

        System.out.println(test.maxSubArrayDivide(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
