package binarytree;


import java.util.LinkedList;
import java.util.Queue;

/**
 *  翻转一棵二叉树。
 */
public class InvertTree_226 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        invertTree1(root);
        System.out.println(LevelOrderTraversal_102.levelOrder(root));
    }

    /**
     * 1.层序遍历实现
     * 思路：对于层序遍历的每一个结点将其左右孩子节点进行交换
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                //左右孩子进行交换
                swapChildren(node);

                //层序遍历拓展下层节点
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 2.递归实现
     * 前后序遍历都可以
     * 中序不行，因为先左孩子交换孩子，再根交换孩子（做完后，右孩子已经变成了原来的左孩子），再右孩子交换孩子（此时其实是对原来的左孩子做交换）
     */
    public static TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        //后序实现
        invertTree(root.left);
        invertTree(root.right);
        swapChildren(root);
        return root;
    }

    private static void swapChildren(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
}
