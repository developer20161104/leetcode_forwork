package questions.hotproblem.tree;

public class FlattenBinaryTree {
    // 先找到左子树最右边的节点，拼接root节点的右节点，再将root节点右节点指向root的原始左节点
    // 基本流程演示
    /**
     *     1                1                1                1
     *   2   5     ->         2        ->     2         ->     2
     *  3 4 6 7              3 4               3                3
     *                          5               4                4
     *                         6 7               5                5
     *                                          6 7                6
     *                                                              7
     * @param root
     */
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left != null){
                TreeNode rightestNode = root.left;
                while(rightestNode.right != null)
                    rightestNode = rightestNode.right;

                // 先将root右侧节点作为找到的节点的右节点
                rightestNode.right = root.right;
                // 再将首部连接
                root.right = root.left;
                // 最后才将左边置null
                root.left = null;
            }

            // 进行下一个节点的操作
            root = root.right;
        }
    }

    TreeNode pre = null;
    // 解法二：右左中后序遍历，使用头插法，防止右节点被取代
    public void flattenPostorder(TreeNode root) {
        // 利用递归的特点来返回头结点
        if(root != null){
            flattenPostorder(root.right);
            flattenPostorder(root.left);

            // 头插法
            root.right = pre;
            root.left = null;
            pre = root;
        }
    }
}
