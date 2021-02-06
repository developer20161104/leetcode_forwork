package questions.tree;

import com.sun.scenario.effect.Merge;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/6 10:23
 * @Author: Mr.Yang
 * @Description:
 */
public class MergeBinaryTree {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode head = null;
        if(t1 == null && t2 == null)
            return head;

        // 出现有一方为null时，置为另一方即可
        if(t1 == null)
            head = t2;
        else if(t2 == null)
            head = t1;
        else {
            // 否则正常处理
            head = new TreeNode(t1.val+ t2.val);
            head.left = mergeTrees(t1.left, t2.left);
            head.right = mergeTrees(t1.right, t2.right);
        }

        return head;
    }


    public static void main(String[] args) {
        CreateTree createTree = new CreateTree();
        MergeBinaryTree test = new MergeBinaryTree();

        ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(1,3,2,5));
        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(2,1,3,null,4,null,7));
        TreeNode t1 = createTree.sequenceConstruction(arr1, 0);
        TreeNode t2 = createTree.sequenceConstruction(arr2, 0);

        TreeNode tm = test.mergeTrees(t1, t2);
        createTree.preorderTravel(tm);
    }
}
