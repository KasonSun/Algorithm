package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *  组合问题
 *
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 对应的键位 2-abc, 3-def, 4-ghi, 5-jkl, 6-mno, 7-pqrs, 8-tuv, 9-wxyz
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 */
public class LetterCombinations_17 {
    public static void main(String[] args) {
        String digits = "23";
        System.out.println("该数字字符串对应的字母组合可以为："+new LetterCombinations_17().letterCombinations(digits));
    }

    /**
     * @param digits
     * @return
     */
    String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> result = new ArrayList<>();
    String str = "";
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return result;
        }
        backTracking(digits, 0);
        return result;
    }

    //回溯具体过程
    public void backTracking(String digits, int index) {//index指向数字字符串的数字下标
        if (index == digits.length()) {
            result.add(str);
            return;
        }
        int digit = digits.charAt(index) - '0';//index指向的数字转换为int
        String letters = letterMap[digit];//取index指向数字对应的字符串
        for (int i = 0; i < letters.length(); i++) {
            str += letters.charAt(i);
            backTracking(digits, index + 1);
            str = str.substring(0, str.length() - 1);
        }
    }
}
