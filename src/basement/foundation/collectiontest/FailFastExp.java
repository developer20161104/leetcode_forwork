package basement.foundation.collectiontest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FailFastExp {
    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>(Arrays.asList(1,2,3,1));

        // 如果没有break则会出现 ConcurrentModificationException
//        for(Integer i:test){
//            System.out.println(i);
//            if(i == 3){
//                test.remove(new Integer(3));
//                // 由于及时跳出，迭代器与修改操作不冲突，所以不会出现异常
//                break;
//            }
//        }

        // 遍历的同时移除元素的正确姿势
        Iterator<Integer> iter = test.iterator();
        while(iter.hasNext()){
            Integer curI = iter.next();
            System.out.println(curI);
            if(curI == 3)
                iter.remove();
        }
    }
}
