package stackqueue;

import java.util.Stack;

/**
 *请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *      实现 MyQueue 类：
 *      void push(int x) 将元素 x 推到队列的末尾
 *      int pop() 从队列的开头移除并返回元素
 *      int peek() 返回队列开头的元素
 *      boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * 说明
 * 你只能使用标准的栈操作 —— 也就是只有push to top,peek/pop from top,size, 和is empty操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *
 * 进阶：你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 *
 * 输入：
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 1, 1, false]
 *
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 */
public class StackToQueue_232 {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        int a = myQueue.peek();
        int b = myQueue.pop();
        int c = myQueue.pop();
        boolean bool = myQueue.empty();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(bool);
    }

}

/**
 * 使用栈实现队列，具体要求看上面
 */
class MyQueue{
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue(){
        stack1 = new Stack<>();//负责进栈
        stack2 = new Stack<>();//负责出栈
    }

    /**
     * 元素入队
     * @param x
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * 元素出队
     * @return
     */
    public int pop(){
        dumpStack1();
        return stack2.pop();
    }

    /**
     * 返回队列最前面元素
     */
    public int peek(){
        dumpStack1();
        return stack2.peek();
    }
    /**
     * 队列是否为空
     * @return
     */
    public boolean empty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }

    /**
     * 栈模拟队列出队操作核心
     */
    private void dumpStack1(){
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }
}
