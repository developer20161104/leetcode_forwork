package questions.chain;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/3 10:27
 * @Author: Mr.Yang
 * @Description:
 */
public class IntersectChain {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 思想：从两者的相同长度处开始遍历，如果出现一致则必在相同长度处保持一致
        // 时间复杂度为O(2m)，空间复杂度为O(1)
        int lenA = getLength(headA), lenB = getLength(headB);
        ListNode longChain = headA, shortChain = headB;
        if(lenA < lenB){
            longChain = headB;
            shortChain = headA;
        }

        int distance = Math.abs(lenA - lenB);
        while(longChain !=null && distance!=0){
            longChain = longChain.next;
            distance--;
        }

        while(longChain != null){
            if(longChain == shortChain)
                return longChain;
            longChain = longChain.next;
            shortChain = shortChain.next;
        }

        return null;
    }

    private int getLength(ListNode head){
        int length = 0;
        while(head != null){
            length ++;
            head = head.next;
        }

        return length;
    }

    // 方法二：直接遍历，无需计算长度，利用了 a+b = b+a 的思想
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        if(headA == null || headB == null)
            return null;

        ListNode pa=headA, pb=headB;
        // 关键点，出现不一致的时候会同时指向null
        while(pa != pb){
            pa =  pa == null ? headB : pa.next;
            pb =  pb == null ? headA : pb.next;
        }

        return pa;
    }
}
