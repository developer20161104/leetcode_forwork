package questions.chain;

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
}
