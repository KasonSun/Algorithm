package array.array04_spiralmatrixii;

import java.util.Arrays;

/**
 * 输入一个矩阵，按照从外向里以顺时针顺序打印矩阵
 * 输入：{{1,2,3},{4,5,6},{7,8,9}}
 * 输出：1,2,3,6,9,8,7,4,5
 *
 */
public class PrintMatrix_jianzhiOffer_29 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] result = printMatrix01(matrix);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 由外向里顺时针打印
     *
     * @param matrix
     * @return
     */
    private static int[] printMatrix(int[][] matrix) {
        int degree = matrix.length;//degree指的是矩阵的阶数
        int[] result = new int[degree * degree];//用于接受顺序打印的返回值
        int i = 0;//作为result的索引

        int left = 0, right = degree - 1, top = 0, bottom = degree - 1;
        while (left <= right && top <= bottom) {
            //上侧从左到右(闭区间)
            for (int column = left; column <= right; column++) {
                result[i++] = matrix[top][column];
            }
            //右侧从上到下
            for (int row = top + 1; row <= bottom; row++) {
                result[i++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                //下侧从右到左
                for (int column = right - 1; column > left; column--) {
                    result[i++] = matrix[bottom][column];

                }
                //左侧从下到上
                for (int row = bottom; row > top; row--) {
                    result[i++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

    public static int[] printMatrix01(int[][] matrix){
        int[] result = new int[matrix.length * matrix.length];
        int i=0;

        int left = 0, right = matrix.length - 1, top = 0, bottom = matrix.length - 1;
        while (left <= right && top <= bottom) {
            for (int col = left; col <= right; col++) {
                result[i++] = matrix[top][col];
            }
            for (int row = top + 1; row <= bottom; row++) {
                result[i++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int col = right - 1; col > left; col--) {
                    result[i++] = matrix[bottom][col];
                }
                for (int row = bottom; row > top; row--) {
                    result[i++] = matrix[row][left];
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
