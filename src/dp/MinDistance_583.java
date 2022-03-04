package dp;

/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 *
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 *
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 *
 */
public class MinDistance_583 {
    public static void main(String[] args) {
        String word1 = "leetcode";
        String word2 = "etco";
        System.out.println("两个字符串删除的最少次数为：" + minDistance01(word1, word2));
    }

    /**
     * 1.动态规划：T(O(n*m))  S(O(n*m)) n为word1的长度，m为word2的长度
     *    本题和动态规划：115.不同的子序列 (opens new window)相比，其实就是两个字符串都可以删除了，情况虽说复杂一些，但整体思路是不变的。
     *    这次是两个字符串可以相互删了，这种题目也知道用动态规划的思路来解
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i][j]：以i-1为结尾的word1和以j-1为结尾的word2 想要达到相等，所需要删除的元素的最少次数dp[i][j]。
     *
     *                  ②状态转移方程
     *                      这一类问题，基本是要分析两种情况
     *                          word1[i - 1] 与 word2[j - 1]相等
     *                          word1[i - 1] 与 word2[j - 1] 不相等
     *                              1.当word1[i - 1] 与 word2[j - 1]相同的时候，dp[i][j] = dp[i - 1][j - 1];
     *
     *                              2.当word1[i - 1] 与 word2[j - 1]不相同的时候，有三种情况：
     *                                  情况一：删word1[i - 1]，最少操作次数为dp[i - 1][j] + 1
     *                                  情况二：删word2[j - 1]，最少操作次数为dp[i][j - 1] + 1
     *                                  情况三：同时删word1[i - 1]和word2[j - 1]，操作的最少次数为dp[i - 1][j - 1] + 2
     *                                  那最后当然是取最小值，所以当word1[i - 1] 与 word2[j - 1]不相同的时候，
     *                                  递推公式：dp[i][j] = min({dp[i - 1][j - 1] + 2, dp[i - 1][j] + 1, dp[i][j - 1] + 1});
     *
     *                  ③dp[i]的初始化
     *                      从递推公式中，可以看出来，dp[i][0] 和 dp[0][j]是一定要初始化的。
     *                      dp[i][0]：word2为空字符串，以i-1为结尾的字符串word1要删除多少个元素，才能和word2相同呢，很明显dp[i][0] = i。
     *                      dp[0][j]的话同理
     *
     *                  ④确定遍历顺序
     *                      从递推公式 dp[i][j] = min(dp[i - 1][j - 1] + 2, min(dp[i - 1][j], dp[i][j - 1]) + 1);
     *                      和dp[i][j] = dp[i - 1][j - 1]可以看出dp[i][j]都是根据左上方、正上方、正左方推出来的。
     *                      所以遍历的时候一定是从上到下，从左到右，这样保证dp[i][j]可以根据之前计算出来的数值进行计算。
     *
     *                  ⑤举例推导dp数组
     *                       word1:"sea"，word2:"eat"为例
     *                       最终的dp[word1.length()][word2.length()]就是出现个数
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];//相等则不需要删除
                }else{
                    //不相等，三种情况，删除word1；删除word2；同时删除
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**
     * 2.本题和动态规划：1143.最长公共子序列 (opens new window)基本相同，只要求出两个字符串的最长公共子序列长度即可，
     *      那么除了最长公共子序列之外的字符都是必须删除的，最后用两个字符串的总长度减去两个最长公共子序列的长度就是删除的最少步数。
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance01(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return word1.length()+word2.length()-dp[word1.length()][word2.length()]*2;
    }
}
