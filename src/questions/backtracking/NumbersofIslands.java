package questions.backtracking;

public class NumbersofIslands {
    private int[][] move = new int[][]{{0,1},{1,0},{0, -1},{-1,0}};

    public int numIslands(char[][] grid) {
        int count = 0, rowLen = grid.length;
        if(rowLen < 1)
            return 0;
        int colLen = grid[0].length;

        for(int i=0;i<rowLen;i++){
            for(int j=0;j<colLen;j++){
                if(grid[i][j] == '1')
                    count += dfs(grid, rowLen, colLen, i,j);
            }
        }
        return count;
    }

    private int dfs(char[][] grid,int rowLen, int colLen, int curRow, int curCol){
        if(grid[curRow][curCol] == '0')
            return 1;

        grid[curRow][curCol] = '0';
        for(int i=0;i<4;i++){
            int row = curRow+move[i][0], col = curCol+move[i][1];
            if(row>=0 && row < rowLen && col >=0 && col<colLen)
                dfs(grid, rowLen, colLen, row, col);
        }

        return 1;
    }


    public static void main(String[] args) {
        NumbersofIslands test = new NumbersofIslands();

        char[][] island = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
        };

        System.out.println(test.numIslands(island));
    }
}
