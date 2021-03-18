package questions.hotproblem.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/8 10:12
 * @Author: Mr.Yang
 * @Description:
 */
public class NumberCombination {
    public Map<String, String> map = Arrays.stream(new String[][]{
            {"2", "abc"},
            {"3", "def"},
            {"4", "ghi"},
            {"5", "jkl"},
            {"6", "mno"},
            {"7", "pqrs"},
            {"8", "tuv"},
            {"9", "wxyz"},
    }).collect(Collectors.toMap(kv -> kv[0], kv->kv[1]));


    public List<String> letterCombinations(String digits) {
        List<String> arr = new LinkedList<>();
        if(digits.length() < 1)
            return arr;

        arr.addAll(Arrays.asList(map.get(Character.toString(digits.charAt(0))).split("")));

        for(int i=1;i<digits.length();i++){
            String[] curStr = map.get(Character.toString(digits.charAt(i))).split("");

            // 队列操作
            int curLen = arr.size();
            for(int j=0;j<curLen;j++){
                String curval = arr.remove(0);

                for(String str: curStr)
                    arr.add(curval+str);
            }
        }

        return arr;
    }

    public List<String> res = new ArrayList<>();

    public List<String> letterCombinationsRec(String digits){
        if(digits.equals(""))
            return res;

        findCombination(digits, 0, "");
        return res;
    }

    // 全体遍历也可用递归
    private void findCombination(String digits, int index, String s){
        if(index == digits.length()){
            res.add(s);
            return;
        }

        Character c = digits.charAt(index);
        String letters = map.get(c.toString());
        // index负责外层遍历，i负责内部遍历
        for(int i=0;i<letters.length();i++)
            findCombination(digits, index+1, s+letters.charAt(i));
    }


    public static void main(String[] args) {
        NumberCombination test = new NumberCombination();

        List<String> arr = test.letterCombinationsRec("23");
        arr.stream().forEach(System.out::println);
    }
}
