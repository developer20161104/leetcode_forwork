package questions.hotproblem.chain;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/3 12:43
 * @Author: Mr.Yang
 * @Description:
 */
public class ReverseChain {
    public ListNode reverseListIter(ListNode head) {
        // 思想：每次遍历head结点，然后使用头插法插入新链表即可
//        ListNode h = new ListNode();
//        ListNode p = null, travel = null;
//        h.next = p;
//        while (head != null){
//            p = h.next;
//            travel = new ListNode(head.val);
//            h.next = travel;
//            travel.next = p;
//            head = head.next;
//        }
//
//        return h.next;

        // 头插法的标准写法
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            // 先保留后续的链表关系
            ListNode p = cur.next;
            // 将当前的节点加入新链表
            cur.next = pre;
            // 移动新链表的插入位置
            pre = cur;
            // 移动原始链表的指针
            cur = p;
        }

        return pre;
    }

    public ListNode reverseListRecur(ListNode head){
//        ListNode h = new ListNode();
//        if(head == null)
//            return h.next;
//
//        ListNode p = h.next, q = new ListNode(head.val);
//        h.next = q;
//        q.next = p;
//        h.next = reverseListRecur(head.next);
//
//        return h.next;

        if(head == null || head.next == null)
            return head;

        ListNode newHead = reverseListRecur(head.next);
        // head = 4, newHead = 5,  5 -> 4
        head.next.next = head;
        // 4 -> null
        head.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head = head.createChain(new int[]{1,2,3,4,5});
        ReverseChain test = new ReverseChain();

//        head.travelChain(test.reverseListIter(head));

        head.travelChain(test.reverseListRecur(head));
    }
}
