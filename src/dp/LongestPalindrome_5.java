package dp;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class LongestPalindrome_5 {
    public static void main(String[] args) {
        String s = "babad";
        System.out.println("最长回文子串为：" + palidrome(s));
    }

    /**
     * 1.双指针法（中心扩散法） O(n^2)   O(1)
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 分别拿到奇数偶数的回文子串长度
            int len1 = extend(s, i, i);// 以i为中心
            int len2 = extend(s, i, i + 1);// 以i和i+1为中心
            // 对比最大的长度
            int len = Math.max(len1, len2);
            // 计算对应最大回文子串的起点和终点
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        // 注意：这里的end+1是因为 java自带的左闭右开的原因
        return s.substring(start, end + 1);
    }

    public static int extend(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是两个字符，回文串的长度是偶数
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 回文串的长度是right-left+1-2 = right - left - 1（-2是因为满足条件后两个指针分别多走了一步）
        return right - left - 1;
    }

    /**
     * 2.暴力解法；O(n^3) O(1)
     * @param s
     * @return
     */
    public static String longestPalindrome01(String s) {
        if (s.length() < 2) {
            return s;
        }
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                //截取所有子串，然后在逐个判断是否是回文的
                if (isPalindrome(s, i, j)) {
                    if (maxLen < j - i + 1) {
                        start = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    //判断是否是回文子串
    public static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 3.动态规划   O(n^2)  O(n^2)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome02(String s) {
        if (s.length() < 2) {
            return s;
        }
        int maxLen = 1;
        int start = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        //初始化：所有长度为1的子串都是回文子串
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        //先枚举子串的长度
        for (int l = 2; l <= s.length(); l++) {
            //再枚举左边界，左边界的上限可以设置的宽松一些
            for (int i = 0; i < s.length(); i++) {
                //由L和i可以确定右边界，即j-i+1=l
                int j = l + i - 1;
                //如果右边界越界，退出当前循环(j是索引，取不到s.length())
                if (j >= s.length()) {
                    break;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                }else{
                    if (j - i < 2) {
                        //描述了j=i（相同位置）,j-i=1（相邻位置）
                        //此时两种情况都是回文子串，其他情况可以由这三种状态得到
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要dp[i][l]==true成立，表示s[i...l]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * 动态规划简化
     *  当此时的两个字符相等两种情况 j-i<=1:dp[i][j]=true; j-i>1:dp[i][j]=true;>dp[i][j]=dp[i+1][j-1]
     * @param s
     * @return
     */
    public static String palidrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0;
        int maxLen=0;
        for (int i = s.length()-1; i>=0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j]=true;
                    }else if(dp[i+1][j-1]==true){
                        dp[i][j]=true;
                    }
                    //之前由于没有加入dp[i][j]==true的判断导致测试用例不能完全通过
                    //观察上面if (s.charAt(i) == s.charAt(j))并且满足j-i<=1 或者 dp[i+1][j-1]为true, 才有dp[i][j]=true
                    //因此会存在dp[i][j]==false的情况，此时是不需要比较最大长度的，因为此时i，j指向并不是回文子串
                    if (dp[i][j] && maxLen < j - i + 1) {
                        start = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return s.substring(start, start+maxLen);
    }
}
