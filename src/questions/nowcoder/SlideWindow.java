package questions.nowcoder;

import java.util.*;

/**
 * @program: leetcode
 * @Date: 2021-04-26 09:08
 * @Author: Lab
 * @Description:
 */
public class SlideWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // use priority queue
        int len = nums.length;
        if(len < 1){
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)-> b-a);
        int[] res = new int[len-k+1];
        int left=0,right=0, index=0;
        for(;right<len;right++){
            queue.add(nums[right]);
            if(right-left+1 > k){
                queue.remove(nums[left]);
                left++;
                res[index++] = queue.peek();
            }else if(right-left+1 == k){
                res[index++] = queue.peek();
            }
        }
        return res;
    }

    public int[] maxSlidingWindowQueue(int[] nums, int k) {
        int len = nums.length;
        if(len < 1){
            return new int[0];
        }

        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[len-k+1];
        int index=0;
        for(int i=0;i<len;i++){
            while(!queue.isEmpty() && queue.peekLast() < nums[i]){
                queue.removeLast();
            }

            if(i-k >=0 && !queue.isEmpty() && queue.peekFirst()==nums[i-k]){
                queue.removeFirst();
            }

            queue.addLast(nums[i]);
            if(i-k+1>=0){
                res[index++] = queue.peekFirst();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SlideWindow().maxSlidingWindowQueue(new int[]{
                1, 3, -1, -3, 5, 3, 6, 7
        }, 3)));
    }
}
