package interview.webank;

import java.util.Scanner;

/**
 * 小明刚刚学会用动态规划方法解决最长上升子序列(LIS)问题。LIS的O(n2)动态规划解法是这样的:设dp[门] 为以i结尾的最长上升子序列的长度,
 * 首先令所有的dp[i]=1,转移由dp[j] 转移过来,要求j属于 [1,i-1]且aj<ai,
 * 转移方程就是dp[i] = max(dp[i], dp[i] + 1),j属于 [1,i-1],i属于 [1,n]最后 max dp[i] 就是答案。
 * 明现在想知道:有多少个长度为n的整数序列,每个整数都在[1,m]之内,
 * 且这个序列的最长上升子序列的长度恰好等于3?由于答案可能会很大,求得的结果对998244353取模即可。
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = Integer.valueOf(str[0]);
        int m = Integer.valueOf(str[1]);

    }
}
