package dp;

/**
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 */
public class UniquePathsWithObstacles_63 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0,}, {0, 0, 0}};
        System.out.println("不同路径II的结果为：" + uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * 动态规划；T:O(m*n) S:O(m*n)
     *                  机器人从（0,0）位置出发，到（m-1,n-1）终点
     *                五部曲：①确定dp[i][j]数组以及下标的含义
     *                            dp[i][j]的定义为：从（0,0）出发，到（i,j）有dp[i][j]条不同的路径
     *                       ②确定递推公式
     *                            求dp[i][j],只有两个方向推导出来，dp[i-1][j]和dp[i][j-1],
     *                            若obstacleGrid[i][j]==0没有障碍则dp[i][j]=dp[i-1][j]+dp[i][j-1];
     *                            若obstacleGrid[i][j]==1有障碍，则保持初始状态0
     *                            代码：
     *                                  if (obstacleGrid[i][j] == 0) { // 当(i, j)没有障碍的时候，再推导dp[i][j]
     *                                      dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
     *                                }
     *                       ③dp数组的初始化
     *                            dp[i][0]=1；dp[0][j]=1存在障碍之后，则（i,0）中障碍之后都是走不到的位置了
     *                                                  （i,0）初始：1  1  1 障碍  0  0  0  0
     *
     *                              代码：
     *                                  vector<vector<int>> dp(m, vector<int>(n, 0));
     *                                  for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) dp[i][0] = 1;
     *                                  for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) dp[0][j] = 1;
     *                       ④确定遍历顺序
     *                            由递推公式，从左到右一层一层遍历就可以了
     *                       ⑤举例推导dp数组
     *                             1  1  1
     *                             1  0  1
     *                             1  1  2
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0; i < m && obstacleGrid[i][0]==0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j]==0; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
