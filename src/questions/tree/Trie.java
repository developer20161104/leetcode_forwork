package questions.tree;

/**
 * 前缀树的定义：每个节点都有1个数组指针，指向26种可能的字母（此处为小写字母）
 */

class TrieNode{
    char value;
    TrieNode[] next = new TrieNode[26];
    boolean stop = false;

    public TrieNode(){
        this.value = ' ';
    }
    public TrieNode(char ch){
        this.value = ch;
    }
}

public class Trie {
    TrieNode head;

    /** Initialize your data structure here. */
    public Trie() {
        this.head = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        // 注意不能覆盖原始结点
        TrieNode p = head;
        for(char ch:word.toCharArray()){
            if(p.next[ch-'a'] == null){
                TrieNode q = new TrieNode(ch);
                p.next[ch-'a'] = q;
            }
            p = p.next[ch-'a'];
        }
        p.stop = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = head;
        for(char ch: word.toCharArray()){
            if(p.next[ch-'a'] == null)
                return false;
            p = p.next[ch-'a'];
        }

        return p.stop;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode p = head;
        for(char ch: prefix.toCharArray()){
            if(p.next[ch-'a'] == null)
                return false;
            p = p.next[ch-'a'];
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("apps");
        System.out.println(trie.search("app"));     // 返回 true

    }
}
