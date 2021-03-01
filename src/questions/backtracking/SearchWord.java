package questions.backtracking;

public class SearchWord {
    int[][] ways = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    int rlen, clen;

    public boolean exist(char[][] board, String word) {
        rlen = board.length;
        clen = board[0].length;

        // 需要进行逐项遍历
        for(int i=0;i<rlen;i++)
            for(int j=0;j<clen;j++)
                if(dfs(board, word, 0, new int[]{i,j}))
                    return true;
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int[] pos){
        // 当只出现一个字母时，需要特别判断，防止回溯时跳过
        if(index == word.length()-1)
            return word.charAt(index) == board[pos[0]][pos[1]];

        if(board[pos[0]][pos[1]] != word.charAt(index))
            return false;

        board[pos[0]][pos[1]] = '*';
        for(int i=0;i<4;i++){
            // 声明新坐标防止递归出错
            int newPosR = pos[0]+ways[i][0];
            int newPosC = pos[1] + ways[i][1];
            if(newPosR >= 0 && newPosR < rlen && newPosC >=0 && newPosC < clen){
                // 带返回值的递归需要向上传递结果
                if(dfs(board, word, index+1, new int[]{newPosR, newPosC}))
                    return true;
            }
        }
        // 这层回溯没有想到
        board[pos[0]][pos[1]] = word.charAt(index);

        return false;
    }

    public static void main(String[] args) {
        SearchWord test = new SearchWord();
//        char[][] board = new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'},
//        };

        char[][] board = new char[][]{
                {'a'},
        };
        System.out.println(test.exist(board, "a"));
    }
}
