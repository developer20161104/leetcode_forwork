package questions.hotproblem;

import java.util.HashMap;

class Node{
    public int key,val;
    public Node pre, next;
    public Node(int k,int v){
        this.key = k;
        this.val = v;
    }
}

// 双向链表的操作
class DoubleList{
    Node head = new Node(-1,-1);
    Node tail = new Node(-2,-2);
    int len = 0;

    public DoubleList() {
        head.next = tail;
        head.pre = null;
        tail.next = null;
        tail.pre = head;
    }

    public void addFirst(Node x){
        // 双链表的操作用图比较形象
        x.next = head.next;
        head.next = x;
        x.pre = head;
        x.next.pre = x;

        this.len ++;
    }

    public void remove(Node x) {
        x.next.pre = x.pre;
        x.pre.next = x.next;

        this.len--;
    }

    public Node removeLast(){
        Node p = tail.pre;
        p.pre.next = p.next;
        tail.pre = p.pre;

        this.len--;
        return p;
    }

    public int size(){
        return this.len;
    }

}

public class LRUCache {
    private HashMap<Integer, Node> map;
    DoubleList cache;
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        int val = map.get(key).val;
        // 在进行查找操作后，提升当前元素的优先级
        put(key, val);

        return val;
    }

    public void put(int key, int value) {
        Node x = new Node(key,value);
        // 包含相同key时，删除原始Node，更新并移位（提高优先级）
        if(map.containsKey(key)){
            cache.remove(map.get(key));
            cache.addFirst(x);
            // 不包含时，则先看是否存满
        }else {
            if(cache.size() == this.cap){
                // 存满了就要删最后一个
                Node val = cache.removeLast();
                map.remove(val.key);
            }

            cache.addFirst(x);
        }

        // map映射一定要更新
        map.put(key, x);
    }

    public static void main(String[] args) {
        LRUCache test = new LRUCache(2);

        test.put(2,1);
        test.put(2,2);
        System.out.println(test.get(2));
        test.put(1,1);
        test.put(4,1);
        System.out.println(test.get(2));

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */