package string;

import java.util.Arrays;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
public class ReverseString_344 {
    public static void main(String[] args) {
        char[] s={'h','e','l','l','o'};
        System.out.println(Arrays.toString(reverseString(s)));
    }
    /**
     * 使用双指针法
     * @param s
     * @return
     */
    public static char[] reverseString(char[] s){
        int left=0;
        int right = s.length-1;
        while(left<right){
            char c = s[left];
            s[left++] = s[right];
            s[right--] = c;
        }
        return s;
    }
}
