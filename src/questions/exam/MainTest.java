package questions.exam;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int p=0;p<T;p++){
            int n = in.nextInt();
            int count = 0;
            HashMap<Integer,Integer []> peoDig = new HashMap<>();
            PriorityQueue<Integer[]> maxQ = new PriorityQueue<>((a,b)-> b[1]-a[1]);
            PriorityQueue<Integer[]> minQ = new PriorityQueue<>((a,b)-> a[1]-b[1]);

            for(int i=0;i<n;i++){
                int curPeo = in.nextInt();
                int curDig = in.nextInt();

                if(!peoDig.containsKey(curPeo)){
                    peoDig.put(curPeo, new Integer[]{curDig, 0});
                    addE(new Integer[]{curPeo, curDig}, maxQ, minQ);
                    if(maxQ.size()+ minQ.size() == 1 || maxQ.peek()[0]==curPeo){
                        count = getCount(count, peoDig, maxQ, minQ, curPeo, curDig);
                    }
                }else if(peoDig.get(curPeo)[0] < curDig){
                    if(curDig > minQ.peek()[1]){
                        for(Integer[] val: maxQ){
                            if(val[0] == curPeo){
                                minQ.remove(val);
                                addE(new Integer[]{curPeo, curDig}, maxQ, minQ);
                                break;
                            }
                        }
                    }else {
                        for(Integer[] val:minQ){
                            if(val[0] == curPeo){
                                maxQ.remove(val);
                                addE(new Integer[]{curPeo, curDig}, maxQ, minQ);
                                break;
                            }
                        }
                    }
                    count = getCount(count, peoDig, maxQ, minQ, curPeo, curDig);

                }
            }
            System.out.println(count);

        }
    }

    private static int getCount(int count, HashMap<Integer, Integer[]> peoDig, PriorityQueue<Integer[]> maxQ, PriorityQueue<Integer[]> minQ, int curPeo, int curDig) {
        if(peoDig.get(curPeo)[1] == 0){
            if(maxQ.size() == minQ.size() && maxQ.peek()[1]+minQ.peek()[1] == 2*curDig
                    || (maxQ.size() < minQ.size() && minQ.peek()[1]*2 == 2*curDig)) {
                peoDig.get(curPeo)[1] = 0;
                count++;
            }
        }
        return count;
    }

    public static  void addE(Integer[] elem,PriorityQueue<Integer[]> maxQ, PriorityQueue<Integer[]> minQ){
        maxQ.add(elem);
        minQ.add(maxQ.poll());
        if(maxQ.size()+1<minQ.size()){
            maxQ.add(minQ.poll());
        }
    }

}
