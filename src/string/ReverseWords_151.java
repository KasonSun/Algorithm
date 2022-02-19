package string;

import java.lang.invoke.StringConcatFactory;

/**
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * 说明：
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 *
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 输入：s = " hello world "
 * 输出："world hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 *
 * 输入：s = "a good example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
 *
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 *
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 */
public class ReverseWords_151 {
    public static void main(String[] args) {
        String s = "Alice does not even like bob";
        System.out.println(reverseWords(s));
    }
    /**
     *
     * 双指针法
     *  1.移除多余空格
     *  2.将整个字符串反转
     *  3.将每个单词反转
     *      举个例子，源字符串为：" the sky is blue "
     *              移除多余空格 : "the sky is blue"
     *              字符串反转："eulb si yks eht"
     *              单词反转："blue is sky the"
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        //1.去除首尾以及中间的多余空格
        StringBuilder sb = removeSpace(s);
        //2.反转整个字符串
        reverseString(sb, 0, sb.length()-1);
        //3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    /**
     * 1.去除首尾以及中间的多余空格
     * @param s
     * @return
     */
    public static StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        //去除首尾的空格
        while(s.charAt(start) == ' ') start++;
        while(s.charAt(end)==' ') end--;
        //创建sb用来接收处理后去除空格的字符串
        StringBuilder sb = new StringBuilder();
        //去除中间的空格
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1)!=' ') {//**很巧妙sb.charAt(sb.length() - 1)!=' '将中间多余的空格去除，只留下一个空格（此时是用sb）
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    /**
     * 2.将整个字符串反转
     * @param sb
     * @param start
     * @param end
     */
    public static void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            //完成字符的交换操作
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));//StringBuilder的方法，setCharAt（index，ch）使用ch替代index指向的字符（串）
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    /**
     * 3.单词反转
     * @param sb
     */
    public static void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        while (start < sb.length()) {//两个循环确定单个单词进行反转的start和end位置
            while (end < sb.length() && sb.charAt(end) != ' ') {
                end++;
            }
            //出循环end刚好指向单词后面的空格，故此时需要-1
            reverseString(sb, start, end - 1);
            //下一轮循环的起止位置
            start = end + 1;
            end = start + 1;
        }
    }
}
