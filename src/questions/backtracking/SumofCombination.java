package questions.backtracking;

import java.util.*;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/14 15:19
 * @Author: Mr.Yang
 * @Description:
 */
public class SumofCombination {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> tot = new LinkedList<>();
        ArrayDeque<Integer> curList = new ArrayDeque<>();
        Arrays.sort(candidates);

        getCollection(tot, 0, target, curList, candidates);
        return tot;
    }

    private void getCollection(List<List<Integer>> tot, int index, int target, ArrayDeque<Integer> curList, int[] candidates){
        if(target == 0){
            // 需要进行深拷贝
            List<Integer> cur = new LinkedList<>(curList);
//            for(Integer value: curList)
//                cur.add(value);
            tot.add(cur);
            return;
        }

        // 通过设置下一轮搜索的起点，使得后续重复遍历被过滤掉
        for(int i=index;i<candidates.length;i++){
            // 滤重操作
//            if(i >0 && candidates[i] == candidates[i-1])
//                continue;

            target -= candidates[i];
            if(target < 0)
                return;

            curList.add(candidates[i]);

            System.out.print("add " + candidates[i] + " curlist ");
            curList.stream().forEach(System.out::print);
            System.out.println();

            // 使用 i 会过滤掉前面的值， 使用index则会全部加入遍历
            getCollection(tot, i, target, curList, candidates);
            // 移除当前项
            curList.removeLast();

            System.out.print("remove " + candidates[i] + " curList ");
            curList.stream().forEach(System.out::print);
            System.out.println();

            target += candidates[i];
        }
    }

    public static void main(String[] args) {
        SumofCombination test = new SumofCombination();

        List<List<Integer>> res = test.combinationSum(new int[]{2,3,5}, 8);

        for(List<Integer> curList: res)
        {
            curList.stream().forEach(System.out::print);
            System.out.println();
        }
    }
}
