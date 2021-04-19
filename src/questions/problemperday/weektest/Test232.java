package questions.problemperday.weektest;

/**
 * @program: leetcode
 * @Date: 2021-03-26 19:36
 * @Author: Lab
 * @Description:
 */
public class Test232 {
    public boolean areAlmostEqual(String s1, String s2) {
        int len_1 = s1.length();
        int i=0, index=-1;
        boolean flag = true;
        while(i<len_1){
            if(s1.charAt(i) != s2.charAt(i)){
                if(index > -1){
                   if(flag && s1.charAt(index) == s2.charAt(i) && s1.charAt(i) == s2.charAt(index)){
                       flag = false;
                   }
                   else return false;
                }
                index = i;
            }
            i++;
        }

        return index < 0 || !flag;
    }

    public int findCenter(int[][] edges) {
        int len = edges.length;
        int[] degree = new int[len+1];

        for(int[] arr: edges){
            degree[arr[0]-1]++;
            degree[arr[1]-1]++;
        }

        int max=0;
        for(int i=1;i<len+1;i++){
            if(degree[max] < degree[i]){
                max=  i;
            }
        }

        return max+1;

    }

    public int maximumScore(int[] nums, int k) {
        int len = nums.length;
        if(len < 1)
            return 0;

        int i=k-1,j=k+1;
        int val = nums[k], min=nums[k], max = 0;
        if(val == 1)
            return len;

        while(i>=0 || j <len){
            int minleft=-1,minright=-1;
            if(i>=0)
                minleft = Math.min(min, nums[i]);
            if(j<len)
                minright = Math.min(min, nums[j]);

            if(minleft > minright){
                val = Math.max(val, minleft*(j-i));
                i--;
            }else if(minleft < minright){
                val = Math.max(val, minright*(j-i));
                j++;
            }else {
                return nums[i]*len;
            }
            max = Math.max(val, max);
        }

        return max;
    }


    public static void main(String[] args) {
        Test232 test = new Test232();

        System.out.println(test.areAlmostEqual("ac", "vc"));

        System.out.println(test.maximumScore(new int[]{1,4,3,7,4,5}, 3));
        Thread.yield();
    }
}
