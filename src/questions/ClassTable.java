package questions;

import java.util.*;

public class ClassTable {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建逆邻接表
        List<List<Integer>> adjacency = new ArrayList<>();

        for(int i=0;i<numCourses;i++)
            adjacency.add(new ArrayList<>());

        int[] flags = new int[numCourses];
        // 将对应位的元素进行匹配
        for(int[] cp: prerequisites){
            adjacency.get(cp[1]).add(cp[0]);
        }

        // 对每条路径使用DFS遍历
        for(int i=0;i<numCourses;i++)
            if(!dfs(adjacency, flags, i))
                return false;

        return true;
    }

    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i){
        // 在没有遍历完成的情况下，出现了1时，说明该位置被遍历了两次
        if(flags[i] == 1) return false;
        if(flags[i] == -1) return true;

        flags[i] = 1;
        for(Integer j:adjacency.get(i)){
            if(!dfs(adjacency, flags, j))
                return false;
        }

        // 遍历完成才会将遍历位置为-1
        flags[i] = -1;
        return true;

    }

    public boolean canFinishTop(int numCourses, int[][] prerequisites){
        if(numCourses <= 0)
            return false;

        int plen = prerequisites.length;
        if(plen == 0)
            return true;

        int[] inDegree = new int[numCourses];
        HashSet<Integer>[] adj = new HashSet[numCourses];

        // 初始化邻接表
        for(int i=0;i<numCourses;i++)
            adj[i] = new HashSet<>();

        // 获取各结点的入度情况以及构建关联结点的邻接表
        for(int[] p: prerequisites){
            inDegree[p[0]]++;
            adj[p[1]].add(p[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        // 加入入度为0的结点
        for(int i=0;i<numCourses;i++){
            if(inDegree[i] == 0)
                queue.add(i);
        }

        int cur = 0;
        while(!queue.isEmpty()){
            Integer top = queue.poll();
            cur++;

            for(int successor: adj[top]){
                // 删除邻接点带来的影响
                inDegree[successor]--;

                // 加入经过邻接点删除后入度为0的点
                if(inDegree[successor] == 0)
                    queue.add(successor);
            }
        }

        return cur == numCourses;
    }

    public static void main(String[] args) {
        ClassTable test = new ClassTable();

        System.out.println(test.canFinishTop(5, new int[][]{
                {1,4},
                {2,4},
                {3,1},
                {3,2},
        }));
    }
}
