package questions.hotproblem.backtracking;

/**
 * @program: leetcode
 * @Date: 2021-04-07 11:02
 * @Author: Lab
 * @Description:
 */
public class RobotTravel {
    int[][] direct = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    int[][] arr;
    int res;

    public int movingCount(int m, int n, int k) {
        arr= new int[m][n];
        count(m,n,0,0,k);

        return res;
    }

    private void count(int m, int n, int curM, int curN, int k){
        if(!judge(curM, curN,k) || arr[curM][curN]==1){
            return;
        }

        arr[curM][curN] = 1;
        res += 1;
        for(int i=0;i<4;i++){
            int newM = curM+direct[i][0];
            int newN = curN+direct[i][1];
            if(newM >=0 && newM < m && newN>=0 && newN < n){
                count(m,n,newM, newN,k);
            }
        }
    }

    private boolean judge(int curM, int curN, int k){
        int val = 0;
        while(curM != 0){
            val += curM %10;
            curM /= 10;
        }
        while(curN != 0){
            val += curN%10;
            curN /= 10;
        }

        return val <= k;
    }

    public static void main(String[] args) {
        RobotTravel test = new RobotTravel();

        System.out.println(test.movingCount(2,3,2));
    }
}
