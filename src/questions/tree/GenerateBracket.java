package questions.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/13 21:10
 * @Author: Mr.Yang
 * @Description: 关键点：找到约束条件
 */
public class GenerateBracket {
    public List<String> generateParenthesis(int n) {
        List<String> arr = new ArrayList<>();

        if(n<1)
            return arr;

        dfs(arr, n,n, "");
        return arr;
    }

    // 深度优先遍历
    private void dfs(List<String> arr, int left, int right, String curStr){
        if(left==0 && right==0){
            arr.add(curStr);
            return;
        }

        // 剪枝条件没想到
        // 当左边剩余括号比右边少时，不满足题目要求
        if(left > right)
            return;

        if(left>0)
            dfs(arr, left-1, right, curStr+"(");

        if(right >0)
            dfs(arr, left, right-1, curStr+")");
    }

    public List<String> generateParenthesisDP(int n){
        // 动态规划版：类似于数学归纳法
        // 匿名内部类初始化待保存列表
        List<List<String>> tot = new ArrayList<List<String>>(){{
            add(new ArrayList<String>(){{
                add("");
            }});
            add(new ArrayList<String>(){{
                add("()");
            }});
        }};

        if(n==0)
            return tot.get(0);

        // tot[n] = "(" + p个括号的组合(tot[p]) + ")"  + q个括号的组合(tot[q])
        // 其中， n = p+q+1.
        for(int i=2; i<=n;i++){
            List<String> tmp = new ArrayList<>();

            for(int p=0;p<i;p++){
                List<String> curListP = tot.get(p);
                List<String> curListQ = tot.get(i-p-1);

                // 遍历并组合
                for(String str: curListP){
                    for(String  str1 : curListQ)
                        tmp.add("("+str+")"+str1);
                }
            }

            tot.add(tmp);

        }

        return tot.get(n);
    }

    public static void main(String[] args) {
        GenerateBracket test = new GenerateBracket();

//        List<String> arr = test.generateParenthesis(8);
        List<String> arr = test.generateParenthesisDP(0);

        arr.stream().forEach(System.out::println);
    }
}
