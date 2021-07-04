package questions.exam;


import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String res = "adwarfar";
        res.matches("ad*");

        while(in.hasNextInt()){
            int n = in.nextInt(), k = in.nextInt();
            int[] arr = new int[n];
            ArrayList<Integer> zeroPos = new ArrayList<>();
            ArrayList<Integer> suitPos = new ArrayList<>();

            for(int i=0;i<n;i++){
                arr[i] = in.nextInt();
                if(arr[i] == 0){
                    zeroPos.add(i);
                }else if(arr[i] <= k){
                    suitPos.add(i);
                }
            }

            int[] prefix = new int[zeroPos.size()+1];
            for(int i=1;i<zeroPos.size()+1;i++){
                prefix[i] = prefix[i-1]+zeroPos.get(i-1);
            }

            int minDis =Integer.MAX_VALUE, minPos = -1;
            for(int i=0;i<suitPos.size();i++){
                int curSuit = suitPos.get(i);
                int pos = BinarySearch(zeroPos, curSuit);
                int rightResides = zeroPos.size()-pos;
                int curDis = curSuit*pos-prefix[pos]+ prefix[zeroPos.size()]-prefix[pos]-rightResides*curSuit;
                if(minDis > curDis){
                    minDis = curDis;
                    minPos = curSuit;
                }
            }

            System.out.println(minPos+1);
        }


    }

    private static int BinarySearch(ArrayList<Integer> arrN, int value){
        int left=0, right=arrN.size()-1;
        while(left<=right){
            int mid = left + (right-left) / 2;
            // 右查找只需要将判断条件增加等号判断，可以表示越过左侧相等元素
            if(arrN.get(mid) < value){
                left = mid+1;
            }
            else right = mid-1;
        }

        return left;
    }
}
