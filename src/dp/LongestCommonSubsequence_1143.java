package dp;

/**
 * 1143. 最长公共子序列（ 可以不连续）
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 */
public class LongestCommonSubsequence_1143 {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("字符串的最长公共子序列长度为：" + longestCommonSubsequence(text1, text2));
    }

    /**
     * 动态规划：T(O(n*m))  S(O(n*m)) n为text1的长度，m为text2的长度
     *
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i][j]:长度为(0,i-1)的字符串text1和长度的为(0,j-1)的字符串text2的最长公共子序列为dp[i][j]
     *
     *                  ②状态转移方程
     *                      存在两种情况，即是text1[i-1]text2[j-1]相同 和 text1[i-1]text2[j-1]不相同
     *                          text1[i-1]text2[j-1]相同，说明找到了一个相同的元素，dp[i][j]=dp[i-1][j-1]+1
     *
     *                          text1[i-1]text2[j-1]不相同，则继续看text1[0,i-2]与text2[0,j-1]的最长公共子序列
     *                          和text1[0,i-1]与text2[0,j-2]的最长公共子序列，取最大值 max(dp[i-1][j],dp[i][j-1])
     *
     *                  ③dp的初始化
     *                       text1[0，i-1]和空串的最长公共子序列为0，所以dp[i][0]=0
     *                       同理dp[0][j]初始化为0
     *                       其他下标都是随着递推公式逐步覆盖，初始为多少都可以，那么就统一初始为0。
     *
     *                  ④确定遍历顺序
     *                      根据状态转移方程，可以看出由三个方向可以推导出dp[i][j],故应该从前向后从上到下进行遍历
     *
     *                  ⑤举例推导dp数组
     *                      以输入：text1 = "abcde", text2 = "ace" 为例
     *                      dp[text1.size()][text2.size()]为最终结果
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
