package backtracking;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 */
public class SolveSudoku_37 {
    public static void main(String[] args) {
    }

    /**
     * 棋盘中的每一个位置都要放一个数字，并检测数字是否合法， 解数独的树形结构要比N皇后更宽更深，是一个二维的递归
     *
     * 递归三部曲：
     *          ①参数返回值：bool backTracking(char[][] board)（找到一个符合条件立刻返回，也就是叶子节点）
     *          ②不要终止条件，解数独需要遍历整个树形结构寻找可能的条件
     *          ③二维递归：一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backtracking(board);
    }

    public boolean backtracking(char[][] board) {
        //一个for遍历棋盘的行，一个for循环遍历棋盘的列
        //一行一列确定下来之后，递归遍历这个位置9个数字的可能性
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {//(i,j)这个位置放k是否合适
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k;
                        if (backtracking(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                //9个数都试玩了，都不行，就返回false
                return false;
                //一行一列确定下来之后，9个数都不行，说明棋盘找不到解
                //直接返回，这也就是为什么没有终止条件也不会永远填不满棋盘而无线递归下去
            }
        }
        //遍历完没有返回false，说明找到了合适的棋盘数字位置
        return true;
    }

    /**
     * 判断棋盘是否合法的三个维度
     *      ①同行是否重复
     *      ②同列是否重复
     *      ③9宫格是否重复
     */
    public boolean isValid(int row, int col, char c, char[][] board) {
        //同行是否重复
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
        }
        //同列是否重复
        for (int j = 0; j < 9; j++) {
            if (board[j][col] == c) {
                return false;
            }
        }
        //9宫格是否重复
        int starRow = (row / 3) * 3;
        int starCol = (col / 3) * 3;
        for (int i = starRow; i < starRow + 3; i++) {
            for (int j = starCol; j < starCol + 3; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}
