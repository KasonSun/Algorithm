package interview.H3C;

/**
 * 已知一课节点个数为n的二叉树的中序遍历单调递增，求该二叉树能有多少种树形，输出答案对10^9+7取模（n在1到1024之间）
 */

public class Q3 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算返回二叉树的个数
     * @param n int整型 二叉树的节点数
     * @return int整型
     */
    public int numberOfTree (int n) {
        // write code here
        if (n < 3) {
            return n;
        }
        long[] dp = new long[n + 1];
        long mod = (long)Math.pow(10, 9) + 7;
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            long count = 0;
            for (int j = 1; j <= i; j++) {
                count += dp[j - 1] * dp[i - j] % mod;
            }
            dp[i] = count % mod;
        }
        return (int) dp[n];
    }
}