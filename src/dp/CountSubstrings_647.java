package dp;

/**
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
public class CountSubstrings_647 {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println("回文子串的数量为:" + countSubstrings(s));
    }

    /**
     * 3. 动态规划：T(O(n^2))  S(O(n^2))
     *
     *              五部曲：
     *                  ①dp数组定义
     *                      布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false
     *
     *                  ②状态转移方程
     *                      当s[i]与s[j]不相等，那没啥好说的了，dp[i][j]一定是false。
     *                      当s[i]与s[j]相等时，这就复杂一些了，有如下三种情况
     *                          情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
     *                          情况二：下标i 与 j相差为1，例如aa，也是文子串
     *                          情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，
     *                          我们看i到j区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是 i+1 与 j-1区间，
     *                          这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
     *
     *                          if (s[i] == s[j]) {
     *                            if (j - i <= 1) { // 情况一 和 情况二
     *                               result++;
     *                               dp[i][j] = true;
     *                                } else if (dp[i + 1][j - 1]) { // 情况三
     *                                 result++;
     *                                 dp[i][j] = true;
     *                             }
     *                          }
     *                          result就是统计回文子串的数量。
     *                          注意这里我没有列出当s[i]与s[j]不相等的时候，因为在下面dp[i][j]初始化的时候，就初始为false。
     *
     *                  ③dp[i]的初始化
     *                      dp[i][j]可以初始化为true么？ 当然不行，怎能刚开始就全都匹配上了。所以dp[i][j]初始化为false。
     *
     *                  ④确定遍历顺序
     *                      首先从递推公式中可以看出，情况三是根据dp[i + 1][j - 1]是否为true，在对dp[i][j]进行赋值true的。
     *                      dp[i + 1][j - 1] 在 dp[i][j]的左下角，如图：
     *                      如果这矩阵是从上到下，从左到右遍历，那么会用到没有计算过的dp[i + 1][j - 1]，
     *                      也就是根据不确定是不是回文的区间[i+1,j-1]，来判断了[i,j]是不是回文，那结果一定是不对的。
     *                      所以一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的。
     *
     *                  ⑤举例推导dp数组
     *                       举例，输入："aaa"，dp[i][j]状态如下：
     *                       注意因为dp[i][j]的定义，所以j一定是大于等于i的，那么在填充dp[i][j]的时候一定是只填充右上半部分。
     *
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {//情况一二
                    if (j - i <= 1) {
                        result++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) {//情况三
                        result++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     *  1.双指针法 O(n^2) O(1)
     *  动态规划的空间复杂度是偏高的，我们再看一下双指针法。首先确定回文串，就是找中心然后想两边扩散看是不是对称的就可以了。
     *  在遍历中心点的时候，要注意中心点有两种情况。一个元素可以作为中心点，两个元素也可以作为中心点。
     *  那么有人同学问了，三个元素还可以做中心点呢。其实三个元素就可以由一个元素左右添加元素得到，四个元素则可以由两个元素左右添加元素得到。
     *  所以我们在计算的时候，要注意一个元素为中心点和两个元素为中心点的情况。
     *  这两种情况可以放在一起计算，但分别计算思路更清晰，我倾向于分别计算，
     *
     * @param s
     * @return
     */
    public static int countSubstrings01(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += extend(s, i, i) ;// 以i为中心
            result += extend(s, i, i + 1);// 以i和i+1为中心
        }
        return result;
    }

    public static int extend(String s, int i, int j) {
        int count = 0;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            count++;
        }
        return count;
    }

    /**
     * 2.暴力解法
     *  O(n^3)  O(1)
     * @param s
     * @return
     */
    public static int countSubstrings02(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (i == j || valid(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean valid(String s, int first, int end) {
        for (int i = first, j = end; i < j; i++, i--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
