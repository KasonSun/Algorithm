package string;

import java.util.Arrays;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 */
public class ReplaceSpace_jianzhiOffer05 {
    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace03(s));
    }
    /**
     * 空格ascall为32
     *      time O(n) space O(n)
     * @param s
     * @return
     */
    public static String replaceSpace01(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                result += "%20";
            }else{
                result += s.charAt(i);
            }
        }
        return result;
    }

    /**
     * StringBuilder
     *      time O(n)  space O()
     * @param s
     * @return
     */
    public static String replaceSpace02(String s) {
        if (s == null) {
            return null;
        }
        //选用 StringBuilder 单线程使用，比较快，选不选都行
        StringBuilder sb = new StringBuilder();
        //使用 sb 逐个复制  ，碰到空格则替换，否则直接复制
        for (int i = 0; i < s.length(); i++) {
            //charAt(i) 为 char 类型，为了比较需要将其转为和 " " 相同的字符串类型
            if (" ".equals(String.valueOf(s.charAt(i)))){//valueOf是String与其他Number子类连接的方法
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 双指针实现
     *      1."We are happy."->"We  are  happy."
     *      2.初始化指针 左指针：指向原始字符串最后一个位置，右指针：指向新字符（新字符串是原字符串与扩展字符串的连接）串最后一个位置
     *      3.
     * @param s
     * @return
     */
    public static String replaceSpace03(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        //扩充空间，空格数量2倍,只是针对空格扩充，一个空格相对于之前则变为三个
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' '){
                str.append("  ");
            }
        }
        //若是没有空格直接返回
        if(str.length() == 0){
            return s;
        }
        //有空格情况 定义两个指针
        int left = s.length() - 1;//左指针：指向原始字符串最后一个位置
//        System.out.println(s.length());//13
        s += str.toString();
//        System.out.println(s.length());17
        int right = s.length()-1;//右指针：指向扩展字符串的最后一个位置
        char[] chars = s.toCharArray();
        while(left>=0){
            if(chars[left] == ' '){
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            }else{
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
//        System.out.println(chars.length);//17
        return new String(chars);
    }
}


    
