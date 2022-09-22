package dp;

/**
 * 115. 不同的子序列(困难)
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *
 * 提示：
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 */
public class NumDistinct_115 {
    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        System.out.println("不同子序列的个数为："+numDistince(s, t));
    }

    /**
     * 动态规划：T(O(n*m))  S(O(n*m)) n为s的长度，m为t的长度
     *
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]。
     *
     *                  ②状态转移方程
     *                      这一类问题，基本是要分析两种情况
     *                          s[i - 1] 与 t[j - 1]相等
     *                          s[i - 1] 与 t[j - 1] 不相等
     *                              1.当s[i - 1] 与 t[j - 1]相等时，dp[i][j]可以有两部分组成。
     *                              一部分是用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]。
     *                              一部分是不用s[i - 1]来匹配，个数为dp[i - 1][j](相当于此时没有匹配相等位置，从t的j开始，i-1)。
     *                              这里可能有同学不明白了，为什么还要考虑 不用s[i - 1]来匹配，都相同了指定要匹配啊。
     *                              例如： s：bagg 和 t：bag ，s[3] 和 t[2]是相同的，但是字符串s也可以不用s[3]来匹配，即用s[0]s[1]s[2]组成的bag。
     *                              当然也可以用s[3]来匹配，即：s[0]s[1]s[3]组成的bag。
     *                              所以当s[i - 1] 与 t[j - 1]相等时，dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
     *
     *                              2.当s[i - 1] 与 t[j - 1]不相等时，dp[i][j]只有一部分组成，不用s[i - 1]来匹配，即：dp[i - 1][j]
     *                              所以递推公式为：dp[i][j] = dp[i - 1][j];
     *
     *                  ③dp[i]的初始化
     *                      从递推公式dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
     *                      和 dp[i][j] = dp[i - 1][j]; 中可以看出dp[i][0] 和dp[0][j]是一定要初始化的。
     *                      每次当初始化的时候，都要回顾一下dp[i][j]的定义，不要凭感觉初始化。
     *                      dp[i][0]表示什么呢？
     *                          dp[i][0] 表示：以i-1为结尾的s可以随便删除元素，出现t为空字符串的个数。
     *                          那么dp[i][0]一定都是1，因为也就是把以i-1为结尾的s，删除所有元素，出现空字符串的个数就是1。
     *                          再来看dp[0][j]，dp[0][j]：空字符串s可以随便删除元素，出现以j-1为结尾的字符串t的个数。
     *                          那么dp[0][j]一定都是0，s如论如何也变成不了t。
     *                          最后就要看一个特殊位置了，即：dp[0][0] 应该是多少。
     *                          dp[0][0]应该是1，空字符串s，可以删除0个元素，变成空字符串t。
     *
     *
     *                  ④确定遍历顺序
     *                      从递推公式dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; 和 dp[i][j] = dp[i - 1][j]; 中可以看出dp[i][j]都是根据左上方和正上方推出来的
     *                      所以遍历的时候一定是从上到下，从左到右，这样保证dp[i][j]可以根据之前计算出来的数值进行计算。
     *
     *                  ⑤举例推导dp数组
     *                       s："baegg"，t："bag"为例
     *                       最终的dp[s.length()][t.length()]就是出现个数
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistince(String s, String t) {
        //dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]。
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;//也就是把以i-1为结尾的s，删除所有元素，出现空字符串的个数就是1。
        }
        //下面的for循环可以省略（实际上已经在初始化的默认值里面了），为了表现出逻辑写了出来
        //上面j=0的情况初始化了，下面从1开始
        for (int j = 1; j <= t.length(); j++) {
            dp[0][j] = 0;//dp[0][j]：空字符串s可以随便删除元素，出现以j-1为结尾的字符串t的个数。dp[0][j]一定都是0，s如论如何也变成不了t。
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
