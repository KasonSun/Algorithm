package array.array04_spiralmatrixii;

import java.util.ArrayList;
import java.util.List;

/**
 *54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 */
public class SpiralMatrix_54 {
    /**
     * 注意与29,59区别，这里是m行n列，初始值需要注意
     * @param matrix
     * @return
     */
    public static List<Integer> spiralMatrix(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int left = 0, right = matrix[0].length-1, top = 0, bottom = matrix.length-1;
        while (left <= right && top <= bottom) {
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int col = right - 1; col > left; col--) {
                   result.add(matrix[bottom][col]);
                }
                for (int row = bottom; row > top; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }
}
