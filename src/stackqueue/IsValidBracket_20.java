package stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 */
public class IsValidBracket_20 {
    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid2(s));
    }

    /**
     * 使用栈实现
     * 思路：
     *      1.遇到三种括号的左边则入栈1
     *      2.遇到三种括号的右边出栈匹配
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        //长度为奇数，直接返回false
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i<s.length()) {
            char c = s.charAt(i++);
//            System.out.println(c);
            switch (c){
                case '(':
                    stack.push(c);
                    break;
                case '[':
                    stack.push(c);
                    break;
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
            }
        }

        if (stack.isEmpty()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 使用队列实现栈，再进行匹配
     * @param s
     * @return
     */
    public static boolean isValid1(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                deque.push(')');
            }else if (ch == '{') {
                deque.push('}');
            }else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            } else {//如果是右括号判断是否和栈顶元素匹配，也即为deque.peek() == ch的情况
                deque.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }

    /**
     * 栈实现简化
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.peek() != c) {
                return false;
            }else{
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
