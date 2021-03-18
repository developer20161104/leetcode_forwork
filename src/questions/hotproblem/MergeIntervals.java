package questions.hotproblem;

import java.util.Arrays;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/18 10:41
 * @Author: Mr.Yang
 * @Description:
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        int[][] res = new int[len][2];
        if(len < 1)
            return res;

//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] - o2[0];
//            }
//        });
        Arrays.sort(intervals, (x,y) -> x[0]-y[0]);

        int[] curInterval = intervals[0];
        int index = 0;
        for(int i=1;i<len;i++){
            if(curInterval[1] >= intervals[i][0])
                // 需要注意选出最大值
                curInterval[1] = Math.max(curInterval[1], intervals[i][1]);
            else
            {
                res[index++] = curInterval;
                curInterval = intervals[i];
            }
        }

        res[index++] = curInterval;
        return Arrays.copyOf(res, index);
    }

    public static void main(String[] args) {
        MergeIntervals test = new MergeIntervals();
        int[][] arr = new int[][]{{1,3},{2,6},{8,10},{9,18}};

        int[][] res = test.merge(arr);
        Arrays.stream(res).forEach(x-> Arrays.stream(x).forEach(System.out::print));
    }
}
