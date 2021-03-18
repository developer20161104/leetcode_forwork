package questions.hotproblem;

public class SimpleQuestion {
    public int removeDuplicates(int[] nums) {
        // 与 python 有一些不同
        // 思路为双指针法，快指针负责遍历，慢指针负责保留新长度
        // 时间复杂度为O(n)，空间复杂度为O(1)
        int i = 0, j = 0, len = nums.length;
        if(len == 0) return i;

        for(;j<len;++j){
            if(nums[i] != nums[j]){
                ++i;
                nums[i] = nums[j];
            }
        }

        // 这里需要把最后一个元素也给加上去
        return i+1;
    }

    public int removeElement(int[] nums, int val) {
        // 同样还是双指针，快指针进行遍历，慢指针记录当前保留的长度
        // 时间复杂度为O(n)，空间复杂度为O(1)
//        int i = 0, j = 0, len = nums.length;
//        if(len == 0) return i;
//
//        for(;j<len;++j){
//            if(nums[j] != val){
//                nums[i] = nums[j];
//                ++i;
//            }
//        }
//
//        return i;

        // 优化，对于要删除的元素数量比较少的时候，
        // 可以考虑将当前要删除的元素与最后的元素互换，然后将长度减掉即可
        int i=0, n=nums.length;
        while(i<n){
            if(nums[i] == val){
                // 这步置换有点东西
                nums[i] = nums[n-1];
                --n;
            }
            else ++i;
        }

        return n;
    }

    public int searchInsert(int[] nums, int target) {
        // 实质为二分查找
        // 该方法实质为左查找，即寻找最左边合适的位置进行插入
        int left=0, right=nums.length;
        while(left < right){
            int mid = left+(right-left)/2;
            if(nums[mid] < target){
                left = mid+1;
            }else right = mid;
        }

        return left;
    }

    public static void main(String[] args) {
        SimpleQuestion test = new SimpleQuestion();

        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        //
        //  System.out.println(test.removeDuplicates(nums));

        //
        //  System.out.println(test.removeElement(nums, 2));

        //
        System.out.println(test.searchInsert(new int[]{1,2,2,3,5,6}, 7));
    }
}
