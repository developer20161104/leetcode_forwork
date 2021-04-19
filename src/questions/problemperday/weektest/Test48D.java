package questions.problemperday.weektest;

import java.util.*;

/**
 * @program: leetcode
 * @Date: 2021-03-24 10:12
 * @Author: Lab
 * @Description:
 */
class AuthenticationManager {
    Map<String, Integer> map;
    int timeTolive;

    public AuthenticationManager(int timeToLive) {
        map = new HashMap<>(2000);
        timeTolive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime+timeTolive);
    }

    public void renew(String tokenId, int currentTime) {
        if(map.containsKey(tokenId) && map.get(tokenId)>currentTime){
            generate(tokenId, currentTime);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        for(Integer val: map.values()){
            if(val > currentTime ){
                count++;
            }
        }

        return count;
    }
}

public class Test48D {
    public int secondHighest(String s) {
        int[] arr=  new int[2];
        Arrays.fill(arr, -1);
        for(char ch: s.toCharArray()){
            if(ch-'0' >=0 && ch-'0'<=9){
                int num = ch-'0';
                if(num > arr[0]){
                    arr[1] = arr[0];
                    arr[0] = num;
                    // 需要考虑 第一位出现相同数字的情况，防止第二位被恶意侵占
                }else if(num != arr[0] &&num > arr[1]){
                    arr[1] = num;
                }
            }
        }

        return arr[0] == arr[1] ? -1:arr[1];
    }

    HashSet<Integer> set;
    int cur=0;
    public int getMaximumConsecutive(int[] coins) {
        set = new HashSet<>(10000);
        Arrays.sort(coins);
        for(int i=0;i<=coins.length;i++){
            dfs(coins, 0, 0, i);
        }

        while(set.contains(cur+1)){
            cur += 1;
        }

        return cur+1;
    }

    private void dfs(int[] counts, int curSum, int index, int k){
        if(curSum-1==cur){
            cur++;
        }else{
            set.add(curSum);
        }
        if(k == index){
            return;
        }

        for(int i=index;i<counts.length;i++){
            curSum += counts[i];
            dfs(counts, curSum, i+1, k);
            curSum -= counts[i];
        }

    }


    public int getMaximumConsecutiveSimple(int[] coins) {
        // 利用动态规划积累的思想
        Arrays.sort(coins);
        int num = 0;
        for(int val: coins){
            if(val > num+1)
                break;
            num += val;
        }

        return num+1;
    }

    public static void main(String[] args) {
        Test48D test = new Test48D();

        System.out.println(test.secondHighest("ab001"));

        System.out.println(test.getMaximumConsecutive(new int[]{
                1,3,4,1,10
        }));

        System.out.println(test.getMaximumConsecutiveSimple(new int[]{
                1,3,4,1,10
        }));
    }
}
