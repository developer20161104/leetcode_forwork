package questions.string;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/17 13:57
 * @Author: Mr.Yang
 * @Description:
 */
public class GroupAnagrams {
    // 将每个字母转化为一个不同的质数，相互间的组合转化为乘积，用来区分
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        // 需要注意的是Java的int类型有长度限制，因此需要使用long类型
        HashMap<Long, List<String>> maps = new HashMap<>();

        for(int i=0;i< strs.length;i++){
            // 使用质数的乘积作为key值
            long sum = 1;
            for(char ch: strs[i].toCharArray()){
                sum *= prime[ch-97];
            }

            // 添加到map中
            if(maps.containsKey(sum))
                maps.get(sum).add(strs[i]);
            else
                maps.put(sum, new ArrayList<>(Arrays.asList(strs[i])));
        }

        return new ArrayList<>(maps.values());
    }

    public static void main(String[] args) {
        GroupAnagrams test = new GroupAnagrams();

        List<List<String>> arr = test.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> curlist : arr)
        {
            curlist.stream().forEach(System.out::print);
            System.out.println();
        }
    }

}
