package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */
public class NTreePreOrder_589 {
    public static void main(String[] args) {

    }

    /**
     * 思路：递归实现
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversal(NTreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    public static void preOrder(NTreeNode root, List<Integer> result) {
        if (root == null) {
            return ;
        }
        result.add(root.val);
        for (NTreeNode child : root.children) {
            preOrder(child, result);
        }

    }

    /**
     * 迭代法 入栈：中-右-左 出栈：中-左-右（注意中间节点会先处理）
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversal1(NTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<NTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            NTreeNode node = stack.pop();
            result.add(node.val);
            //注意逆序入栈，出栈才符合前序规则
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }
        return result;
    }
}
