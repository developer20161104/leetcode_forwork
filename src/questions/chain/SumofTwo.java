package questions.chain;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/6 11:20
 * @Author: Mr.Yang
 * @Description:
 */

public class SumofTwo {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 设置增位
        ListNode head = new ListNode(), p = head;
        int incre = 0;
        while(l1 != null && l2!=null){
            ListNode q = new ListNode((l1.val+ l2.val+incre)%10);
            p.next = q;
            p = q;

            incre = (l1.val+ l2.val+incre)/10;
            l1 = l1.next;
            l2 = l2.next;
        }

        // 补充完后续的链表
        ListNode largeNode = l1==null ? l2:l1;
        while(largeNode != null){
            p.next = new ListNode((largeNode.val+incre) % 10);
            incre = (largeNode.val+incre) / 10;

            p = p.next;
            largeNode = largeNode.next;
        }

        // 注意增位引起的新结点
        if(incre != 0)
            p.next = new ListNode(incre);

        return head.next;
    }


    public static void main(String[] args) {
        ListNode node = new ListNode();

        SumofTwo test = new SumofTwo();
        ListNode node1 = node.createChain(new int[]{1,2});
        ListNode node2 = node.createChain(new int[]{9,9,9});

        node.travelChain(test.addTwoNumbers(node1, node2));
    }
}
