package questions.nowcoder;

/**
 * @program: leetcode
 * @Date: 2021-03-21 22:13
 * @Author: Lab
 * @Description:
 */
import java.util.ArrayList;
import java.util.Scanner;

public class ChooseNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNextInt()){
            int[] count = new int[13];
            for(int i=0;i<13;i++){
                count[in.nextInt()-1]++;
            }


        }
    }

    private void dfs(int[] arr, int count, ArrayList<Integer> res){
        if(count == 1){
            for(int i=0;i<9;i++)
                if(arr[i] == 1) {
                    res.add(i+1);
                    break;
                }

            return;
        }
    }

}
