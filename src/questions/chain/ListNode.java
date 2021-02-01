package questions.chain;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/1 15:17
 * @Author: Mr.Yang
 * @Description:
 */
public class ListNode {
      int val;
      ListNode next;
      ListNode(){
          val = 0;
          next = null;
      }

      ListNode(int x) {
          val = x;
          next = null;
      }

      public ListNode createChain(int[] nums){
          ListNode p = new ListNode();
          ListNode head = p;

          for(int num: nums){
              ListNode curNode = new ListNode(num);
              p.next = curNode;
              p = curNode;
          }

          return head.next;
      }

      public void travelChain(ListNode head){
          while(head != null){
              System.out.println(head.val);
              head = head.next;
          }
      }

    public static void main(String[] args) {
        ListNode test = new ListNode();
        test = test.createChain(new int[]{1,2,3,1,2,3,4});

        test.travelChain(test);
    }
}
