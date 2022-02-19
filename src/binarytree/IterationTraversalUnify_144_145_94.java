package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树迭代遍历
 */
public class IterationTraversalUnify_144_145_94 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        List<Integer> result = new ArrayList<>();

        //前序遍历
        result=preOrderTraversal(root);
        System.out.println("前序遍历为："+result.toString());

        //中序遍历
        result = inOrderTraversal(root);
        System.out.println("中序遍历为："+result.toString());

        //后序遍历
        result = postOrderTraversal(root);
        System.out.println("后序遍历为："+result.toString());
    }

    /**
     * 前序迭代遍历 使用栈实现   前序顺序：中-左-右  入栈顺序：中-右-左 出栈顺序：中-左-右
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root!=null){
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();// 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right != null) stack.push(node.right);// 添加右节点（空节点不入栈）
                if (node.left != null) stack.push(node.left);// 添加左节点（空节点不入栈）
                stack.push(node);// 添加中节点
                stack.push(null);// 中节点访问过，但是还没有处理，加入空节点做为标记。
            }else{// 只有遇到空节点的时候，才将下一个节点放进结果集
                stack.pop();// 将空节点弹出
                node = stack.peek(); // 重新取出栈中元素
                stack.pop();//处理完弹出
                result.add(node.val);
            }
        }
        return result;
    }

    /**
     * 中序迭代遍历   中序顺序：左-中-右  入栈顺序：左-右
     * @param root
     * @return
     */
    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root!=null){
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();
                if (node.right != null) stack.push(node.right);
                stack.push(node);
                stack.push(null);
                if (node.left != null) stack.push(node.left);
            }else{
                stack.pop();
                node = stack.peek();
                stack.pop();
                result.add(node.val);
            }
        }
        return result;
    }

    /**
     * 后序迭代遍历  后序顺序：左-右-中  入栈顺序：中-左-右  出栈顺序：中-右-左  最后翻转结果
     * @param root
     * @return
     */
    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root!=null){
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();
                stack.push(node);
                stack.push(null);
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }else{
                stack.pop();
                node = stack.peek();
                stack.pop();
                result.add(node.val);
            }
        }
        return result;
    }
}
