package questions.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/3 16:26
 * @Author: Mr.Yang
 * @Description:
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return root;

        // 递归给后续的子节点
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        // 每一步中实际做的事情
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;

    }


    public TreeNode invertTreeIter(TreeNode root){
        if(root == null)
            return root;
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            // 先进行置换
            TreeNode left = tmp.left;
            tmp.left = tmp.right;
            tmp.right = left;

            // 存在后续元素则直接进行添加
            if(tmp.left != null)
                queue.add(tmp.left);

            if(tmp.right != null)
                queue.add(tmp.right);
        }

        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree test = new InvertBinaryTree();
        CreateTree t = new CreateTree();
        int[] arr = new int[]{4,2,7,1,3,6,9};
        ArrayList<Integer> listobject = (ArrayList<Integer>) Arrays.stream(arr).boxed().collect(Collectors.toList());

        TreeNode res = test.invertTreeIter(t.sequenceConstruction(listobject,0));
        t.preorderTravel(res);
    }
}
