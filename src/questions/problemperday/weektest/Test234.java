package questions.problemperday.weektest;

import java.math.BigInteger;
import java.util.*;

/**
 * @program: leetcode
 * @Date: 2021-03-29 09:55
 * @Author: Lab
 * @Description:
 */
public class Test234 {
    public int numDifferentIntegers(String word) {
        int left = 0, right = 0;
        word += "a";
        HashSet<Integer> set = new HashSet<>();

        while(right < word.length()){
            char cur = word.charAt(right);
            if(!(cur-'0' >=0 && cur-'0'<=9)){
                int i=1, val = 0, exp = 1;
                while(right-i >=left){
                    val += (word.charAt(right-i)-'0')*(exp);
                    exp *= 10;
                    i++;
                }
                // 需要考虑0的影响
                if(left!=right)
                    set.add(val);
                left = right+1;
            }
            right++;
        }

        return set.size();
    }

    public int reinitializePermutation(int n) {
        int[] perm = new int[n];
        for(int i=0;i<n;i++){
            perm[i] = i;
        }

        int count = 1;
        perm = operation(perm, n);
        while(!judge(perm)){
            perm = operation(perm, n);
            count++;
        }
        return count;
    }

    private boolean judge(int[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=i)
                return false;
        }
        return true;
    }

    private static int[] operation(int[] perm, int n){
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            if(i%2  == 0){
                arr[i] = perm[i/2];
            }else
                arr[i] = perm[n/2+(i-1)/2];
        }

        return arr;
    }

    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String,String> map = new HashMap<>();
        for(List<String> curList:knowledge){
            map.put(curList.get(0), curList.get(1));
        }

        StringBuilder res = new StringBuilder();
        int left=0,right=0;
        boolean notIn = true;
        while(right < s.length()){
            char val = s.charAt(right);
            if(val-'a' >=0 && val-'a'<26 && notIn){
                res.append(val);
            }else if(val == '('){
                notIn = false;
                left = right+1;
            }else if(val == ')'){
                String subString = s.substring(left, right);
                if(map.containsKey(subString)){
                    res.append(map.get(subString));
                }else res.append("?");

                left = right+1;
                notIn = true;
            }
            right++;
        }

        return res.toString();
    }

    public int maxNiceDivisors(int primeFactors) {
        if(primeFactors < 3)
            return primeFactors;

        int count_3 = primeFactors/3;
        int mod = primeFactors-count_3*3;
        if(mod ==1){
            count_3--;
            mod+=3;
        }

        BigInteger res = new BigInteger(String.valueOf(1));
        BigInteger three = new BigInteger(String.valueOf(3));
        BigInteger times = new BigInteger(String.valueOf(count_3));
        BigInteger mods = new BigInteger(String.valueOf(1000000007));

        // 需要用快速幂
        res = res.multiply(three.modPow(times, mods));


        if(mod == 0)
            return Integer.parseInt(res.toString());
        else
            return Integer.parseInt(res.multiply(new BigInteger(String.valueOf(2))).mod(mods).toString());
    }

    public static void main(String[] args) {
        Test234 test = new Test234();

        System.out.println(test.numDifferentIntegers("a123bc34d8ef34b0b0001a01c1"));

        System.out.println(test.reinitializePermutation(1000));

        List<List<String>> arr= new ArrayList<>();
        arr.add(new ArrayList<>(Arrays.asList("name","bob")));
        arr.add(new ArrayList<>(Arrays.asList("age","two")));

        System.out.println(test.evaluate("(name)is(age)yearsold", arr));

        System.out.println(test.maxNiceDivisors(1000));
    }
}
