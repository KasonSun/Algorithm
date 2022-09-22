package interview.zte;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 捡金币:求捡金币的最大值，不能相邻捡
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int[] coins = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            coins[i] = Integer.valueOf(str[i]);
        }

        int[][] dp = new int[coins.length][2];
        dp[0][0] = coins[0];//取当前值
        dp[0][1] = 0;//不取当前值
        for (int i = 1; i < coins.length; i++) {
            /*dp[i][0] = Math.max(dp[i - 1][1] + coins[i], dp[i][0]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i][1]);*/
            dp[i][0] = dp[i - 1][1] + coins[i];
            dp[i][1] = dp[i - 1][0];
        }
        System.out.println(Math.max(dp[coins.length-1][0], dp[coins.length-1][1]));
    }
}
