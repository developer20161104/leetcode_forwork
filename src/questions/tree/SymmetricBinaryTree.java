package questions.tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/1/26 11:51
 * @Author: Mr.Yang
 * @Description:
 */
public class SymmetricBinaryTree {
    public boolean isSymmetric(TreeNode root) {
        // 此处实现的递归是对每个结点的左右进行判断，与题意不合
//        boolean res = false;
//        if(root == null)
//            return true;
//        if(root.left != null && root.right!=null && root.left.val == root.right.val)
//            res = true;
//
//        return res && isSymmetric(root.left) && isSymmetric(root.right);

        if(root == null)
            return true;
        // 递归需要判断的是左右节点，因此需要新建一个函数来引用双参数
        return judgement(root.left, root.right);
    }

    private boolean judgement(TreeNode left, TreeNode right){
        // 注意这里是终止条件，即只要满足就得结束后面的判断，因此不能将相等的情况丢里面
        if(left == null && right == null)
            return true;
        if(left == null || right == null)
            return false;
        if(left.val != right.val)
            return false;

        return judgement(left.left, right.right) && judgement(left.right, right.left);
    }

    public static void main(String[] args) {
        SymmetricBinaryTree test = new SymmetricBinaryTree();
        CreateTree treeGene = new CreateTree();

        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,2,2,null,3,null,3));
        System.out.println(test.isSymmetric(treeGene.sequenceConstruction(arr,0)));
        System.out.println();
    }
}
