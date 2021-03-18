package questions.hotproblem.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DividedString {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 使用 FLoyd算法，将问题抽象为两点间的路径长度问题
        int nvars = 0;

        Map<String , Integer> map = new HashMap<>();

        // 先统计变量的个数，并给对应的变量映射下标
        int n = equations.size();
        for(int i=0;i<n; i++){
            if(!map.containsKey(equations.get(i).get(0)))
                map.put(equations.get(i).get(0), nvars++);
            if(!map.containsKey(equations.get(i).get(1)))
                map.put(equations.get(i).get(1), nvars++);
        }

        double[][] graph = new double[nvars][nvars];
        // 初始化图
        for(int i=0; i<nvars;i++)
            Arrays.fill(graph[i], -1.0);

        for(int i=0;i<n;i++){
            int va = map.get(equations.get(i).get(0)), vb = map.get(equations.get(i).get(1));

            graph[va][vb] = values[i];
            graph[vb][va] = 1/values[i];
        }

        // floyd 思想，类似于动态规划，累计路径的长度值
        for(int k=0;k<nvars;k++){
            for(int i=0;i<nvars;i++){
                for(int j=0;j<nvars;j++){
                    if(graph[i][k] > 0 && graph[k][j] > 0)
                        graph[i][j] = graph[i][k]*graph[k][j];
                }
            }
        }

        // 针对每个问题对图进行查询获取结果
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for(int i=0; i<queriesCount;i++){
            List<String> query = queries.get(i);
            double res = -1.0;

            if(map.containsKey(query.get(0)) && map.containsKey(query.get(1))){
                int ia = map.get(query.get(0)), ib = map.get(query.get(1));
                if(graph[ia][ib] > 0)
                    res = graph[ia][ib];
            }

            ret[i] = res;
        }

        return ret;
    }
}
