package dp;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * (注意m，n从1开始)
 *
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 输入：m = 3, n = 3
 * 输出：6
 */
public class UniquePaths_62 {
    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println("不同路径I结果为：" + uniquePaths(m, n));
        System.out.println("不同路径I结果为：" + uniquePaths01(m, n));
    }

    /**
     * 1.动态规划；T:O(m*n) S:O(m*n)
     *                  机器人从（0,0）位置出发，到（m-1,n-1）终点
     *                五部曲：①确定dp[i][j]数组以及下标的含义
     *                            dp[i][j]的定义为：从（0,0）出发，到（i,j）有dp[i][j]条不同的路径
     *                       ②确定递推公式
     *                            求dp[i][j],只有两个方向推导出来，dp[i-1][j]和dp[i][j-1],则dp[i][j]=dp[i-1][j]+dp[i][j-1]
     *                       ③dp数组的初始化
     *                            dp[i][0]一定都为1；dp[0][j]同理
     *                       ④确定遍历顺序
     *                            由递推公式，从左到右一层一层遍历就可以了
     *                       ⑤举例推导dp数组
     *                             m=3,n=7,
     *                             1  1  1  1  1  1  1
     *                             1  2  3  4  5  6  7
     *                             1  3  6  10 15 21  28
     *
     * @param m,n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //dp初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 2.深度优先搜索--遍历整个二叉树
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths01(int m, int n) {
        return dfs(1, 1, m, n);
    }

    public static int dfs(int i, int j, int m, int n) {
        if(i>m || j>n) return 0;//越界
        if(i==m && j==n) return 1;//相当于找到了一条路径，也即为叶子节点
        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }
}
