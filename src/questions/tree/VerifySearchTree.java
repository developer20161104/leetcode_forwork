package questions.tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 二叉搜索树的最大特点：中序遍历是有序的！！！
 */

public class VerifySearchTree {
    long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        // 只能判断邻近的是否满足
//        if(root == null)
//            return true;
//
//        boolean res = false;
//        if(root.left == null || root.left.val < root.val)
//            if(root.right == null || root.right.val > root.val){
//                res = true;
//            }
//
//        return isValidBST(root.left) && isValidBST(root.right) && res;

        // 直接中序遍历比较输出即可
        if(root == null)
            return true;

        boolean left = isValidBST(root.left);
        if(pre < root.val)
            pre = root.val;
        else
            return false;

        boolean right = isValidBST(root.right);

        return left&&right;
    }



    public static void main(String[] args) {
        CreateTree test = new CreateTree();
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(5,2,5));
        TreeNode t = test.sequenceConstruction(arr,0);

        System.out.println(new VerifySearchTree().isValidBST(t));
    }
}
