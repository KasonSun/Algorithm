package dp;

/**
 * NC_BM68 矩阵的最小路径和
 *  给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 * 数据范围:1≤n,m≤500，矩阵中任意值都满足0-100
 * i,j≤100
 * 要求：时间复杂度 O(nm)O(nm)
 */
public class MinPathSum_NC_BM68 {
    public int minPathSum (int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int[][] dp=new int[n][m];
        int count=0;
        for(int i=0;i<n;i++){
            count+=matrix[i][0];
            dp[i][0]=count;
        }
        count=0;
        for(int j=0;j<m;j++){
            count+=matrix[0][j];
            dp[0][j]=count;
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+matrix[i][j];
            }
        }
        return dp[n-1][m-1];
    }
}
