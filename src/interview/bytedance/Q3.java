package interview.bytedance;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 给你一个字符串，你可以将其中的一些字符更改，这样的操作可以执行m次，且只能在给定可以操作的位置进行操作
 * 第一个字符串是操作的字符串的长度n和最多操作次数m
 * 第二个字符串是你允许操作的字符串str
 * 第三个是长度为n的01字符串，'0'当前位置无法修改，'1'表示可以修改
 * 问在给定的操作下，最长连续且相同的字符串
 *
 * 把26类分开讨论，滑动窗口的形式去搞这个东西
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < t; i++) {
            String[] line1 = sc.nextLine().split(" ");
            int n = Integer.valueOf(line1[0]);
            int m = Integer.valueOf(line1[1]);
            String opStr = sc.nextLine();
            String ozStr = sc.nextLine();
            int result = 0;
            //按照a，z进行替换
            for (int j = 'a'; j <= 'z'; j++) {
                char[] chars = opStr.toCharArray();
                //将所有可以替换的位置替换为当次循环的字符
                for (int k = 0; k < chars.length; k++) {
                    if (ozStr.charAt(k) == '1') {
                        chars[k] = (char) j;
                    }
                }

                Queue<Integer> queue = new LinkedList<>();
                int count = 0;//count用来统计替换次数
                //遍历当前替换后的字符串数组，统计由香啊用字符组成的子串的最大长度
                for (int k = 0; k < chars.length; k++) {
                    //如果当前字符不是当次循环进行替换的字符，则将count清零，队列清空，表示字符串断了，需要重新进行统计，进行一下次循环
                    if (chars[k] != j) {
                        count = 0;
                        while(!queue.isEmpty()) queue.poll();
                        continue;
                    }
                    if (opStr.charAt(k) != j) {
                        count++;
                        queue.offer(k);
                    }
                    while (count > m) {
                        if (opStr.charAt(queue.peek()) != j) {
                            count--;
                        }
                        queue.poll();
                    }
                    result = Math.max(result, queue.size());
                }
            }
            System.out.println(result);
        }
    }
}
