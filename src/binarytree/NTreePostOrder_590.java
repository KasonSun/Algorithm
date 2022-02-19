package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的 后序遍历 .
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */
public class NTreePostOrder_590 {
    public static void main(String[] args){}

    /**
     * 递归实现
     *
     * @return
     */
    public static List<Integer> postOrderTraversal(NTreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    public static void postOrder(NTreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        for (NTreeNode child : root.children) {
            postOrder(child,result);
        }
        result.add(root.val);
    }

    /**
     * 迭代实现 (入栈：中-左-右  出栈：中-右-左  翻转)（注意中间节点会先处理）
     * @param root
     * @return
     */
    public static List<Integer> postOrderTraversal1(NTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<NTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            NTreeNode node = stack.pop();
            result.add(node.val);
            for (int i = 0; i < node.children.size(); i++) {
                stack.push(node.children.get(i));
            }
        }
        Collections.reverse(result);//翻转
        return result;
    }
}
