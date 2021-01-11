package questions;
import java.lang.reflect.Array;
import java.util.*;

public class ValidBrackets {
    private static final String[] SET_VALUE = new String[]{"{","[","(","{}","[]","()"};
    private static final HashSet<String> judgeString = new HashSet<>(Arrays.asList(SET_VALUE));

    public boolean isValid(String s) {
        // 最初想法，采用栈来实现
        // 时间复杂度为O(n),空间复杂度不为O(1)，需要看s中包含的最长左括号的数量
        Stack<String> stack = new Stack<>();

        for(int i=0;i<s.length();++i){
            String curString = String.valueOf(s.charAt(i));
            if(judgeString.contains(curString)) stack.push(curString);
            else if(!stack.isEmpty()){
                curString = stack.pop() + curString;
                if(!judgeString.contains(curString))
                    return false;
                // 错误1：没有考虑只含有右边括号的情况
            }else return false;
        }

        return stack.isEmpty();
    }


    public boolean isValid2(String s){
        // 写法有点低效，没必要转化为字符串，也没必要使用一个hash表存储符合类型，直接遍历即可
        Stack<Character> stack = new Stack<>();

        for(char character: s.toCharArray()){
            // 逆向思路不错，既然不同，则选择保存相同的即可
            if(character == '{') stack.push('}');
            else if(character == '[') stack.push(']');
            else if(character == '(') stack.push(')');
            // 如果出现右括号就好进行判断了
            else if(stack.isEmpty() || character != stack.pop()) return false;
        }

        return stack.isEmpty();
    }

    // 其实还可以考虑使用字典来保存左右括号的关系，然后进行栈判断即可
    public static void main(String[] args) {
        ValidBrackets test = new ValidBrackets();
        System.out.println(test.isValid("{"));
    }
}
