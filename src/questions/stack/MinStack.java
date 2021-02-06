package questions.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/1 15:47
 * @Author: Mr.Yang
 * @Description:
 */
public class MinStack {
    /** initialize your data structure here. */
    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack() {
        this.stack = new LinkedList<>();
        this.minStack = new LinkedList<>();
        this.minStack.push(Integer.MAX_VALUE);

    }

    public void push(int x) {
        this.stack.add(x);
        this.minStack.add(Math.min(this.minStack.peekLast(), x));
    }

    public void pop() {
        this.stack.removeLast();
        this.minStack.removeLast();
    }

    public int top() {
        return this.stack.peekLast();
    }

    public int getMin() {
        return this.minStack.peekLast();
    }
}
