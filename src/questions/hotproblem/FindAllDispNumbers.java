package questions.hotproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/5 11:23
 * @Author: Mr.Yang
 * @Description:
 */
public class FindAllDispNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 利用数组下标的有序性来进行未出现元素的查询
        // 同时，将原始位置取反以便后续的标记（神仙思路）

        for(int i=0;i<nums.length;i++){
            int index = Math.abs(nums[i])-1;
            nums[index] *= nums[index] > 0 ? -1 : 1;
        }

        // 原始写法
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i] > 0)
                res.add(i+1);
        }

        // java流写法：注意操作的对象为下标:虽然缩短了语句，但是比较耗时
        return IntStream.range(0, nums.length).filter(index -> nums[index]>0).map(index -> index+1).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        FindAllDispNumbers test = new FindAllDispNumbers();

        List<Integer> res = test.findDisappearedNumbers(new int[]{1,1,2,2});
        res.stream().forEach(System.out::println);
    }
}
