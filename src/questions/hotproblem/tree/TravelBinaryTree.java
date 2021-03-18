package questions.hotproblem.tree;

import java.util.*;

/**
 * 二叉树的各种遍历 先序、后序、中序、层次
 * 包含了迭代与递归两个版本
 */

public class TravelBinaryTree {
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        // 中序递归版本
        if(root!= null){
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }

        return res;
    }

    public List<Integer> inorderTraversalIter(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while(!stack.isEmpty() && root!=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            res.add(root.val);

            // 这一点很精髓
            root = root.right;
        }

        return res;

    }

    public List<Integer> preorderTraversal(TreeNode root){
        if(root != null){
            res.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }

        return res;
    }

    public List<Integer> preorderTraversalIter(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty() && root != null){
            // 先序遍历遍历即访问
            while(root!= null){
                stack.push(root);
                res.add(root.val);
                root = root.left;
            }

            // 当前节点为空时，返回上一个节点的右节点
            while(root ==null && !stack.isEmpty()){
                root = stack.pop().right;
            }
        }

        return res;
    }


    public List<Integer> postorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        // 记录上次访问的节点
        TreeNode pre = null;
        while(root!=null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }

            // 从左节点返回不需要访问
            root = stack.peek();

            // 节点需要访问为两种情况：
            // 1. 当前节点为叶子节点
            // 2. 当前节点的右节点是上次访问的节点
            if(root.right == null || root.right == pre){
                // 记录先前节点
                pre = root;
                res.add(stack.pop().val);
                // 让root到下一轮更新
                root = null;
            }else
                root = root.right;
        }

        return res;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            List<Integer> curList = new ArrayList<>();
            int curSize = queue.size();
            for(int i=0;i<curSize;i++){
                TreeNode curNode = queue.poll();

                // 直接过滤null节点
                if(curNode != null){
                    curList.add(curNode.val);

                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }

            }

            // 此处需要去除多余的空表
            if(!curList.isEmpty())
                res.add(curList);
        }

        return res;
    }

    public static void main(String[] args) {
        CreateTree test = new CreateTree();

        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        TreeNode t = test.sequenceConstruction(arr, 0);

        TravelBinaryTree show = new TravelBinaryTree();
//        show.postorderTraversal(t).stream().forEach(System.out::println);

        List<List<Integer>> res = show.levelOrder(t);
        for(List<Integer> cur: res){
            cur.stream().forEach(System.out::print);
            System.out.println();
        }
    }
}
