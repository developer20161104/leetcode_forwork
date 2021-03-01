package questions.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/14 16:12
 * @Author: Mr.Yang
 * @Description:
 */
public class FullSort {
    // 对于重复元素，采用标记数组来进行筛选
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> curList = new LinkedList<>();

        if(nums.length < 1)
            return res;

        used = new boolean[nums.length];
        GetSort(res,  nums,curList);
        return res;
    }

    private void GetSort(List<List<Integer>> tot, int[] nums, List<Integer> curList){
        if(nums.length == curList.size()){
            tot.add(new LinkedList<>(curList));
            return;
        }

        // 如果此处选择从index开始，则不会选前面的元素
        for(int i=0;i<nums.length;i++){
            if(!used[i]){
                curList.add(nums[i]);
                used[i] = true;

                GetSort(tot, nums,  curList);
                curList.remove(new Integer(nums[i]));
                used[i] = false;
            }

        }
    }

    public static void main(String[] args) {
        FullSort test = new FullSort();
        List<List<Integer>> res =test.permute(new int[]{1,2,3});

        for(List<Integer> curList: res){
            curList.stream().forEach(System.out::print);
            System.out.println();
        }
    }
}
