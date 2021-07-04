package questions.exam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: leetcode
 * @Date: 2021-04-18 10:28
 * @Author: Lab
 * @Description: 火车调度问题：需要分类，不同种类进行单独放置
 */
public class MainMeituan {

    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        while(in.hasNextLine()){
            HashMap<String, ArrayList<Integer>> typeElem = new HashMap<>();
            HashMap<String, Integer> typePos = new HashMap<>();

            int start = 0;
            int N = Integer.parseInt(in.nextLine());
            for(int i=0;i<N;i++){
                String[] line = in.nextLine().split(" ");
                String choose = line[0];
                String type = line[2];

                if(choose.equals("1")){
                    // 添加
                    Integer elem = Integer.valueOf(line[1]);
                    if(typePos.containsKey(type)){
                        typeElem.get(type).add(elem);
                    }else {
                        typePos.put(type, start++);
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(elem);
                        typeElem.put(type, list);
                    }
                }else {
                    // 换序
                    Integer pos1 = typePos.get(line[1]);
                    Integer pos2 = typePos.get(line[2]);

                    typePos.put(line[1], pos2);
                    typePos.put(line[2], pos1);
                }
            }

            // 获取顺序的type
            List<String> sort =  typePos.entrySet().stream().sorted((a, b)-> a.getValue()-b.getValue()).map(Map.Entry::getKey).collect(Collectors.toList());

            for(String type: sort){
                for(Integer elem: typeElem.get(type)){
                    System.out.print(elem+" ");
                }
            }
        }
    }
}
