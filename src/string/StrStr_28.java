package string;

/**
 * 实现strStr()函数。
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * 说明：当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 * 输入：haystack = "", needle = ""
 * 输出：0
 *
 */
public class StrStr_28 {
    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
        System.out.println(strStr1(haystack,needle));
        System.out.println(strStr2(haystack,needle));
        System.out.println(strStr3(haystack,needle));
    }
    /**
     * 一、基于滑动窗口实现
     *
     *      1.找到首字母相等
     *      2.找到后续字符，并计数是否等于needle的长度（不等则进入下一轮的循环）
     *
     * O(m*n)
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr1(String haystack, String needle) {
        int m = needle.length();
        // 当 needle 是空字符串时我们应当返回 0
        if (m == 0) {
            return 0;
        }
        int n = haystack.length();
        if (n < m) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < n - m + 1) {
            // 找到首字母相等
            while (i < n && haystack.charAt(i) != needle.charAt(j)) {
                i++;
            }
            if (i == n) {// 没有首字母相等的
                return -1;
            }
            // 遍历后续字符，判断是否相等
            i++;
            j++;
            while (i < n && j < m && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {// 找到
                return i - j;
            } else {// 未找到
                i=i-j+1;//未找到则从第一次匹配的首字母相同的位置的后面开始匹配
                j = 0;
            }
        }
        return -1;
    }

    /**
     * 二、KMP实现，next数组（前缀表）
     * O(m+n)
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr2(String haystack, String needle) {
        if(needle.length()==0){
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = 0;
        for(int i = 0; i<haystack.length();i++){
            while(j>0 && haystack.charAt(i) != needle.charAt(j)){
                j = next[j-1];
            }
            if(haystack.charAt(i)==needle.charAt(j)){
                j++;
            }
            if(j==needle.length()){
                return (i-needle.length()+1);
            }
        }

        return -1;
    }

    /**
     * next[]数组
     *      可以尝试对“aabaaf”串进行模拟
     *      getNext的功能此时与needle无关，只是单纯在求解前缀表
     *      1.初始化 next数组记录起始位置j=0，i=0(j指向前缀末尾的位置，i指向后缀末尾的位置，j实际也代表i（包括i）前面最长相等前后缀的长度)
     *      2.如果不相同，j就要从next[j-1]数组的值即为下一个匹配的位置。
     *      3.如果相同，那么i 和 j 同时向后移动，更新next数组
     * @param next
     * @param s
     */
//    public static void getNext(int[] next, String s){
//        int j = 0;
//        next[0] = 0;
//        for (int i = 1; i<s.length(); i++){
//            while(j>0 && s.charAt(i) != s.charAt(j)){//不等
//                j=next[j-1];
//            }
//
//            if(s.charAt(i)==s.charAt(j)){//相等
//                j++;
//            }
//            next[i] = j;//更新next，j实际也代表i（包括i）前面字串最长相等前后缀的长度
//        }
//    }

    /**
     *注意：next数组是针对子串的，next数组是针对子串的，next数组是针对子串的
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr3(String haystack, String needle){
        if (needle.length() == 0) {
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {//此处长度等于模式串的长度时，进行返回，返回的为字符串起始匹配的位置
                return (i - next.length + 1);
            }
        }
        return -1;
    }

    /***
     * 获取next数组
     *      1.初始化next数组，i，j
     *      2.匹配不相等，获取next[j-1]的值，找到下一次匹配的位置
     *      3.匹配相等，j继续向前移动（实际代表此时位置i（包括i）之前的最长相等前后缀变长）， 更新next数组
     * @param next
     * @param s
     * @return
     */
    public static void getNext(int[] next, String s) {
        int j = 0;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {//初始0位置，一个字符无最长前后缀，next[0]=0,i用来更新next，故next从1开始
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;//最长相等前后缀的长度+1
            }
            next[i] = j;//更新next数组
        }
    }
}
