package questions.hotproblem.tree;

import java.util.ArrayList;
import java.util.Arrays;

public class PathSumBinaryTree {
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;

        return dfs(root, sum, 0)+pathSum(root.left, sum)+pathSum(root.right, sum);
    }

    private int dfs(TreeNode root, int sum, int curSum){
        if(root == null)
            return 0;

        // 内层叠加可能会出现重复元素累加两次
        curSum += root.val;
        int left = dfs(root.left, sum, curSum);
        int right = dfs(root.right, sum, curSum);

        // 并且在出现相等情况时，不能返回，因为后续可能存在满足条件的情况
        return (curSum==sum ? 1:0)+left+right;
    }


    public static void main(String[] args) {
        CreateTree createTree = new CreateTree();

        TreeNode t = createTree.sequenceConstruction(new ArrayList<>(Arrays.asList(
                10,5,-3,3,2,null,11,3,-2,null,1
        )), 0);

        PathSumBinaryTree test = new PathSumBinaryTree();
        System.out.println(test.pathSum(t, 8));
    }
}
