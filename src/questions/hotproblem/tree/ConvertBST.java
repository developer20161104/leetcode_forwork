package questions.hotproblem.tree;

import java.util.ArrayList;
import java.util.Arrays;

public class ConvertBST {
    int val = 0;
    public TreeNode convertBST(TreeNode root) {
        // 遍历顺序变成右中左即可
        if(root == null)
            return root;

        convertBST(root.right);
        val += root.val;
        root.val = val;
        convertBST(root.left);

        return root;
    }

    public static void main(String[] args) {
        CreateTree createTree = new CreateTree();
        TreeNode t = createTree.sequenceConstruction(new ArrayList<>(Arrays.asList(
                4,1,6,0,2,5,7,null,null,null,3,null,null,null,8
        )), 0);

        ConvertBST test = new ConvertBST();
        createTree.preorderTravel(test.convertBST(t));
    }
}
