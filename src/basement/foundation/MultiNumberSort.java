package basement.foundation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Stack;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/8 15:31
 * @Author: Mr.Yang
 * @Description:
 */
public class MultiNumberSort {
    /*
    *
    *   归并排序
    *   时间复杂度为 O(nlogn)，空间复杂度为O(nlogn)
    *
    * */

    // 内部嵌套形式的递归，写起来有点麻烦
    public int[] mergeSort(int[] arr){
        if(arr.length < 2)
            return arr;

        int[] res = new int[arr.length];
        merge(arr, res, 0, arr.length-1);

        return res;
    }

    private void merge(int[] arr,int[] res, int left, int right){
        if(left >= right)
            return;

        int mid = left+(right-left)/2;
        int t=left;
        int st1 = left, st2 = mid+1, ed1=mid, ed2=right;

        // 此处归并的递归形式
        merge(arr, res,st1, ed1);
        merge(arr, res,st2, ed2);

        while(st1<=ed1 && st2<=ed2){
            if(arr[st1] < arr[st2])
                res[t++] = arr[st1++];
            else
                res[t++] = arr[st2++];
        }

        while(st1 <=ed1)
            res[t++] = arr[st1++];

        while(st2 <= ed2)
            res[t++] = arr[st2++];

        // 需要注意下标问题
        for(t=left;t<=right;t++)
            arr[t] = res[t];
    }

    public int[] sortbyMerge(int[] arr){
        if(arr.length<2)
            return arr;

        int mid = arr.length / 2;

        // 使用外部递归更容易理解
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr,mid, arr.length);

        return mergeOps(sortbyMerge(left), sortbyMerge(right));
    }

    private int[] mergeOps(int[] left, int[] right){
        int[] result = new int[left.length+ right.length];
        int i=0;

        // 同时选择
        while(left.length >0 && right.length>0){
            if(left[0] < right[0]){
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            }else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }

        }

        // 部分选择
        while(left.length >0){
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while(right.length>0){
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    /*
     *
     *   快速排序
     *   时间复杂度为 O(nlogn)，空间复杂度为O(1)
     *
     * */

    public int[] sortByQuick(int[] arr){
        if(arr.length < 2)
            return arr;
        return quickSort(arr, 0, arr.length-1);
//        return quickSortIter(arr, 0, arr.length-1);
    }

    private int[] quickSort(int[] arr, int left, int right){
        if(left < right){
            int partition = getPartitionRaw(arr,left, right);
            // partition位置已经为有序，因此只要排列其它位置
            // 双闭区间
            quickSort(arr, left, partition-1);
            quickSort(arr, partition+1, right);
        }
        return arr;
    }

    private int[] quickSortIter(int[] arr, int left, int right){
        int[] stack = new int[right-left+1];
        int top = -1;

        stack[++top] = left;
        stack[++top] = right;

        // 迭代版本实质为模仿系统栈
        while(top >= 0) {
            right = stack[top--];
            left = stack[top--];

            // 由该函数进行数组内部的移动
            int partition = getPartition(arr, left, right);

            // 左边可移动
            if (left < partition - 1) {
                stack[++top] = left;
                stack[++top] = partition-1;
            }

            // 右边可移动
            if(right > partition+1){
                stack[++top] = partition+1;
                stack[++top] = right;
            }
        }

        return arr;
    }


    private int getPartition(int[] arr, int left, int right){
        int pivot = left, index=pivot+1;
        // 类似于快慢指针
        // index保存的为当前比pivot位置小的元素的下一位坐标
        for(int i=index;i<=right;i++){
            // 该循环将所有比pivot位置小的元素都放左边
            if(arr[i]<arr[pivot]){
                swap(arr, i, index);
                index++;
            }
        }

        // 最后交换pivot位置与最后一个比其小位置的元素即可
        swap(arr, pivot, index-1);
        return index-1;
    }

    private int getPartitionRaw(int[] arr, int left, int right){
        int pivot = arr[left];

        // 原始方法，需要对每个位置进行移动
        while(left < right){
            while(left < right && arr[right] > pivot)
                right--;
            arr[left] = arr[right];

            while(left < right && arr[left] <= pivot)
                left++;
            arr[right] = arr[left];
        }

        // 最后一个填充
        arr[left] = pivot;

        return left;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     *
     *   堆排序
     *   时间复杂度为 O(nlogn)，空间复杂度为O(1)
     *
     * */
    public int[] sortbyHeap(int[] arr){
        // 从第一个非叶子节点开始调整
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr, i, arr.length);
        }

        // 调整堆的结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr, 0, j);
            adjustHeap(arr, 0, j);
        }

        return arr;
    }

    private  void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        for(int k=i*2+1;k<length;k=k*2+1){
            // 左右节点的选择
            if(k+1 < length && arr[k]<arr[k+1])
                k++;

            // 如果比父节点大，则进行值交换
            // 逐一此处需要遍历所有的子节点
            // 修复因swap导致的非叶子节点小于子节点的情况（使子节点依然满足堆的性质）
            if(arr[k] > temp){
                arr[i] = arr[k];
                i =k;
            }else break;
        }

        // 最后只进行一次交换
        // 类似于快速排序的partition值
        arr[i] = temp;
    }

    public static void main(String[] args) {
        MultiNumberSort test = new MultiNumberSort();
        int[] arr = new int[]{4,6,8,5,5,5,4,9};

//        arr = test.sortbyMerge(arr);
        arr = test.sortByQuick(arr);
//        arr = test.sortbyHeap(arr);
        Arrays.stream(arr).forEach(System.out::println);

        BigDecimal a = new BigDecimal(String.valueOf(10));
    }
}
