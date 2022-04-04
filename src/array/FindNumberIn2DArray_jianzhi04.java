package array;

/**
 * 剑指 Offer 04. 二维数组中的查找  此题与240.搜素二维矩阵一致
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * 限制：
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 */
public class FindNumberIn2DArray_jianzhi04 {
    /**
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增， 因此从左下角开始查找，
     * 当要查找数字比左下角数字大时。右移 要查找数字比左下角数字小时，上移。这样找的速度最快。
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col <= matrix[0].length - 1) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else{
                return true;
            }
        }
        return false;
    }
}
