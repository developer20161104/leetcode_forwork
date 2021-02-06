package questions.chain;

import java.util.ArrayList;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/4 20:12
 * @Author: Mr.Yang
 * @Description:
 */
public class Palindrome {
    public boolean isPalindrome(ListNode head) {
        // 方法一，数组双指针法
        // 先转化为数组，再利用双指针进行双边处理
        ArrayList<Integer> arr = new ArrayList<>();
        while(head!= null){
            arr.add(head.val);
            head = head.next;
        }

        int left = 0, right = arr.size()-1;
        while(left < right && arr.get(left).equals(arr.get(right))){
            left ++;
            right --;
        }

        return left >= right;
    }

    ListNode front;
    public boolean isPalindrome2(ListNode head){
        // 方法二：递归法，利用递归的从后向前的性质
        front = head;
        return judgement(head);
    }

    private boolean judgement(ListNode head){
        if(head == null)
            return true;

        boolean res = judgement(head.next) && head.val == front.val;
        front = front.next;
        return res;
    }

    public boolean isPalindrome3(ListNode head){
        // 方法三：快慢指针加上头插法
        ListNode slow= head, fast = head;
        ListNode pre=head, prepre = null;
        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;

            pre.next = prepre;
            // prepre保存的是先前的pre指针，因此此处为逆向构造
            prepre = pre;
        }

        if(fast != null)
            slow = slow.next;
        while(pre != null && slow != null){
            if(pre.val != slow.val)
                return false;

            pre = pre.next;
            slow = slow.next;
        }

        return true;

    }
    public static void main(String[] args) {
        Palindrome test = new Palindrome();
        ListNode t = new ListNode();
        t = t.createChain(new int[]{1,2,3,4,2,1});

        System.out.println(test.isPalindrome(t));

        System.out.println(test.isPalindrome2(t));

        System.out.println(test.isPalindrome3(t));
    }
}
