package questions.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/23 10:51
 * @Author: Mr.Yang
 * @Description:
 */
public class SubSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> curSet = new LinkedList<>();

        // 不能一次完成就使用循环多次调用
        for(int i=0;i<= nums.length;i++)
            getSort(nums, res, curSet, 0, i);

        return res;
    }

    private void getSort(int[] nums, List<List<Integer>> res,List<Integer> curSet, int index, int k){
        if(curSet.size() == k){
            res.add(new LinkedList<>(curSet));
            return;
        }

        // 回溯法
        for(int i=index;i< nums.length;i++){
            curSet.add(nums[i]);
            // 使用i+1与i=index防止重复
            getSort(nums, res, curSet, i, k);
            curSet.remove(new Integer(nums[i]));
        }

    }

    public List<List<Integer>> subsetsIter(int[] nums){
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> curList = new LinkedList<>();
        int n = nums.length;

        // 位运算获取进制数 2^n
        for(int i=0;i< (1 << n);++i){
            curList.clear();

            // 遍历整体集合
            for(int j=0;j<n;++j)
                // 对应位相与不为0，表示待放入元素
                if((i & (1 << j)) != 0)
                    curList.add(nums[j]);
            res.add(new LinkedList<>(curList));
        }

        return res;
    }

    public static void main(String[] args) {
        SubSet test = new SubSet();

        List<List<Integer>> res = test.subsets(new int[]{1,2,3});
        for(List<Integer> curlist: res){
            curlist.stream().forEach(System.out::print);
            System.out.println();
        }
    }
}
