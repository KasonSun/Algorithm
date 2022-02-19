package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class WordBreak_139 {
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        String[] array = {"leet", "code"};
        wordDict = Arrays.asList(array);
        System.out.println("单词拆分结果为：" + wordBreak(s, wordDict));
    }

    /**
     * 动态规划；T(O(n^3))  S(O(n))
     *          题目说不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用，所以是完全背包
     *                五部曲：①确定dp数组以及下标的含义
     *                            dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词
     *                       ②确定递推公式
     *                            如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。（j < i ）
     *                            所以递推公式是 if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true
     *                       ③dp数组的初始化
     *                            dp[i] 的状态依靠 dp[j]是否为true，那么dp[0]就是递归的根基，dp[0]一定要为true，否则递归下去后面都都是false了
     *                            dp[0]表示如果字符串为空的话，说明出现在字典里
     *                            下标非0的dp[i]初始化为false，只要没有被覆盖说明都是不可拆分为一个或多个在字典中出现的单词
     *                       ④确定遍历顺序
     *                            随然两种方式都可以，但本题还有特殊性，因为是要求子串，最好是遍历背包放在外循环，将遍历物品放在内循环
     *                       ⑤举例推导dp数组
     *                                  "leetcode"  ["leet","code"]
     *                                  dp[i] 1 0 0 0 1 0 0 0 1    (0-false  1-true)
     *
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];//布尔数组默认值false
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;//dp[i] : 字符串长度为i(实际上截取字符串取不到i)的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词
                }
            }
        }
        return dp[s.length()];
    }
}
