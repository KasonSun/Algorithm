package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割问题
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串:是正着读和反着读都一样的字符串。
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 */
public class Partition_131 {
    public static void main(String[] args) {
        String s = "aab";
        System.out.println("分割回文串的结果为：" + new Partition_131().partition(s));
    }

    /**
     * 递归参数和返回值：void backingTracking(String s, int startIndex)
     * 终止条件：starIndex==s.length()
     * 单层递归逻辑：
     *      在for (int i = startIndex; i < s.size(); i++)循环中，我们 定义了起始位置startIndex，那么 [startIndex, i] 就是要截取的子串。
     *      首先判断这个子串是不是回文，如果是回文，就加入在path中，path用来记录切割过的回文子串
     *
     * @param s
     * @return
     */
    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backingTracking(s, 0);
        return result;
    }

    public void backingTracking(String s, int startIndex) {
        //如果起始位置==s的大小，说明找到了一组分割方案
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            boolean flag = isPalindrome(s, startIndex, i);
            if (flag) {
                String str = s.substring(startIndex, i + 1);
                path.add(str);
            }else{
                continue;
            }

            //其实位置后移，保证不重复
            backingTracking(s, i + 1);
            path.remove(path.size() - 1);//回溯
        }
    }

    /**
     * 判断是否是回文串
     * @param s
     * @param startIndex
     * @param end
     * @return
     */
    public static boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
