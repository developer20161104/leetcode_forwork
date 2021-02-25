package questions.tree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // 利用先序遍历与中序遍历进行构造
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeSteps(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }


    private TreeNode buildTreeSteps(int[] preorder, int[] inorder, int low,int high, int left, int right){
        // 当先序中的元素被取完后，结束操作
        if(low == high)
            return null;

        TreeNode t = new TreeNode(preorder[low]);
        int i;
        // 寻找后序上相同值的位置
        for(i=left;i<right;i++)
            if(inorder[i] == preorder[low])
                break;

        // 此处需要知道多少个数用于子树的构造，因此也需要约束前序遍历的下标
        int leftnum = i-left;
        t.left = buildTreeSteps(preorder, inorder, low+1, leftnum+low+1, left, i);
        t.right = buildTreeSteps(preorder, inorder, low+leftnum+1, high, i+1, right);

        return t;
    }

    public static void main(String[] args) {
        CreateTree test = new CreateTree();

        ArrayList<Integer> arrObject = new ArrayList<>(Arrays.asList(1,2,2,null,3,null,3));
        TreeNode T = test.sequenceConstruction(arrObject, 0);

        test.preorderTravel(T);

        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};

        TreeNode tree = test.buildTree(preorder, inorder);
        test.preorderTravel(tree);
    }
}
