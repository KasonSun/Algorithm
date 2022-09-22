package interview.meituan;

import java.util.Scanner;

/**
 * Q3：迷宫探险
 *
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] pStr = sc.nextLine().split(" ");
        String[] wStr = sc.nextLine().split(" ");
        int[] p = new int[n+1];
        int[] w = new int[n+1];
        for (int i = 1; i < n; i++) {
            p[i] = Integer.valueOf(pStr[i-1]);
            w[i] = Integer.valueOf(wStr[i-1]);
        }
        int[] dp = new int[n];
        dp[1] = w[1];
        for (int i = 1; 2*i+1 <= n; i++) {
            dp[2 * i] = dp[i] + w[2 * i];
            dp[2 * i + 1] = dp[i] + w[2 * i + 1];
        }
        System.out.println(Math.max(dp[n - 1], dp[n]));
    }
}
