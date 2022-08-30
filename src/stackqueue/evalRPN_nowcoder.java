package stackqueue;
import java.util.*;

public class evalRPN_nowcoder {
    public static void main(String[] args) {
//        String inStr = "(100+100)-100*100";
        String inStr = "3/2";
        System.out.println(Arrays.toString(toStringArray(inStr)));
        System.out.println(Arrays.toString(intoPost(inStr)));
        System.out.println(new evalRPN_nowcoder().solve(inStr));
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回表达式的值
     * @param s string字符串 待计算的表达式
     * @return int整型
     */
    public int solve (String s) {
        String[] tokens = intoPost(s);
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

    public static String[] intoPost(String inFix){
        String[] inFixArray = toStringArray(inFix);
        List<String> list = new ArrayList<>();//存放后缀结果
        Stack<String> stack = new Stack<String>();//存放运算符的栈
        for (int i = 0; i < inFixArray.length; i++) {
            String element = inFixArray[i];
            switch (element) {
                case "+":
                case "-":
                    //不是空、不是(
                    //弹出元素，直到遇到优先级更低，或者栈为空（"("只会遇到")"弹出），故+、-一直弹出直到"("或者为空
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        list.add(stack.pop());
                    }
                    stack.push(element);
                    break;
                case "*":
                case "/":
                    //不是+、-、(、不是空，或者不为空、（是* 或 是/）
                    while (!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("+") && !stack.peek().equals("-")) {
                        list.add(stack.pop());
                    }
                    stack.push(element);
                    break;
                case "(":
                    stack.push(element);
                    break;
                case ")":
                    String out = stack.pop();
                    while (!out.equals("(")) {
                        list.add(out);
                        out = stack.pop();
                    }
                    break;
                //数字，直接拿出
                default:
                    list.add(element);
                    break;
            }
        }
        //最后把所以运算符出栈
        while (!stack.isEmpty()) {
           list.add(stack.pop());
        }
        String[] result=list.toArray(new String[list.size()]);
        return result;
    }
    public static String[] toStringArray(String str){
        List<String> list=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        int cur=0;
        while(cur<str.length()){
            while(cur<str.length() && str.charAt(cur)>='0' && str.charAt(cur)<='9'){
                sb.append(str.charAt(cur++) + "");
            }
            if (sb.length() > 0) {
                list.add(sb.toString());
                sb = new StringBuilder();
            }
            if (cur < str.length() && (str.charAt(cur) < '0' || str.charAt(cur) > '9')) {
                list.add(str.charAt(cur) + "");
            }
            cur++;
        }
        String[] result=list.toArray(new String[list.size()]);
        return result;
    }
}