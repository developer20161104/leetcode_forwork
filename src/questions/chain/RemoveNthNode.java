package questions.chain;

import basement.foundation.Reflection;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/8 13:26
 * @Author: Mr.Yang
 * @Description:
 */
public class RemoveNthNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow=head,fast = head;
        int count=0, raw=n;
        // 先跳跃到指定位置
        while(fast.next!=null && n!=0){
            fast = fast.next;
            n--;
            count++;
        }

        // 再进行双向向下遍历
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
            count++;
        }

        // 检验被删除位置是否为首位
        if(count==raw-1)
            return head.next;

        // 其余情况直接删除        slow.next = slow.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode();
        node = node.createChain(new int[]{1,2,3});

        RemoveNthNode test = new RemoveNthNode();
        node.travelChain(test.removeNthFromEnd(node, 1));
    }
}
