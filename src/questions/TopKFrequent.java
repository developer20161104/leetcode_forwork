package questions;

import java.util.*;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 调用内库，先获取各个元素的出现次数
        HashMap<Integer, Integer> map = new HashMap<>();

//        for(int num: nums){
//            if(map.containsKey(num))
//                map.put(num, map.get(num)+1);
//            else
//                map.put(num, 1);
//        }
        // 可以简写
        for(int num:nums)
            map.put(num, map.getOrDefault(num,0)+1);

        // 再进行逆序排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((x,y) -> y.getValue()-x.getValue());

        // 截取前k个元素即可
        if(k < map.keySet().size())
            list = list.subList(0, k);
        return list.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    public int[] topKFrequentSelf(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num: nums){
            if(map.containsKey(num))
                map.put(num, map.get(num)+1);
            else
                map.put(num, 1);
        }

        // 将获取的元素与次数关系对放入指定大小的小根堆中
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((x, y) -> x.getValue()-y.getValue());
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(queue.size() < k)
                queue.add(entry);
            else if(entry.getValue() > queue.peek().getValue()){
                queue.remove();
                queue.add(entry);
            }
        }

        return queue.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    public static void main(String[] args) {
        TopKFrequent test = new TopKFrequent();

        System.out.println(Arrays.toString(test.topKFrequentSelf(new int[]{
                5,2,5,3,5,3,1,1,3
        }, 2)));
    }
}
