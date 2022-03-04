package dp;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 注意：(0 <= s.length <= 100, 0 <= t.length <= 10^4,两个字符串都只由小写字符组成)
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 *
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 */
public class IsSubsequence_392 {
    public static void main(String[] args) {
        String s = "";//空串是任何字符串的子串
        String t = "ahbgdc";
        System.out.println("子序列判断结果为：" + isSubsequence(s, t));
    }

    /**
     * 2.动态规划：T(O(n*m))  S(O(n*m))
     *                  注意：需要保证连续并且和最大,s是否是t的子串
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i][j] 表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]。
     *                      注意这里是判断s是否为t的子序列。即t的长度是大于等于s的
     *
     *                  ②状态转移方程
     *                      if (s[i - 1] == t[j - 1]): t中找到了一个字符在s中也出现了;
     *                          那么dp[i][j] = dp[i - 1][j - 1] + 1;
     *
     *                      if (s[i - 1] != t[j - 1]): 相当于t要删除元素，继续匹配
     *                          此时相当于t要删除元素，t如果把当前元素t[j - 1]删除，
     *                          那么dp[i][j] 的数值就是 看s[i - 1]与 t[j - 2]的比较结果了，即：dp[i][j] = dp[i][j - 1];
     *
     *                  ③dp[i]的初始化
     *                      从递推公式可以看出dp[i][j]都是依赖于dp[i - 1][j - 1] 和 dp[i][j - 1]，所以dp[0][0]和dp[i][0]是一定要初始化的。
     *                      这里大家已经可以发现，在定义dp[i][j]含义的时候为什么要表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]。
     *                      因为这样的定义在dp二维矩阵中可以留出初始化的区间，如图（看代码随想录题解）
     *                      如果要是定义的dp[i][j]是以下标i为结尾的字符串s和以下标j为结尾的字符串t，初始化就比较麻烦了
     *                      这里dp[i][0]和dp[0][j]是没有含义的，仅仅是为了给递推公式做前期铺垫，所以初始化为0。
     *                      其实这里只初始化dp[i][0]就够了，但一起初始化也方便，所以就一起操作了，
     *
     *                  ④确定遍历顺序
     *                      同理从递推公式可以看出dp[i][j]都是依赖于dp[i - 1][j - 1] 和 dp[i][j - 1]，那么遍历顺序也应该是从上到下，从左到右
     *
     *                  ⑤举例推导dp数组
     *                      以示例一为例，输入：s = "abc", t = "ahbgdc"，dp状态转移图如下：
     *                      dp[i][j]表示以下标i-1为结尾的字符串s和以下标j-1为结尾的字符串t 相同子序列的长度，
     *                      所以如果dp[s.size()][t.size()] 与 字符串s的长度相同说明：s与t的最长相同子序列就是s，那么s 就是 t 的子序列。
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t){
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = dp[i][j - 1];//表示指向t串指针移动，因为s相当于必须匹配成功的串
                }
            }
        }
        if (dp[s.length()][t.length()] == s.length()) {
            return true;
        }
        return false;
    }
    /**
     *  1.双指针法 T(O(n))  S(O(1))
     *      两个指针循环遍历，i,j指针
     *      空串是任何字符串的子串
     *      t中出现s，t的长度应该大于等于s
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence01(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        if (s.length() == 0 || s == null) {
            return true;
        }
        for (int i = 0, j = 0; i < s.length() && j < t.length(); j++) {
            if (s.charAt(i) == t.charAt(j)) {
                if (i == s.length() - 1) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }
}
