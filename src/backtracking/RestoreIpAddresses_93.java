package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割问题
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入'.' 来形成。
 * 你不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 */
public class RestoreIpAddresses_93 {
    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println("复原后的IP地址可能为："+new RestoreIpAddresses_93().restoreIpAddresses(s));
    }

    /**
     * 递归参数和返回值：void backTracking(String s, int startIndex, int pointNum) pointNum记录点的数量
     * 终止条件：pointNum==3说明字符串分为4段，验证第四段是否合法，如果合法就加入到结果集中
     * 单层递归逻辑：循环中截取[startIndex, i]这个区间就是截取的子串，需要判断这个子串是否合法，如果合法就在字符串后面加上符号.表示已经分割，如果不合法就结束本层循环，剪掉分支
     *
     * @param s
     * @return
     */
    List<String> result = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {//初始剪枝
            return result;
        }
        backTracking(s,0,0);
        return result;
    }
    //startIndex:搜索的起始位置，pointNum：添加逗点的数量
    public void backTracking(String s, int startIndex, int pointNum) {
        if (pointNum == 3) {//逗点为3，分割结束
            //判断第四顿字符串是否合法，合法就放进result
            if (isValid(s, startIndex, s.length() - 1)) {
                result.add(s);
            }
            return ;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                //public String substring(int beginIndex)
                //返回一个字符串,这个字符串的子字符串。指定子字符串开头的字符索引beginIndex和延伸到这个字符串的结束。
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);//在str的后面插入.
                pointNum++;
                backTracking(s, i + 2, pointNum);//插入逗点之后下一个子串的起始位置为i+2
                pointNum--;//回溯
                s = s.substring(0, i + 1) + s.substring(i + 2);//回溯删掉逗点(i+2是因为加入.字符串长度加一)
            }else{
                break;
            }
        }
    }

    /**
     * 判断字符串在左闭右闭[start, end]所组成的数字是否合法
     * @param s
     * @param start
     * @param end
     * @return
     */
    public static boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) {//0开头的数字不合法(单个0是合法的)
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i ++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {//遇到不合法的非数字字符
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) {//大于255不合法
                return false;
            }
        }
        return true;
    }
}
