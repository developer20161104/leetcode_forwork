package questions.problemperday;

import questions.hotproblem.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: leetcode
 * @Date: 2021-06-30 21:35
 * @Author: Lab
 * @Description:
 */
public class Serialize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res= new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode cur= q.poll();
                if(cur != null){
                    res.append(cur.val+",");
                    q.offer(cur.left);
                    q.offer(cur.right);
                }else res.append("null,");
            }
        }

        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.split(",");
        TreeNode head = null;
        int len = datas.length;
        if(len == 0 || datas[0].equals("null")){
            return head;
        }

        Queue<TreeNode> q= new LinkedList<>();
        head = new TreeNode(Integer.parseInt(datas[0]));
        q.offer(head);
        int i=1;
        while(!q.isEmpty()){
            TreeNode node= q.poll();

            if(!datas[i].equals("null")){
                node.left = new TreeNode(Integer.parseInt(datas[i]));
                q.offer(node.left);
            }
            i++;

            if(!datas[i].equals("null")){
                node.right = new TreeNode(Integer.parseInt(datas[i]));
                q.offer(node.right);
            }
            i++;
        }

        return head;
    }
}
