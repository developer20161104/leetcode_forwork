package questions.hotproblem.tree;

import java.util.*;

/**
 * @program: leetcode_forwork
 * @Date: 2021/1/26 15:58
 * @Author: Mr.Yang
 * @Description:
 */
public class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        // 选择两者中的最大值作为最深的深度
        return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthIter(TreeNode root){
        int depth = 0;
        if(root == null)
            return depth;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int length = queue.size();
            // 遍历当前队列的所有元素，并将其中符合要求的节点加入队列
            for(int i=0;i<length;i++){
                TreeNode curNode = queue.poll();

                if(curNode.left != null)
                    queue.add(curNode.left);
                if(curNode.right != null)
                    queue.add(curNode.right);
            }
            depth += 1;
        }

        return depth;
    }

    public static void main(String[] args) {
        MaxDepthBinaryTree test = new MaxDepthBinaryTree();
        CreateTree createTree = new CreateTree();

        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,null,2,null,null,null));
        System.out.println(test.maxDepth(createTree.sequenceConstruction(arr, 0)));
        System.out.println(test.maxDepthIter(createTree.sequenceConstruction(arr, 0)));
    }
}
