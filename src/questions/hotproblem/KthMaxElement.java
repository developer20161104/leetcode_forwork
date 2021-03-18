package questions.hotproblem;

import java.util.Random;

/**
 *  采用快速排序的思想，找出第k大的元素
 *  快速排序的切分操作使得每次处理后总会出现一个有序位
 *
 */

public class KthMaxElement {
    private static Random random = new Random(System.currentTimeMillis());

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if(len < k)
            return -1;

        int target = len-k, left=0,right=len-1;
        while (true){
            int index = partition(nums, left, right);
            if(index == target)
                return nums[target];
            else if(index < target){
                // 当前定位的下标小于待查找目标时，将左指针右移
                left = index+1;
            }else
                right = index-1;
        }
    }

    private int partition(int[] nums, int left, int right){
        // 随机选择一个作为pivot，防止出现最坏情况
        if(right > left)
            swap(nums, left, left+1+random.nextInt(right-left));

        int pivot = nums[left];
        // 当前小于 pivot 的最右边元素位置
        int index = left;

        for(int i=left+1;i<=right;i++){
            if(pivot > nums[i]){
                swap(nums, ++index, i);
            }
        }

        swap(nums, index, left);
        return index;
    }

    private void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        KthMaxElement test = new KthMaxElement();
        System.out.println(test.findKthLargest(new int[]{5,4,6,3,7,2,1}, 2));
    }
}
