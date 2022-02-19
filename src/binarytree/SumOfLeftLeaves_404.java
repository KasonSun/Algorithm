package binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 计算给定二叉树的所有左叶子之和
 * 左叶子定义：如果左节点不为空，且左节点没有左右孩子，那么这个节点就是左叶子
 */
public class SumOfLeftLeaves_404 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("该二叉树的左叶子节点数的和为："+sumOfLeftLeaves(root));
    }
    /**
     * 1.迭代法实现（层序）
     * 思路:平时我们解二叉树的题目时，已经习惯了通过节点的左右孩子判断本节点的属性，而本题我们要通过节点的父节点判断本节点的属性
     *      核心判断条件：root.left != null && root.left.left == null && root.left.right == null
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    if (node.left.left == null && node.left.right == null) {
                        sum += node.left.val;
                    }
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }

    /**
     * 2.递归实现（后序，因为要通过递归函数的返回值来累加求取左叶子数值之和）
     *          确定递归参数与返回值：根节点，返回值为数值之和
     *          终止条件：if(root==null) return 0;
     *          单层递归逻辑：当遇到左叶子节点的时候，则记录数值，然后通过递归求取左子树左叶子之和，右子树左叶子之和，两部分相加即为二叉树的左叶子之和
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);//left
        int rightValue = sumOfLeftLeaves(root.right);//right
                                                        //mid
        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        int sum = midValue + leftValue + rightValue;
        return sum;
    }

    /**
     * 3.迭代法实现（前序，前中后序都可以）
     * 思路:平时我们解二叉树的题目时，已经习惯了通过节点的左右孩子判断本节点的属性，而本题我们要通过节点的父节点判断本节点的属性
     *      核心判断条件：root.left != null && root.left.left == null && root.left.right == null
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && node.left.left == null && node.left.right == null) {
                sum += node.left.val;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return sum;
    }
}
