package string;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 输入: "aba"
 * 输出: False
 *
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class RepeatedSubstringPattern_459 {
    public static void main(String[] args) {
        String s="abab";
        System.out.println(repeatedSubstring(s));
    }
    /**
     * 1.
     * 对于KMP算法的next数组求解可以看出，本题实际上是KMP的应用
     *      数组长度减去最长相同前后缀的长度相当于是第一个周期的长度，也就是一个周期的长度，如果这个周期可以被整除，就说明整个数组就是这个周期的循环。
     *       强烈建议大家把next数组打印出来，看看next数组里的规律，有助于理解KMP算法
     *       eg:s="asdfasdfasdf"
     *          next[]={0,0,0,0,1,2,3,4,5,6,7,8}
     *          最长相等前后缀的长度为next[len-1]=8
     * @param s
     * @return
     */
    public static boolean repeatedSubstring(String s) {
        int len = s.length();
        int[] next = new int[len];
        getNext(next, s);

        //判断是否有重复的子字符串,
        // next[len - 1] > 0(!=0)这一步能够过滤不含重复子串的字符串；例如“aabaaf”其next[]={0,0,1,2,0}，next[len-1]=0，此时已经不满足要求
        // len % (len - next[len - 1]) == 0，判断了含有周期循环的子串存在
        if (next[len - 1] != 0 && len % (len - next[len - 1]) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取next数组
     * @param next
     * @param s
     */
    public static void getNext(int[] next, String s) {
        //初始化next
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }

    /**
     * 2.技巧
     * @param s
     * @return
     */
    public static boolean repeatedSubString01(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }

    /**
     * 3.暴力法
     * @param s
     * @return
     */
    public static boolean repeatedSubString02(String s) {
        if (s.length() <= 1) {
            return false;
        }
        for (int i = 1; i * 2 <= s.length(); i++) {
            if (s.length() % i == 0) {
                boolean match = true;
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
}
