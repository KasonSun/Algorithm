package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 * 每一种解法包含一个不同的n皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 */
public class SolveNQueens_51 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("n皇后的解法为：" + new SolveNQueens_51().solveNQueens(n));
    }

    /**
     * 皇后的约束条件：① 不能同行；②不能同列；③不能同斜线（核心就是对三个条件进行验证）
     * （通过画树形结构图可以看出只要搜索到了树的叶子节点，说明就找到了皇后门的合理位置了；row的大小达到n也即为叶子节点）
     * <p>
     * 递归三部曲：
     * ①递归参数和返回值：全局变量result来记录最终的结果；参数n为棋盘的大小，row来记录当前遍历到棋盘的第几层 void backTracking(int n, int row, Char[][] chessboard)
     * ②递归终止条件：递归到叶子节点，就可以收集结果进行返回了
     * ③单层递归逻辑：递归的深度即为row控制棋盘的行，每一行中for循环的col控制棋盘的列，一行一列，确定最后皇后的位置，每一次都要从新的一行开始搜索，从0开始
     *
     * @param n
     * @return
     */
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard=new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTracking(n, 0, chessboard);
        return result;
    }

    //回溯递归核心逻辑（递归深度row可以到达n，宽度为for循环中的col）
    public void backTracking(int n, int row, char[][] chessboard) {
        if (row == n) {
            result.add(ArrayToList(chessboard));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTracking(n, row + 1, chessboard);
                chessboard[row][col] = '.';
            }
        }
    }

    //皇后的约束条件：① 不能同行；②不能同列；③不能同斜线（核心就是对三个条件进行验证）
    //在这份代码中，细心的同学可以发现为什么没有在同行进行检查呢？
    //因为在单层搜索的过程中，每一层递归，只会选for循环（也就是同一行）里的一个元素，所以不用去重了。
    public static boolean isValid(int row, int col, int n, char[][] chessboard) {
        //检查列
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        //检查45度对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        //检查135度对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    //将二维数组转换为列表
    public static List ArrayToList(char[][] chessboard) {
        List<String> list = new ArrayList<>();
        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
}
