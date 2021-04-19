package questions.problemperday.weektest;

import java.util.PriorityQueue;

/**
 * @program: leetcode
 * @Date: 2021-03-22 09:07
 * @Author: Lab
 * @Description:
 */
public class Test233 {
    public int maxAscendingSum(int[] nums) {
        int len = nums.length;
        if(len < 1)
            return 0;

        int curMax = 0, pre = 0, max=0;
        for(int num:nums){
            if(num > pre){
                pre = num;
                curMax += num;
            }else {
                max = Math.max(max, curMax);
                curMax = num;
                pre = num;
            }
        }

        return Math.max(max, curMax);
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> BuyQ = new PriorityQueue<>((o1, o2) -> (int) (o2[0] - o1[0])); //<价格, 数目>，大顶堆
        PriorityQueue<int[]> SellQ = new PriorityQueue<>((o1, o2) -> (int) (o1[0] - o2[0]));  //<价格, 数目>，小顶堆
        for (int[] order : orders) {
            if (order[2] == 0) {   //当前订单是Buy，需要找最小的Sell
                while (order[1] > 0 && !SellQ.isEmpty() && SellQ.peek()[0] <= order[0]) {  //当前订单数目大于0，Sell最低价<= 当前订单价格
                    if (order[1] > SellQ.peek()[1]) {  //当前订单数 > Sell最低价的订单数
                        order[1] -= SellQ.poll()[1];  //Sell中最低价被删除
                    } else if (order[1] == SellQ.peek()[1]) {  //当前订单数 == Sell最低价的订单数
                        SellQ.poll();  //Sell中最低价被删除
                        order[1] = 0;
                    } else if (order[1] < SellQ.peek()[1]) { //当前订单数 < Sell最低价的订单数
                        int[] minSell = SellQ.poll(); //更新Sell最低价的订单数量
                        SellQ.add(new int[] {minSell[0], minSell[1] - order[1]});
                        order[1] = 0;
                    }
                }
                if (order[1] > 0) BuyQ.add(new int[] {order[0], order[1]});//当前订单数还有余留，则压入队列中
            } else {  //当前订单是Sell，需要找最大的Buy
                while (order[1] > 0 && !BuyQ.isEmpty() && BuyQ.peek()[0] >= order[0]) {  //当前订单数目大于0，Buy最高价>= 当前订单价格
                    if (order[1] > BuyQ.peek()[1]) {  //当前订单数 > Buy最高价的订单数
                        order[1] -= BuyQ.poll()[1];  //Buy最高价被删除
                    } else if (order[1] == BuyQ.peek()[1]) {  //当前订单数 == Buy最高价的订单数
                        BuyQ.poll();  //Buy最高价被删除
                        order[1] = 0;
                    } else if (order[1] < BuyQ.peek()[1]) { //当前订单数 < Buy最高价的订单数
                        int[] maxBuy = BuyQ.poll(); //更新Buy最高价的订单数量
                        BuyQ.add(new int[] {maxBuy[0], maxBuy[1] - order[1]});
                        order[1] = 0;
                    }
                }
                if (order[1] > 0) SellQ.add(new int[] {order[0], order[1]});//当前订单数还有余留，则压入队列中
            }
        }
        int res = 0;
        while (!BuyQ.isEmpty()) {
            res = (res + BuyQ.poll()[1]) % 1000000007;
        }
        while (!SellQ.isEmpty()) {
            res = (res + SellQ.poll()[1]) % 1000000007;
        }
        return res;
    }

    public int maxValue(int n, int index, int maxSum) {
        int st = (int) Math.sqrt(maxSum);
        int res = sumSide(index+1, st)+sumSide(n-index, st)-st;
        while(res <= maxSum){
            st++;
            res = sumSide(index+1, st)+sumSide(n-index, st)-st;
        }

        return st-1;
    }

    private int sumSide(int index, int x){
        int res;
        if(x <= index){
            res = x*(x+1)/2+index-x;
        }else{
            res = x*(x+1)/2-(x-index)*(x-index+1)/2;
        }

        return index!=0? res:0;
    }

    public static void main(String[] args) {
        Test233 test = new Test233();

//        System.out.println(test.getNumberOfBacklogOrders(new int[][]{
////                {7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}
//                {10,5,0},{15,2,1},{25,1,1},{30,4,0}
//        }));

        System.out.println(test.maxValue(9,1,9));
        System.out.println(test.maxValue(3,2,18));
    }
}
