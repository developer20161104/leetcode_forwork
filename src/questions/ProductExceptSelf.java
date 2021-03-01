package questions;

import java.util.Arrays;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        // 需要一个prefix数组来记录前缀积，并利用自身来作为后缀积进行处理
        // 后缀积可以简化为一个变量
        int len = nums.length;
        if(len < 2)
            return nums;

        int[] prefix = new int[len];
        prefix[0] = nums[0];
        for(int i=1;i<len;i++)
            prefix[i] = prefix[i-1]*nums[i];

        for(int i=len-2; i>=0;i--)
            nums[i] *= nums[i+1];

        nums[0] = nums[1];
        for(int i=1;i<len-1;i++)
            nums[i] = nums[i+1]*prefix[i-1];

        nums[len-1] = prefix[len-2];

        return nums;
    }

    public int[] productExceptSelfPro(int[] nums){
        // 通过单变量，将过程简化为两个循环
        int len = nums.length;
        if(len < 2)
            return nums;

        int[] ans = new int[len];
        // 首位没必要计算
        ans[0] = 1;
        for(int i=1;i<len;i++)
            ans[i] = ans[i-1]*nums[i-1];

        // 同样，尾部也不用计算
        int backval = 1;
        for(int i=len-1;i>=0;i--){
            ans[i] = ans[i]*backval;
            backval *= nums[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        ProductExceptSelf test = new ProductExceptSelf();
        Arrays.stream(test.productExceptSelfPro(new int[]{2,3,0,1,2})).forEach(System.out::println);
    }
}
