package questions.problemperday;

import questions.hotproblem.chain.ListNode;

/**
 * @program: leetcode
 * @Date: 2021-03-25 16:31
 * @Author: Lab
 * @Description:
 */
public class DeleteDuplicateList {

    public ListNode deleteDuplicates(ListNode head) {
        // 定义三个指针，分别指向前缀，当前以及之后三个部分
        ListNode pre = new ListNode(-101);
        pre.next = head;

        if(head == null)
            return pre.next;

        ListNode cur = head, next = head.next;
        while(next != null){
            while(next!=null && cur.val == next.val){
                cur = next;
                next = next.next;
            }

            // 如果前缀的下一个不是当前指针，则必存在重复
            if(pre.next != cur){
                // 由于返回的是头结点，因此如果第一个就开始重复，就需要特殊判断
                if(pre.next == head){
                    head = next;
                }
                pre.next = next;
            }else {
                // 不重复则直接向
                pre =cur;
            }

            if(next!=null){
                cur = next;
                next = next.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode().createChain(new int[]{
                2,2
        });

        l = new DeleteDuplicateList().deleteDuplicates(l);

        l.travelChain(l);
    }
}
