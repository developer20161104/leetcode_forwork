package basement.foundation;

/**
 * 实现一个最基本的并查集结构
 */
public class DisjointsetUnion {
    int[] arr;
    int size;

    DisjointsetUnion(int size){
        this.size = size;
        this.arr = new int[size+1];

        for(int i=1;i<=size;i++)
            arr[i] = i;
    }

    public int find(int x)
    {
        if(this.arr[x] == x)
            return x;
        else
            return find(this.arr[x]);
    }

    void merge(int i, int j){
        this.arr[find(i)] = find(j);
    }
}
