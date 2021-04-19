package questions.hotproblem.backtracking;

import questions.hotproblem.tree.CreateTree;
import questions.hotproblem.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: leetcode
 * @Date: 2021-04-14 13:32
 * @Author: Lab
 * @Description:
 */
public class RootTracing {
    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        res = new ArrayList<List<Integer>>();
        getPath(root, target, new LinkedList<Integer>(), 0);

        return res;
    }

    void getPath(TreeNode root, int target, LinkedList<Integer> curList, int sum){
        if(root == null){
            return;
        }

        sum+= root.val;
        curList.add(root.val);
        if(root.left == null && root.right == null){
            if(sum == target){
                res.add(new LinkedList<>(curList));
            }
            sum -= root.val;
            curList.removeLast();
            return;
        }
        getPath(root.left, target, curList, sum);

        getPath(root.right, target, curList, sum);

    }

    public static void main(String[] args) {
        CreateTree test = new CreateTree();
        RootTracing show = new RootTracing();

        ArrayList<Integer> arrObject = new ArrayList<>(Arrays.asList(5,4,8,11,null,13,4,7,2,5,1));
        TreeNode T = test.sequenceConstruction(arrObject, 0);

        show.pathSum(T, 22);
    }
}
