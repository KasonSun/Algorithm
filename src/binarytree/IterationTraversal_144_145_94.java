package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树迭代遍历
 */
public class IterationTraversal_144_145_94 {
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
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();//中
            result.add(node.val);
            if (node.right != null) {//右
                stack.push(node.right);
            }
            if (node.left != null) {//左
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 中序迭代遍历（中序迭代区别与前后序遍历）   中序顺序：左-中-右  入栈顺序：左-右
     * @param root
     * @return
     */
    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        //从根开始直到左子树为空（期间的遍历的节点入栈），开始出栈，访问，再遍历右子树
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur=cur.left;//左
            }else{
                cur = stack.pop();//中
                result.add(cur.val);

                cur = cur.right;//右
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
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();//中（此时已经出来了）
            result.add(node.val);
            if (node.left != null) {//左
                stack.push(node.left);
            }
            if (node.right != null) {//右
                stack.push(node.right);
            }
        }
        Collections.reverse(result);//最后翻转结果
        return result;
    }
}
