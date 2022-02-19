package binarytree;

import java.util.*;

/**
 * 二叉树层次遍历II(自底向上)
 *      给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 */
public class LevelOrderTraversal_107 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("自底向上层序遍历(队列实现)的结果为："+levelOrderBottom(root).toString());
    }

    /**
     * 自底向上实现二叉树层次遍历(只需要将正常二叉树层次遍历的结果列表进行逆置)
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);//根节点先加入队列
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();//用来存放每一层的节点值的列表（每次while会重新赋值）
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        Collections.reverse(result);//原地逆置
        return result;
    }
}
