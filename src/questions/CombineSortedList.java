package questions;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class CombineSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 先尝试循环法解决
//        ListNode l = new ListNode();
//        ListNode head = l;
//        // 可以进行一些优化，如果出现了单链，则直接把链表串联即可
//        while(l1 != null || l2 != null){
//            l.next = new ListNode();
//            l = l.next;
//
//            if(l1 != null && (l2 == null || l1.val < l2.val)){
//                l.val = l1.val;
//                l1 = l1.next;
//            }else{
//                l.val = l2.val;
//                l2 = l2.next;
//            }
//        }
//
//        return head.next;

        // 可以优化的部分：不用创建新的节点，直接使用原始节点即可
        ListNode l = new ListNode(0);
        ListNode head = l;

        // 两个链表都存在元素时
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                l.next = l1;
                l1 = l1.next;
            }else {
                l.next = l2;
                l2 = l2.next;
            }

            l = l.next;
        }

        // 只有一个链表存在元素，此时直接拼接即可
        if(l1 != null){
            l.next = l1;
        }else
            l.next = l2;

        return head.next;
    }

    public ListNode mergeTwoListRecursive(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        // 对于递归，如果是相互关联的，则需要知晓的是对什么东西进行连接，以及连接需要的是什么
        ListNode head = l1.val < l2.val ? l1:l2;
        head.next = mergeTwoListRecursive(head.next, l1.val<l2.val ? l1.next:l2.next);
        return head;
    }

    public ListNode mergeTwoListRecursive2(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        // 选择递归小值链表，拼接由栈来完成
        if(l1.val < l2.val){
            l1.next = mergeTwoListRecursive2(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoListRecursive2(l1, l2.next);
            return l2;
        }
    }

    public ListNode createListNode(int[] arr){
        ListNode l = new ListNode();
        ListNode head = l;
        for(int value: arr){
            l.val = value;
            l.next = new ListNode();
            l = l.next;
        }

        return head;
    }

    public static void main(String[] args) {
        CombineSortedList test = new CombineSortedList();

        ListNode l1 = test.createListNode(new int[]{1,2,4});
        ListNode l2 = test.createListNode(new int[]{1,3,4});

        System.out.println();

        ListNode res = test.mergeTwoLists(l1, l2);

        res = test.mergeTwoListRecursive2(l1, l2);
        System.out.println(res);
    }
}
