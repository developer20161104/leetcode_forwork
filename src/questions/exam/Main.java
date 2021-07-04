package questions.exam;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNextLine()){
            String[] val = in.nextLine().split(" ");
            int n = Integer.parseInt(val[0]);
            int m = Integer.parseInt(val[1]);
            int k = Integer.parseInt(val[2]);

            int[][] arr = new int[n][2];
            for(int i=0;i<n;i++){
                String[] val1 = in.nextLine().split(" ");
                arr[i][0] = Integer.parseInt(val1[0]);
                arr[i][1] = Integer.parseInt(val1[1]);
            }


            int[][] dp = new int[n+1][k+1];
            for(int i=1;i<=n;i++){
                for(int j=1;j<=k;j++){
                    if(arr[i-1][0] <= j*m){
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+arr[i-1][1]);
                    }else{
                        // 当出现距离不能一步到达时，前k步固定
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }

            System.out.println(dp[n][k]+arr[0][1]);

        }


    }


}
