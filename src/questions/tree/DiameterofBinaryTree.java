package questions.tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/5 14:53
 * @Author: Mr.Yang
 * @Description: 该题的实质就是求取以某一个节点为根节点，左右子树的最大深度之和
 */
public class DiameterofBinaryTree {
    int maxDepth=0;

    public int diameterOfBinaryTree(TreeNode root) {
        // 本题的实质是求取以某个节点为根节点，其左右子树的最大路径
        DepthofBinaryTree(root);
        return maxDepth;
    }

    private int DepthofBinaryTree(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = DepthofBinaryTree(root.left);
        int right = DepthofBinaryTree(root.right);

        // 通过一个全局变量来获取整棵中的最大直径
        maxDepth = Math.max(maxDepth, left+right);

        // 灵魂点，求取左右结点的最大长度
        return Math.max(left, right)+1;
    }

    public static void main(String[] args) {
        DiameterofBinaryTree test = new DiameterofBinaryTree();
        CreateTree createTree = new CreateTree();
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,5,null,null));
        TreeNode tree = createTree.sequenceConstruction(arr, 0);

        System.out.println(test.diameterOfBinaryTree(tree));
    }
}
