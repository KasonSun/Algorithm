package dp;

/**
 * 72. 编辑距离(困难)
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 */
public class MinDistance_72 {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("最小编辑距离为：" + minDistance(word1, word2));
    }

    /**
     * 动态规划：T(O(n*m))  S(O(n*m)) n为word1的长度，m为word2的长度
     *      注意：题目是返回将 word1 转换成 word2 所使用的最少操作数
     *              word1 ---> word2
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i][j]：以i-1为结尾的word1和以j-1为结尾的word2 想要达到相等，最近的编辑距离为dp[i][j]。
     *
     *                  ②状态转移方程
     *                      在确定递推公式的时候，首先要考虑清楚编辑的几种操作，整理如下：
     *                      if (word1[i - 1] == word2[j - 1])
     *                          不操作
     *                      if (word1[i - 1] != word2[j - 1])
     *                          插
     *                          删
     *                          换
     *                      也就是以上的四中情况：
     *                          1. if (word1[i - 1] == word2[j - 1]) 那么说明不用任何编辑，dp[i][j] 就应该是 dp[i - 1][j - 1]，即dp[i][j] = dp[i - 1][j - 1];
     *                          此时可能有同学有点不明白，为啥要即dp[i][j] = dp[i - 1][j - 1]呢？
     *                          那么就在回顾上面讲过的dp[i][j]的定义，word1[i - 1] 与 word2[j - 1]相等了，那么就不用编辑了，
     *                          以下标i-2为结尾的字符串word1和以下标j-2为结尾的字符串word2的最近编辑距离dp[i - 1][j - 1]就是 dp[i][j]了。
     *                          在整个动规的过程中，最为关键就是正确理解dp[i][j]的定义！
     *
     *                          2.if (word1[i - 1] != word2[j - 1])，此时就需要编辑了，如何编辑呢？
     *                          操作一：word1删除一个元素，那么就是以下标i - 2为结尾的word1 与 j-1为结尾的word2的最近编辑距离 再加上一个操作。
     *                              dp[i][j] = dp[i - 1][j] + 1;
     *                          操作二：word2删除一个元素，那么就是以下标i - 1为结尾的word1 与 j-2为结尾的word2的最近编辑距离 再加上一个操作。
     *                          (这里有同学发现了，怎么都是删除元素，添加元素去哪了。word2添加一个元素，相当于word1删除一个元素，word2删除一个元素相当于word1添加一个元素
     *                          例如 word1 = "ad" ，word2 = "a"，word1删除元素'd' 和 word2添加一个元素'd'，变成word1="a", word2="ad"， 最终的操作数是一样！)
     *                              dp[i][j] = dp[i][j - 1] + 1;
     *                          操作三：替换元素，word1替换word1[i - 1]位置的字母，使其与word2[j - 1]相同，此时不用增加元素，那么以下标i-2为结尾的word1
     *                          与 j-2为结尾的word2的最近编辑距离 加上一个替换元素的操作。
     *                              dp[i][j] = dp[i - 1][j - 1] + 1;
     *
     *                              综上，当 if (word1[i - 1] != word2[j - 1]) 时取最小的，即：dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
     *
     *                  ③dp[i]的初始化
     *                      dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。
     *                      么dp[i][0] 和 dp[0][j] 表示什么呢？
     *                      dp[i][0] ：以下标i-1为结尾的字符串word1，和空字符串word2，最近编辑距离为dp[i][0]。
     *                      那么dp[i][0]就应该是i，对word1里的元素全部做删除操作，即：dp[i][0] = i;
     *                      同理dp[0][j] = j;
     *
     *                  ④确定遍历顺序
     *                      由状态转移公式：以在dp矩阵中一定是从左到右从上到下去遍历。
     *
     *                  ⑤举例推导dp数组
     *                      word1 = "horse", word2 = "ros"
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        //dp[i][j]：以i-1为结尾的word1和以j-1为结尾的word2 想要达到相等，最近的编辑距离为dp[i][j]。
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
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
