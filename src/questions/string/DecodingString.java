package questions.string;

import java.util.LinkedList;
import java.util.Stack;

public class DecodingString {
    public String decodeString(String s) {
        int len = s.length();
        if(len < 1)
            return "";

        StringBuilder res = new StringBuilder();
        int multi=0;

        // 保存数字
        LinkedList<Integer> stack_multi = new LinkedList<>();
        // 保存两个'['之间的字母
        LinkedList<String> stack_res = new LinkedList<>();

        for(Character c: s.toCharArray()){
            if(c == '['){
                // 记录之前的倍数
                stack_multi.addLast(multi);
                // 将倍数之前的字符串保存
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }else if( c == ']'){
                StringBuffer tmp = new StringBuffer();
                int cur_multi = stack_multi.removeLast();

                for(int i=0; i<cur_multi;i++)
                    tmp.append(res);

                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if(c >= '0' && c <='9')
                multi = multi*10+ Integer.parseInt(c+ "");
            else
                res.append(c);
        }

        return res.toString();
    }

    public String decodeStringRec(String s){
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int index){
        StringBuffer res = new StringBuffer();
        int num = 0;

        while(index < s.length()){
            if(s.charAt(index) >= '0' && s.charAt(index) <= '9'){
                num = 10*num+ s.charAt(index)-'0';
            }
            else if(s.charAt(index) == '['){
                String[] tmp = dfs(s, index+1);
                // 更新递归后的最新索引
                index = Integer.parseInt(tmp[0]);
                while(num > 0){
                    res.append(tmp[1]);
                    num--;
                }
            }
            else if(s.charAt(index) == ']')
                // 需要返回最新指针位置以及当前得到的字符串
                return new String[]{String.valueOf(index), res.toString()};
            else
                res.append(s.charAt(index));
            index++;
        }

        return new String[]{res.toString()};
    }


    public static void main(String[] args) {
        DecodingString test = new DecodingString();

        System.out.println(test.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
        System.out.println(test.decodeStringRec("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
    }
}
