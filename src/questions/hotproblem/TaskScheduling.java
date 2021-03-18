package questions.hotproblem;

import java.util.Arrays;

public class TaskScheduling {
    public int leastInterval(char[] tasks, int n) {
        // 基本思想：统计出现最多的次数，剩余的数量则进行填充，多出来的部分直接计数
        int[] count = new int[26];
        for(char task: tasks)
            count[task-'A'] ++;

        Arrays.sort(count);
        // 同时，需要考虑的与最大数量相等时的情况，此时必有一个超出范围，需要单独计数
        int reside = 0, same=0;
        for(int i=24;i>=0;i--){
            reside += count[i];
            if(count[i] == count[25]) {
                same++;
                reside--;
            }
        }

        int tmp = (count[25]-1)*n;
        return Math.max(reside-tmp,0)+tmp+count[25]+same;
    }

    public static void main(String[] args) {
        TaskScheduling test = new TaskScheduling();

        System.out.println(test.leastInterval(new char[]{
                'A','A','A','B','B','B'    
        }, 2));

        String s = "afaw";
    }
}
