package array.array04_spiralmatrixii;

import java.util.Arrays;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 */
public class SpiralMatrixII__59 {
    public static void main(String[] args) {
        int n = 4;
        int[][] target = new int[n][n];
        target = spiralMatrixII01(n);
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.print(Arrays.toString(target[i]));
                break;
            }
            System.out.print(Arrays.toString(target[i]));
            System.out.print("\n");
        }
        System.out.print("]");
    }

    /**
     * 螺旋矩阵二
     *
     * @param n
     * @return
     */
    private static int[][] spiralMatrixII(int n) {
        //n==1直接进行返回
        if (n == 1) {
            return new int[][]{{1}};
        }

        int[][] res = new int[n][n];
        int loop = n / 2;//每个圈循环次数，n为3则循环一圈，中间的值需要单独处理
        int mid = n / 2;//中间位置，n为3， 中间位置就是（1,1）；n为5（2,2）

        int startX = 0;//每次循环的起始位置
        int startY = 0;

        int count = 1;//填充数字
        int offset = 1;//定义偏移量(偏移量的原因是保证前闭后开，最后一个位置作为转向部分)（每一圈循环需要控制每一条边的长度）

        while (loop > 0) {
            int i = startX;
            int j = startY;

            //模拟上侧从左到右
            //四个方向满足前闭后开循环不变原则
            for (; j < startX + n - offset; j++) {
                res[i][j] = count++;
            }
            //模拟右侧从上到下
            for (; i < startY + n - offset; i++) {
                res[i][j] = count++;
            }
            //模拟下侧从右到左
            for (; j > startX; j--) {
                res[i][j] = count++;
            }
            //模拟左侧从下到上
            for (; i > startY; i--) {
                res[i][j] = count++;
            }
            loop--;
            //第二圈开始的时候，起始位置各自加1，如第一圈起始位置为（0,0）则第二圈起始位置为（1,1）
            startX++;
            startY++;

            //offset控制每一条边的遍历长度（+2是因为前闭后开区间每遍历一圈对应的两边都会少1）
            offset += 2;

            //若n为奇书需要单独给中间位置赋值
            if (n % 2 == 1) {
                res[mid][mid] = count;
            }
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/spiral-matrix-ii/solution/luo-xuan-ju-zhen-ii-by-leetcode-solution-f7fp/
     *
     * @param n
     * @return
     */
    private static int[][] spiralMatrixII01(int n) {
        if (n == 1) {
            return new int[][]{{1}};
        }

        int num = 1;
        int[][] matrix = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            for (int col = left; col <= right; col++) {//一横
                matrix[top][col] = num++;
            }
            for (int row = top + 1; row <= bottom; row++) {//一竖
                matrix[row][right] = num++;
            }
            if (left < right && top < bottom) {
                for (int col = right - 1; col > left; col--) {//反向一横，区别上面此处要留空
                    matrix[bottom][col] = num++;
                }
                for (int row = bottom; row > top; row--) {//反向一竖，区别上面此处要留空，接头处
                    matrix[row][left] = num++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return matrix;

    }

}
