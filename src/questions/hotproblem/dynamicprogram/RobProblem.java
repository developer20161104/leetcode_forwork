package questions.hotproblem.dynamicprogram;

import questions.hotproblem.tree.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

public class RobProblem {
    // 数组中的打劫
    public int rob(int[] nums) {
        int len = nums.length;
        if(len < 1)
            return 0;
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];

        for(int i=2;i<len+1;i++)
            // 关键点在于状态转移方程
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i-1]);

        return dp[len];
    }

    // 环中的打劫
    public int robII(int[] nums) {
        // 对于环问题，使用两次调用即可转化为数组问题
        int len = nums.length;
        if(len < 1)
            return 0;
        if(len < 2)
            return nums[0];
        return Math.max(rob(Arrays.copyOfRange(nums, 0, len-1)),
                rob(Arrays.copyOfRange(nums, 1, len)));
    }

    // 二叉树中的打劫
    public int robIII(TreeNode root) {
        // 最优子结构 max(爷爷+四个孙子，两个父亲)
        if(root == null)
            return 0;

        int money = root.val;
        if(root.left != null){
            money += robIII(root.left.left) + robIII(root.left.right);
        }

        if(root.right != null)
            money += robIII(root.right.left)+robIII(root.right.right);

        return Math.max(money, robIII(root.left)+robIII(root.right));
    }

    HashMap<TreeNode, Integer> memo = new HashMap<>();
    public int robIIIpro(TreeNode root){
        if(root == null)
            return 0;

        if(memo.containsKey(root))
            return memo.get(root);

        int money = root.val;
        if(root.left != null)
            money += (robIIIpro(root.left.left) + robIIIpro(root.left.right));

        if(root.right != null)
            money += (robIIIpro(root.right.left) + robIIIpro(root.right.right));

        int res = Math.max(money, robIIIpro(root.left)+robIIIpro(root.right));
        memo.put(root, res);

        return res;
    }

    public static void main(String[] args) {
        RobProblem test = new RobProblem();

        System.out.println(test.rob(new int[]{0,2,1,1,10,3,5}));

        System.out.println(test.robII(new int[]{2,1,1,2}));
    }
}
