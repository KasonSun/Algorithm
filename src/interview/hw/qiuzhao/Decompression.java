package interview.hw.qiuzhao;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * 秋招一面手撕
 * 解压报文
 * 1）原始报文长度不会超过1000，不考虑异常的情况
 * 示例1
 * 输入
 * 3[k]2[mn]
 * 输出
 * kkkmnmn
 * 说明
 * k 重复3次，mn 重复2次，最终得到 kkkmnmn
 * 示例2
 * 输入
 * 3[m2[c]]
 * 输出
 * mccmccmcc
 * 说明
 * m2[c] 解压缩后为 mcc，重复三次为 mccmccmcc”
 */
public class Decompression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        decompression(str);
    }

    /**
     * 从后往前遍历，遇到字母直接放到栈中，遇到数字则把所有数字取出来以后，再解压栈中字母
     * 根据[]括号解压
     * 其中需要注意的是解压完一定要将解压的字符串放回栈中，这样对于嵌套压缩串的处理才有效
     *
     * @param str
     */
    public static void decompression(String str) {
        Stack<String> stack = new Stack<>();
        int index = str.length() - 1;
        while (index >= 0) {
            if (str.charAt(index)<'0' || str.charAt(index)>'9') {//非数字入栈
                stack.push(str.charAt(index) + "");
                index--;
            }else{//遇到数字解压
                String num = "";
                String res = "";
                //循环将数字组合，因为有可能数字是多位的值而不是仅仅只有一位
                while (index >= 0 && (str.charAt(index) > '0' && str.charAt(index) < '9')) {
                    num = str.charAt(index) + num;
                    index--;
                }
                //此时上面while循环出来index指向的值一定不是数字字符，此时栈顶一定是做括号[
                while (!stack.peek().equals("]")) {
                    String tmp = stack.pop();
                    if (!tmp.equals("[")) {//取出[]之间的字符串
                        res += tmp;
                    }
                }
                //此时上面的while循环出来，栈顶一定是右括号]
                stack.pop();
                StringBuilder sb = new StringBuilder();
                //重复num次
                for (int i = 0; i < Integer.valueOf(num); i++) {
                    sb.append(res);
                }
                stack.push(sb.toString());//重点，一定要将解压的放回去，对于第二个嵌套的输入才会有正确的输出
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}
/*
python:
def decompression(s):
    stack=[]
    length=len(s)
    index=length-1
    while index>=0:
        if not s[index].isdigit():
            stack.append(s[index])
            index-=1
        else:
            num=""
            res=""
            while index>=0 and s[index].isdigit():
                num=s[index]+num
                index-=1
            while stack[-1]!="]":
                temp=stack.pop()
                if temp!="[":
                    res+=temp
            stack.pop()
            res=int(num)*res
            stack.append(res)
    stack.reverse()
    print("".join([item for item in stack if item not in "[]"]))

s="3[k]2[mn]"
s1="3[m12[c]]"
decompression(s1)
 */