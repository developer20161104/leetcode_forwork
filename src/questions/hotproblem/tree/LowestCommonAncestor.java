package questions.hotproblem.tree;

import java.util.ArrayList;
import java.util.Arrays;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 出现则返回节点
        if(root==null || root.val==p.val || root.val==q.val)
            return root;

        // 采用后序遍历，从下往上开始
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 右边出现节点，则返回右侧（此时为子节点）
        if(left == null) return right;
        // 左边出现节点，返回左侧（子节点）
        if(right == null) return left;
        // 两边都出现，说明该节点为最近公共祖先，因此返回该节点（父节点）
        return root;
    }

    public static void main(String[] args) {
        LowestCommonAncestor test = new LowestCommonAncestor();
        TreeNode t = new CreateTree().sequenceConstruction(new ArrayList<>(Arrays.asList(
                3,5,1,6,2,0,8,null,null,7,4
        )),0);

        System.out.println(test.lowestCommonAncestor(t, new TreeNode(7), new TreeNode(4)).val);
    }
}
