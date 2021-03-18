package questions.problemperday;

import questions.hotproblem.chain.ListNode;

/**
 * @program: leetcode
 * @Date: 2021-03-18 10:33
 * @Author: Lab
 * @Description: 反转链表II
 */
public class ReverseList {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode p = new ListNode(), q, newHead=p;
        int i=1;
        // 顺序插入
        while(i<left){
            q = new ListNode(head.val);
            p.next = q;
            p = p.next;
            head = head.next;
            i++;
        }

        // 头插法
        while(i<=right){
            q = new ListNode(head.val);
            q.next = p.next;
            p.next = q;
            head = head.next;
            i++;
        }

        // 最后需要将多余的进行拼接
        while(left <= right){
            p = p.next;
            left++;
        }

        p.next = head;

        return newHead.next;
    }

    public ListNode reverseBetweenSim(ListNode head, int left, int right){
        ListNode rehead = new ListNode();
        rehead.next = head;
        ListNode pre = rehead;
        int i=1;
        while(i<left){
            pre = pre.next;
            i++;
        }

        head = pre.next;
        while(i<right){
            ListNode nex = head.next;

            // 先断除后续节点的影响
            head.next = nex.next;
            // 再对nex进行头插法
            nex.next = pre.next;
            pre.next = nex;
            i++;
        }

        return rehead.next;
    }

    public static void main(String[] args) {
        ReverseList test = new ReverseList();

        ListNode head = new ListNode().createChain(new int[]{
                1,2,3,4,5
        });

        head = test.reverseBetweenSim(head, 2,4);
        head.travelChain(head);
    }
}
