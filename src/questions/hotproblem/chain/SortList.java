package questions.hotproblem.chain;

public class SortList {
    public ListNode sortList(ListNode head) {
        // 目前想到的是暴力法 O(n2)
        ListNode sortedHead = new ListNode(Integer.MIN_VALUE);
        while(head != null){
            ListNode p = sortedHead;
            while(p.next != null && p.next.val < head.val)
                p = p.next;

            ListNode q = new ListNode(head.val);
            q.next = p.next;
            p.next = q;

            head = head.next;
        }

        return sortedHead.next;
    }

    public ListNode sortListByMerge(ListNode head){
        return head==null ? head : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head){
        if(head.next == null)
            return head;

        // 利用快慢指针获取中间位置
        // 需要获取slow指针的前驱指针（此处有差异）
        // 问题之处：对slow操作会导致断链，因此需要一个前驱指针来完成断开
        ListNode fast=head, slow=head, pre = null;
        while (fast != null && fast.next != null){
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        pre.next = null;
        return merge(mergeSort(head), mergeSort(slow));
    }

    private ListNode merge(ListNode left, ListNode right){
        ListNode sortedHead = new ListNode(0);
        ListNode cur = sortedHead;

        while(left != null && right != null){
            if(left.val > right.val){
                cur.next = right;
                cur = cur.next;
                right = right.next;
            }else {
                cur.next = left;
                cur = cur.next;
                left = left.next;
            }
        }

        if(left!= null)
            cur.next = left;

        if(right!= null)
            cur.next =right;

        return sortedHead.next;
    }

    public static void main(String[] args) {
        SortList test =new SortList();
        ListNode n = new ListNode().createChain(new int[]{-1,5,3,4,0});

//        n.travelChain(test.sortList(n));
        n.travelChain(test.sortListByMerge(n));
    }
}
