package hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *  （三种方法实现， 第三种滑动窗口效率比较好）
 */
public class FindAnagrams_438 {
    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        List<Integer> list = new ArrayList<>();
        list = findAnagrams03(s, p);
        System.out.println(list.toString());

    }

    /**
     * 方法一：1148ms
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams01(String s, String p) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i + p.length() <= s.length(); i++) {
            String str = s.substring(i, i + p.length());
            if (isAnagram(str, p)) {
                list.add(i);
                continue;
            }
        }
        return list;
    }

    /**
     * 数组实现（编译通过）
     * 判断是否为异位词
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        for (char c : s.toCharArray()) {
            record[c - 'a'] += 1;//’a‘是65，实际上就是将26个字母值准换为0-26之间的值，方便计算
        }
        for (char c : t.toCharArray()) {
            record[c - 'a'] -= 1;
        }
        for (int i : record) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二：利用java工具包排序实现(2501ms)
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams02(String s, String p) {
        List<Integer> list = new ArrayList<>();
        char[] pch = p.toCharArray();
        Arrays.sort(pch);
        for (int i = 0; i + p.length() <= s.length(); i++) {
            String str = s.substring(i, i + p.length());
            char[] sch = str.toCharArray();
            Arrays.sort(sch);
            if (String.valueOf(pch).equals(String.valueOf(sch))) {//不能直接比较，需要转回字符串
                list.add(i);
                continue;
            }
        }
        return list;
    }

    /**
     * 方法三：使用滑动窗口实现（40ms）
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams03(String s, String p) {
        int start = 0, left = 0, right= 0;
        int match = 0;

        List<Integer> res = new ArrayList<>();

        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> needs  = new HashMap<>();

        for(char c : p.toCharArray())
            needs.put(c, needs.getOrDefault(c, 0) + 1);

        while(right < s.length())
        {
            //---------rp to move-------------------
            char c1 = s.charAt(right);
            if(needs.containsKey(c1) )
            {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if(window.get(c1).equals(needs.get(c1)))
                    match++;
            }
            else
            {
                left = right+1;
                window = new HashMap<>();
                match = 0;
            }
            right++;


            //---------lp to move-------------------
            while(match == needs.size())
            {
                start = left;
                if(window.equals(needs))    res.add(start);

                char c2 = s.charAt(left);
                if(window.containsKey(c2))
                {
                    window.put(c2, window.get(c2) - 1);
                    if(window.get(c2) < (needs.get(c2)))//有可能存在window中某个字符的个数大于实际needs中的个数
                        match--;
                }

                left++;
            }
        }
        return res;
    }

}
