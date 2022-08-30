package backtracking;

import java.util.ArrayList;

/**
 * BM58 字符串的排列
 *   描述
 * 输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
 * 例如输入字符串ABC,则输出由字符A,B,C所能排列出来的所有字符串ABC,ACB,BAC,BCA,CBA和CAB。
 */
public class StringPermutation_NC_BM_58 {
    public static void main(String[] args) {
        String str = "ABC";
        System.out.println(new StringPermutation_NC_BM_58().permutation(str).toString());
    }
    ArrayList<String> result=new ArrayList<>();
    ArrayList<Character> path=new ArrayList<>();
    public ArrayList<String> permutation(String str) {
        char[] chArr=str.toCharArray();
        int[] used = new int[str.length()];
        backTracking(chArr,used);
        return result;
    }

    public void backTracking(char[] chArr, int[] used) {
        if (path.size() == chArr.length) {
            StringBuffer sb = new StringBuffer();
            for (char c : path) {
                sb.append(c);
            }
            result.add(sb.toString());
            return;
        }
        for (int i = 0; i < chArr.length; i++) {
            if(used[i]==1){
                continue;
            }
            path.add(chArr[i]);
            used[i] = 1;
            backTracking(chArr, used);
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }
}
