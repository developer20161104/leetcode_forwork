package array;
import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 方法一：查表法，时间复杂度O(n)，空间复杂度为O(n)
        // 可继续优化
//        HashMap<Integer, Integer> dict = new HashMap<>();
//        int len = nums.length;
//
//        for(int i=0; i< len; ++i){
//            dict.put(nums[i],i);
//        }
//
//        for(int i=0; i< len; ++i){
//            int delta = target-nums[i];
//            if(dict.containsKey(delta) && dict.get(delta) != i) {
//                return new int[]{
//                    i, dict.get(delta)
//                } ;
//            }
//        }
//
//        return null;

        // 不一定需要先全部放进去再查找，可以边放边找
        HashMap<Integer, Integer> dict = new HashMap<>();
        int len = nums.length;

        int[] res = new int[]{0,0};
        for(int i=0; i<len;++i){
            if(dict.containsKey(target-nums[i]) && dict.get(target-nums[i]) != i){
                res[0] = i;
                res[1] = dict.get(target-nums[i]);
                break;
            }
            dict.put(nums[i], i);
        }

        return res;

    }

    public static void main(String[] args) {
        TwoSum test = new TwoSum();
        int[] res = test.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(res[0]+" "+res[1]);
    }
}
