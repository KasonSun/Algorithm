package stackqueue;

import java.util.Stack;

/***
 * 根据 逆波兰表达式（后缀表达式）表示法，求表达式的值。
 * 有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 *
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：
 * 该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 */
public class evalRPN_150 {
    public static void main(String[] args) {
        String[] tokens={"2","1","+","3","*"};
        System.out.println(evalRPN(tokens));

        String inStr = "3+(2-5)*6/3";
        String inStr1 = "100+100";
        String[] inStrArray = {"3", "2", "5", "-", "6", "*", "3", "/", "+"};
        String[] inStrArray1 = {"100", "100","+","100","100","*","-"};
        System.out.println(evalRPN(inStrArray1));
    }

    /**
     * 逆波兰表达式计算
     * 思路：（前提：表达式总会得出有效数值且不存在除数为 0 的情况）
     *      1.非运算符号压入栈
     *      2.遇到运算符号则出栈两个元素进行运算，运算结果压入栈
     *      3.最后返回栈中的最终结果
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String str = tokens[i];
            if (!str.equals("+") && !str.equals("-") && !str.equals("*") && !str.equals("/")) {//== 在leetcode 提交不通过，jdk版本问题
                stack.push(Integer.parseInt(str));
                continue;
            }else{
                int b = stack.pop();
                int a = stack.pop();//注意此处的前后位置会影响到结果
                int c = 0;
                if (str.equals("+")) {
                    c = a + b;
                }
                if (str.equals("-")){
                    c = a - b;
                }
                if (str.equals("*")) {
                    c = a * b;
                }
                if (str.equals("/")) {
                    c = a / b;
                }
                stack.push(c);
            }

        }
        return stack.pop();
    }
}
