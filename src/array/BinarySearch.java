package array;
import javax.crypto.spec.PSource;

public class BinarySearch {
    public int RecursiveBS(int[] nums, int target){
        return RBS(nums, 0, nums.length-1, target);
    }

    private int RBS(int[] nums, int left, int right, int target){
        if(left > right) return left;
        int mid = left+(right-left)/2;
        if(nums[mid] < target) return RBS(nums, mid+1, right, target);
        else return RBS(nums, left, mid-1, target);
    }

    public int IterativeBS(int[] nums, int target){
        // 应用的是查找的思想
        int left=0, right=nums.length-1;
        // 最后会得到一个空区间
        while(left<=right){
            int mid = left + (right-left) / 2;
            // 右查找只需要将判断条件增加等号判断，可以表示越过左侧相等元素
            if(nums[mid] < target){
                left = mid+1;
            }
            else right = mid-1;
        }

        return left;
    }

    public int IterativeBSExclude(int[] nums, int target){
        int left=0, right=nums.length-1;

        // 对于出现在最后位置的情况，该算法没有考虑进去，因此需要单独判断或者将右指针包括该位置
        if(nums[right] < target) return right+1;

        // 最后会得到一个单一的值
        while(left<right){
            int mid = left+(right-left)/2;
            if(nums[mid] < target){
                left = mid+1;
            }
            // 否则还是有可能存在于右侧中，不能直接进行剔除
            else right = mid;
        }

        return left;
    }

    // 34 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        // 使用相同函数来构造的方法，需要区分左右查找的区别
        // return new int[]{SearchLeftRight(nums,target,0), SearchLeftRight(nums, target, 1)};

        // 方式二：分别进行设计
        int left=0, right= nums.length-1, mid;
        int[] res = new int[]{-1, -1};
        if(right<0) return res;

        // 该方法为逐一查询，最后为空区间，因此需要将right扣除到比left小
        while(left<=right){
            mid = left+(right-left)/2;
            if(nums[mid] < target){
                left = mid+1;
            }else right = mid-1;
        }

        if(nums[left] != target) return res;
        res[0] = left;

        left=0;
        right=nums.length-1;

        while(left<right){
            mid = left+(right-left) / 2;
            if(nums[mid] <= target){
                left = mid+1;
            }else right = mid;
        }

        if(nums[left] == target)
            res[1] = left;
        else
            res[1] = left-1;
        return res;
    }

    private int SearchLeftRight(int[] nums, int target, int flag){
        int left=0, right=nums.length-1;

        if(right < 0) return -1;
        while(left<right){
            int mid = left+(right-left) / 2;
            if(flag == 0){
                if(nums[mid] < target){
                    left = mid+1;
                }else right = mid;
            }else {
                if(nums[mid] <= target){
                    left = mid+1;
                }else right = mid;
            }
        }

        // 对于左查找，得到的是第一次出现的位置
        // 对于右查找，则得到的是最后一次出现的位置或者是严格大于该值的第一个位置，因此需要特别判断
        int pos = left;
        // 右查找的特殊判断
        if(flag==1 && left>0 && nums[left] != target) pos = left-1;
        if(nums[pos] == target)
            return pos;
        return -1;
    }


    // 33 搜索旋转排序数组
    public int search(int[] nums, int target) {
        // 该类型不适合采用排除法，因为有可能不存在，需要额外的判断
        int left=0, right=nums.length-1;
        while(left <= right){
            int mid = left + (right-left) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] >= nums[left]){
                if(nums[mid] > target && nums[left] <= target){
                    right = mid-1;
                }else left = mid+1;
            }else {
                if(nums[mid] < target && nums[right] >= target){
                    left = mid+1;
                }else right=mid-1;
            }
        }

        return -1;
    }

    // 搜素含重复数字的旋转排序数组
    public boolean search_repeat(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        if(right<0) return false;

        while(left <= right){
            int mid = left + (right-left) / 2;

            if(nums[mid] == target) return true;
            // 由题意，可知待查询结果不一定存在于数组中，因此采用查找法比较合适
            if(nums[mid] > nums[left]){
                // 左有序
                if(target >= nums[left] && target < nums[mid]){
                    right = mid-1;
                }else left = mid+1;
            }else if(nums[mid] < nums[left]){
                // 右有序
                if(target <= nums[right] && target > nums[mid]){
                    left = mid+1;
                // 两边相等时退化为逐步查询
                }else right = mid-1;
            }else left++;
        }
        return false;
    }


    // 寻找旋转排序数组的最小值
    public int findMin(int[] nums) {

//        int left=0, right = nums.length-1;
//        if(right<0) return -1;
//        while(left <= right){
//            int mid = left+(right-left)/2;
//
//            // 出现中间位时的必要判断
//            if(mid >0 && nums[mid]<nums[mid-1])
//                return nums[mid];
//            if(nums[mid] < nums[right]){
//                right = mid-1;
//            }else left = mid+1;
//        }
//
//        // 设置判断条件，当位置不在初始位上时，找到的位置位于下一位
//        if(left>0) return nums[left-1];
//        return nums[left];

        // 使用排除法的更简洁版本
        int left=0, right=nums.length-1;
        if(right<0) return -1;
        while(left < right){
            int mid = left+(right-left)/2;
            if(nums[mid] < nums[right]){
                // 核心点：当前的位置也可能为最终结果，也是此处没有想好
                right=mid;
            }else left=mid+1;
        }

        return nums[left];
    }


    // 寻找含重复数字的旋转排序数组中的最小值
    public int findMinRt(int[] nums) {
        int left=0, right=nums.length-1;
        if(right<0) return -1;
        while(left < right){
            int mid = left+(right-left)/2;
            if(nums[mid] < nums[right]){
                right=mid;
            }else if(nums[mid] > nums[right])left=mid+1;
            // 由于使用的是右边判断，因此出现相同情况时应该优先缩小右区间
            // 错误案例：1,3,3
//            else ++left;
            else --right;
        }

        return nums[left];
    }


    // 山脉数组的峰顶索引
    public int peakIndexInMountainArray(int[] arr) {
        // 由题意，一定会存在一个满足该条件的索引，因此选择排除法
        int left =0, right = arr.length-1;
        // 甚至能够省略判断条件，右边会收缩到最终位
//        if(arr[0]>arr[1]) return 0;
        while(left<right){
            // 因为先对右边进行收缩，因此采用向上取整
            int mid = left + (right-left+1) / 2;
            if(mid>0 && arr[mid-1]>arr[mid]){
                right = mid-1;
            }else left = mid;
        }

        return left;
    }

    // 山脉数组中查找目标值
    public int findInMountainArray(int target, int[] mountainArr) {
        int left=0 ,right = mountainArr.length-1, mid= 0;
        while(left < right){
            mid = left + (right-left+1) /2;
            if(mid>0 && mountainArr[mid-1]>mountainArr[mid]){
                right = mid-1;
            }else left= mid;
        }

        int peak_index = left, pos=-1;
        if(mountainArr[peak_index] < target) return pos;

        left = 0;
        right = peak_index;
        while(left<= right){
            mid = left+ (right-left)/2;
            if(mountainArr[mid] < target){
                left = mid+1;
            }else right = mid-1;
        }

        if(mountainArr[left] == target) return left;

        left = peak_index+1;
        right = mountainArr.length-1;
        while(left <= right){
            mid = left + (right-left)/2;
            if(mountainArr[mid] < target){
                right = mid-1;
            }else left = mid+1;
        }

        if(mountainArr[left-1] == target) return left-1;
        return pos;

    }


    // 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 朴素法，先合并再利用中位数性质求取
        // 时间复杂度为O(n+m)，空间复杂度为O(1)
//        int len_1 = nums1.length, len_2 = nums2.length;
//        int center = (len_1+len_2) / 2, even = 0;
//        if((len_1+len_2)%2 == 0) even = 1;
//        int[] res = new int[]{0,0};
//
//        int i_1 = 0, i_2 = 0, count=0;
//        while(i_1<len_1 || i_2<len_2){
//            int cur_val_1 = 1000000+1, cur_val_2 = 1000000+1;
//            if(i_1 < len_1) cur_val_1 = nums1[i_1];
//            if(i_2 < len_2) cur_val_2 = nums2[i_2];
//
//            if(cur_val_1 < cur_val_2){
//                i_1 ++;
//            }else i_2++;
//
//            if(count == center-1) res[0] = Math.min(cur_val_1, cur_val_2);
//            if(count == center){
//                res[1] = Math.min(cur_val_1, cur_val_2);
//                break;
//            }
//
//            count++;
//        }
//
//        if(even == 0) return res[1];
//        return (double)(res[0]+res[1])/2;

        // 可优化部分：奇偶合并，取消尾部判断，直接换成条件赋值
        int len_1 = nums1.length, len_2 = nums2.length;
        int i_1=0, i_2 = 0;
        int len = len_1+len_2, pre_value=0, cur_value=0;

        for(int j=0;j<=len/2;++j){
            pre_value = cur_value;
            // 当前一个数组还存在元素，并且比后一个数组元素小或者后一个数组没元素时，从前一个数组中选取
            if(i_1<len_1 && (i_2>=len_2 || nums1[i_1]<nums2[i_2])){
                cur_value = nums1[i_1++];
            }else cur_value = nums2[i_2++];

        }

        if(len%2==1) return cur_value;
        return (double)(cur_value+pre_value)/2;
    }

    public double findMedianSortedArrays_Recursive(int[] nums1, int[] nums2){
        int len_1 = nums1.length, len_2 = nums2.length;
        // 由于k是从1开始的，因此需要寻找的是上界与下一个值
        int pre = (len_1+len_2+1) / 2, cur=(len_1+len_2+2) / 2;
        return (getKthValue(nums1, 0, len_1-1, nums2, 0, len_2-1, pre)+getKthValue(nums1,0,len_1-1, nums2,0, len_2-1, cur))*0.5;
    }

    // 寻找第k个数
    private int getKthValue(int[] nums1, int st_1, int ed_1, int[] nums2, int st_2, int ed_2, int k){
        int len1 = ed_1-st_1+1;
        int len2 = ed_2-st_2+1;

        // 始终将短数组作为 nums1，便于后续的判断
        if(len1 > len2) return getKthValue(nums2, st_2, ed_2, nums1, st_1, ed_1, k);
        // 当第一个数组中已经被排除完后，答案必定在另一个数组中，直接返回即可
        if(len1 == 0) return nums2[st_2+k-1];

        // 当只剩下最后一个时，选择其中的小值作为最终答案
        if(k == 1) return Math.min(nums1[st_1], nums2[st_2]);

        // 注意截取的长度不能超过当前数组的最大长度
        int i = st_1+ Math.min(k/2, len1)-1;
        int j = st_2+ Math.min(k/2, len2)-1;

        // 删除两者中的小值，注意此处删除的值在最终合并数组中不一定都是连续的，但是必定是在第k小数的左边
        if(nums1[i] < nums2[j]){
            return getKthValue(nums1, i+1, ed_1, nums2, st_2, ed_2, k-(i-st_1+1));
        }else
            return getKthValue(nums1, st_1, ed_1, nums2, j+1, ed_2, k-(j-st_2+1));
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,3,3,4,5,6};

        BinarySearch test = new BinarySearch();
        // 以上皆为左二分，即最终返回的是最左侧的合适位置
//        System.out.println(test.RecursiveBS(nums, 2));
//        System.out.println(test.IterativeBS(nums, 2));
//        System.out.println(test.IterativeBSExclude(new int[]{1,1,2}, 1));

        // 依据题目的要求进行右指针范围的选择，如果需要查找到尾部之后的位置，则使用排除法时需要增加右指针的判断位置

        int[] res = test.searchRange(new int[]{1,1,2}, 1);
        for (int re : res) System.out.println(re);

        int[] nums_33 = new int[]{3,4,0,1,2};
//        System.out.println(test.search(nums_33, 0));

//        System.out.println(test.findMin(nums_33));

        int[] num_34 = new int[]{0,2,2,2};
        System.out.println(test.findMinRt(num_34));

//        System.out.println(test.search_repeat(new int[]{1,1,1,0,1}, 0));

//        System.out.println(test.peakIndexInMountainArray(new int[]{2,1,0}));

//        System.out.println(test.findInMountainArray(2, new int[]{1,5,2}));

//        System.out.println(test.findMedianSortedArrays(new int[]{1,2,3}, new int[]{2,3,4}));
//        System.out.println(test.findMedianSortedArrays_Recursive(new int[]{1,2,3}, new int[]{2,3,4}));



    }
}
