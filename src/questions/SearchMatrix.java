package questions;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 由于行最多减少 rlen次，列最多增加 clen次，因此时间复杂度为O(rlen+clen)
        int rlen=matrix.length, clen=matrix[0].length;
        int i=rlen-1, j=0;

        // 此处使用的是左下位，向右增加，向上减少
        while(i>=0 && j < clen){
            if(target < matrix[i][j])
                i--;
            else if(target > matrix[i][j])
                j++;
            else
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        SearchMatrix test = new SearchMatrix();

        int[][] matrix = new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30},
        };

        System.out.println(test.searchMatrix(matrix, 20));
    }
}
