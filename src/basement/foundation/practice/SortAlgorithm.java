package basement.foundation.practice;

import java.util.Arrays;

/**
 * @program: leetcode
 * @Date: 2021-06-02 21:35
 * @Author: Lab
 * @Description:
 */
public class SortAlgorithm {
    public int[] sortByQuick(int[] arr){
        Quicksort(arr, 0, arr.length-1);
        return arr;
    }

    public void Quicksort(int[] arr,int left, int right){
        if(left <= right){
            int mid = left + (right-left)/2;

            int partition = getPartition(arr, left, right);
            Quicksort(arr, left, partition-1);
            Quicksort(arr, partition+1, right);
        }
    }

    public int getPartition(int[] arr, int left, int right){
        int tmp = arr[left];

        while(left < right){
            while(right > left && arr[right] >= tmp){
                right--;
            }
            arr[left] = arr[right];

            while(right > left && arr[left] <= tmp){
                left++;
            }
            arr[right] = arr[left];

        }

        arr[left] = tmp;
        return left;
    }

    public int[] merge(int[] arr){
        if(arr.length < 2){
            return arr;
        }

        int mid = arr.length/2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return mergeSorts(merge(left), merge(right));
    }

    public int[] mergeSorts(int[] left, int[] right){
        int[] arr = new int[left.length+right.length];

        int l=0,r=0, pos=0;
        while(l < left.length && r < right.length){
            if(left[l] <= right[r]){
                arr[pos++] = left[l++];
            }else {
                arr[pos++] = right[r++];
            }
        }

        while(l < left.length){
            arr[pos++] = left[l++];
        }

        while(r < right.length){
            arr[pos++] = right[r++];
        }

        return arr;
    }

    public static void main(String[] args) {
        SortAlgorithm test = new SortAlgorithm();

//        System.out.println(Arrays.toString(test.sortByQuick(new int[]{4,1,2,5,6})));
        System.out.println(Arrays.toString(test.merge(new int[]{4,1,2,2,2,5,6})));
    }
}
