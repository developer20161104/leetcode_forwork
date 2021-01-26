package questions.tree;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/1/26 11:51
 * @Author: Mr.Yang
 * @Description:
 */
public class CreateTree {
    public TreeNode sequenceConstruction(ArrayList<Integer> arr, int index){
        // 初始化直接置为null，避免后续多余的判断
        TreeNode head = null;

        if(index < arr.size() && arr.get(index) != null){
            head = new TreeNode();
            head.val = arr.get(index);
            head.left = sequenceConstruction(arr, 2*index+1);
            head.right = sequenceConstruction(arr, index*2+2);
        }

        return head;
    }

    public void preorderTravel(TreeNode head){
        if(head != null){
            preorderTravel(head.left);
            System.out.println(head.val);
            preorderTravel(head.right);
        }
    }

    public static void main(String[] args) {
        CreateTree test = new CreateTree();

        ArrayList<Integer> arrObject = new ArrayList<>(Arrays.asList(1,2,2,null,3,null,3));
        TreeNode T = test.sequenceConstruction(arrObject, 0);

        test.preorderTravel(T);
    }
}
