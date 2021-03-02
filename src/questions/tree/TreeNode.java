package questions.tree;

/**
 *  @program: leetcode_forwork
 *  @Date: 2021/1/26 11:52
 *  @Author: Mr.Yang
 *  @Description:
 */

public class TreeNode {
   public int val;
   public TreeNode left;
   public TreeNode right;
   public TreeNode() {}
   public TreeNode(int val) { this.val = val; }
   TreeNode(int val, TreeNode left, TreeNode right) {
       this.val = val;
       this.left = left;
       this.right = right;
   }
}
