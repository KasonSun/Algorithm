package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class MaxDepth_104 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("二叉树最大深度为："+maxDepth2(root));
    }

    /**
     * 1.层序迭代实现
     * 思路：二叉树的最大深度即为层数值
     * @param root
     * @return
     */
    public static int maxDepth1(TreeNode root) {
        int maxDepth = 0;
        if (root == null) {
            return maxDepth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            maxDepth++;//当前层遍历完则深度加一
        }
        return maxDepth;
    }

    /**
     * 2.递归实现(实际上是一个后序的遍历过程)
     *      1.确定递归函数的参数和返回值：参数就是传入树的根节点，返回就返回这棵树的深度，所以返回值为int类型
     *      2.确定终止条件：如果为空节点的话，就返回0，表示高度为0
     *      3.确定单层递归的逻辑：先求它的左子树的深度，再求的右子树的深度，最后取左右深度最大的数值 再+1 （加1是因为算上当前中间节点）就是目前节点为根节点的树的深度。
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        return getDepth(root);
    }

    public static int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);
        int depth = 1 + Math.max(leftDepth, rightDepth);
        return depth;
    }

    /**
     * 上面的递归实现可以合并一个函数
     * @param root
     * @return
     */
    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth2(root.left);
        int rightDepth = maxDepth2(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
