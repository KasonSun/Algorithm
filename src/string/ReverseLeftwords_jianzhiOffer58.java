package string;

import java.lang.invoke.StringConcatFactory;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 输入: s = "abcdefg", k = 2
 * 输出:"cdefgab"
 *
 * 输入: s = "lrloseumgh", k = 6
 * 输出:"umghlrlose"
 */
public class ReverseLeftwords_jianzhiOffer58 {
    public static void main(String[] args) {
        String s = "lrloseumgh";
        int k = 6;
        System.out.println(reverseLeftWords(s, k));
    }

    /**
     * 思路：转换为字符数组操作
     *
     * @param s
     * @param k
     * @return
     */
    public static String reverseLeftWords(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = k; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        sb.append(s.substring(0, k));

        return sb.toString();
    }
}
