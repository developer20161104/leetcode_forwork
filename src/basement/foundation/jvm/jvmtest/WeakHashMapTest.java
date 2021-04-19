package basement.foundation.jvm.jvmtest;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @program: leetcode
 * @Date: 2021-04-09 19:52
 * @Author: Lab
 * @Description:
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
        myHashMap();
    }

    private static void myHashMap(){
//        HashMap<Integer, String> map = new HashMap<>();

        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();

        System.out.println(map + "\t" + map.size());
    }
}
