package questions.hotproblem.chain;

import java.util.HashSet;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/1 15:17
 * @Author: Mr.Yang
 * @Description:
 */
public class CircleChain {
    public boolean hasCycle(ListNode head) {
        // 尝试快慢指针
        ListNode fast = head, slow = head;
        while(fast != null){
            fast = fast.next;
            slow = slow.next;

            // 中途出现null则一定不存在环
            if(fast == null)
                break;

            fast = fast.next;
            // 如果存在环则一定会出现相同位置
            if(fast !=null && fast.val == slow.val)
                return true;
        }

        return false;
    }

    // 求取环形链表的第一个节点
    // Hashset法，将元素存入set中，首次出现重复为环的首节点
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while(head != null){
            if(set.contains(head)){
                return head;
            }else{
                set.add(head);
                head = head.next;
            }
        }

        return null;
    }

    // 双指针法： 设长度为 a+b
    // 第一次先找到两指针重合位置，此时慢指针走了 nb 步（联立f=2s，f=s+nb可得）
    // 第二次让慢指针再走 a 步，即可找到环首位置
    public ListNode detectCycleDoublePoints(ListNode head) {
        ListNode fast=head, slow = head;
        while(fast != null && fast.next != null){
            // 先找到重合位置
            fast = fast.next.next;
            slow = slow.next;

            // 再走 a 步找到环首
            if(fast == slow){
                fast = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }

                return slow;
            }
        }

        return null;

    }
}
