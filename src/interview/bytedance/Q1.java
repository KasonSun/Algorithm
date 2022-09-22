package interview.bytedance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 弹珠游戏嘛
 * 给定一个地图
 *
 * 这个地图有两种数字，一种是-1，另一种是分数
 * 若是-1则可以像左下和右下走，否则只能向下走，问价值最大的一条路径
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int N = Integer.valueOf(str[0]);
        int M = Integer.valueOf(str[1]);
        int[][] matrix = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            String[] str1 = sc.nextLine().split(" ");
            for (int j = 1; j <= M; j++) {
                matrix[i][j] = Integer.valueOf(str1[j-1]);
            }
        }
        int[][] dp = new int[N+2][M+2];
        boolean[][] visited = new boolean[N+2][M+2];
        for (int j = 1; j <= M; j++) {
            visited[1][j] = true;
        }
        for(int i=1;i<=N;i++){
            for (int j = 1; j <=M; j++) {
                if(!visited[i][j]) continue;//只有当前被访问过，下面的if逻辑才能执行，意味着从第一行的每个位置开始
                if(matrix[i][j]==-1){//向左下和右下走
                    dp[i + 1][j - 1] = Math.max(dp[i + 1][j - 1], dp[i][j]);
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
                    visited[i + 1][j - 1]=true;
                    visited[i + 1][j + 1]=true;
                }else{//matrix[i][j]>=0，只能向下走
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + matrix[i][j]);
                    visited[i + 1][j]=true;
                }
            }
        }
        int result=0;
        for (int j = 1; j <= M; j++) {
            result = Math.max(result, dp[N+1][j]);
        }
        System.out.println(result);
    }
}
