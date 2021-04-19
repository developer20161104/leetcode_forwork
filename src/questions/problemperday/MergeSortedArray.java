package questions.problemperday;

import java.util.Arrays;

/**
 * @program: leetcode
 * @Date: 2021-04-05 10:46
 * @Author: Lab
 * @Description:
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1, k=m+n-1;
        while(i>=0 && j >=0){
            if(nums1[i] > nums2[j]){
                nums1[k--] = nums1[i--];
            }else {
                nums1[k--] = nums2[j--];
            }
        }

        while(j>=0){
            nums1[k--] = nums2[j--];
        }

        Arrays.stream(nums1).forEach(System.out::println);
    }

    private void swap(int[] nums1, int[] nums2,int i,int j){
        int tmp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = tmp;
    }

    public static void main(String[] args) {
        MergeSortedArray test = new MergeSortedArray();

        test.merge(new int[]{0}, 0, new int[]{1}, 1);

    }
}
