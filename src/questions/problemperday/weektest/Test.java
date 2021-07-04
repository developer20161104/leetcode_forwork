package questions.problemperday.weektest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: leetcode
 * @Date: 2021-06-13 11:35
 * @Author: Lab
 * @Description:
 */
public class Test {

    public boolean makeEqual(String[] words) {
        if(words.length < 1){
            return true;
        }
        if(words.length == 1){
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for(String val: words){
            for(char v: val.toCharArray()){
                if(map.containsKey(v)){
                    map.put(v, map.get(v)+1);
                }else map.put(v, 1);
            }
        }

        List<Integer> values = new ArrayList<>(map.values());
        if(values.get(0) < words.length){
            return false;
        }
        for(int i=1;i<values.size();i++){
            if(values.get(i) % words.length != 0 ){
                return  false;
            }
        }

        return true;
    }
}
