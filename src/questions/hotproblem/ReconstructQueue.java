package questions.hotproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (x,y)-> x[1]==y[1] ? (x[0]-y[0]):(x[1]-y[1]));

        int i;
        LinkedList<ArrayList<Integer>> sort = new LinkedList<>();
        for(i=0;i<people.length; i++){
            ArrayList<Integer> curSort = new ArrayList<>(Arrays.asList(people[i][0], people[i][1]));
            // 既要考虑大于部分，也要过滤小于部分
            int pos = 0,pad=0;
            while(people[i][1] != 0 && pos < people[i][1])
            {
                if(people[i][0] > sort.get(pos+pad).get(0))
                    pad++;
                else pos++;

            }
            while(pos+pad < sort.size() && people[i][0] > sort.get(pos+pad).get(0))
                pad++;

            if(pos==0) sort.add(curSort);
            else sort.add(pos+pad, curSort);
        }

        // 还得进行拆箱还原
        int[][] res = new int[people.length][2];
        i=0;
        for(ArrayList<Integer> list:sort){
            res[i][0] = list.get(0);
            res[i++][1] = list.get(1);
        }
        return res;
    }

    public int[][] reconstructQueuePro(int[][] people){
        // 逆序加正序，先把高个子放进去，再处理矮个子时逐个插入即可
        Arrays.sort(people, (x,y)-> x[0]==y[0] ? x[1]-y[1] : y[0]-x[0]);

        List<int[] > res = new LinkedList<>();

        for(int[] curPerson: people){
            res.add(curPerson[1], curPerson);
        }
        return res.toArray(people);
    }

    public static void main(String[] args) {
        ReconstructQueue test = new ReconstructQueue();

        int[][] res = test.reconstructQueue(new int[][]{
                {7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
        });

        for(int[] cur:res){
            System.out.println(cur[0] + " " + cur[1]);
        }
    }
}
