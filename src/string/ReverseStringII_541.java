package string;

import java.util.Arrays;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 *
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 */
public class ReverseStringII_541 {
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseStringII(s,k));
    }
    /**
     *我们直接按题意进行模拟：反转每个下标从 2k 的倍数开始的，长度为 k 的子串。若该子串长度不足 k，则反转整个子串。
     * @param s
     * @param k
     * @return
     */
    public static String reverseStringII(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse01(arr, i, Math.min(i + k, n) - 1);//0-k是k+1个，故需要-1
        }
        return new String(arr);//传入字符数组构造字符串
    }

    public static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void reverse01(char[] arr, int left, int right){
        for (; left < right; left++, right--) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }
}
