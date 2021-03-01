package questions.dynamicprogram;

public class MaxMultiplicationSubArray {
    public int maxProduct(int[] nums) {
        int dp_max=1, dp_min=1, maxValue=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i] <0){
                int tmp = dp_max;
                dp_max = dp_min;
                dp_min = tmp;
            }

            // 死活想不出状态转移方程
            // 注意到由于连续性的影响，最值选取的是当前的值与先前值与当前值的乘积
            dp_max = Math.max(nums[i], dp_max*nums[i]);
            dp_min = Math.min(nums[i], dp_min*nums[i]);

            maxValue = Math.max(maxValue, dp_max);
        }

        return maxValue;
    }

    public static void main(String[] args) {
        MaxMultiplicationSubArray test = new MaxMultiplicationSubArray();
        System.out.println(test.maxProduct(new int[]{-2,0,-1}));
    }
}
