package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 */
public class MinDepth_111 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("二叉树最小深度为："+minDepth(root));
    }

    /**
     * 最小深度区别与最大深度，主要在左右孩子不为空的逻辑（最大深度就是层值）
     * 1.递归实现
     * 思路：1.左子树为空，右子树不为空，最小深度为1+右子树深度
     *      2.右子树为空，左子树不为空，最小深度为1+左子树深度
     *      3.左右子树都不为空，最小深度为左右子树深度最小值+1
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        // 当一个左子树为空，右不为空，这时并不是最低点
        if (root.left == null && root.right != null) {
            return rightDepth + 1;
        }
        // 当一个右子树为空，左不为空，这时并不是最低点
        if (root.left != null && root.right == null) {
            return leftDepth + 1;
        }
        //左右都不为空，最小深度为左右子树深度最小值+1
        return Math.min(leftDepth, rightDepth) + 1;
    }

    /**
     * 2.迭代法（层次遍历）
     * @param root
     * @return
     */
    public static int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            depth++;
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                //是叶子节点，直接返回depth，因为从上往下遍历，所以该值即为最小值（最早遇到的叶子节点则深度最小）
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }
}
