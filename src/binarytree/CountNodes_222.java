package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~2^h个节点。
 *
 */
public class CountNodes_222 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node1.left = node3;

        System.out.println("完全二叉树节点数量为："+countNodes1(root));
    }

    /**
     * 1.迭代实现（层序遍历）
     *      思路：只需要遍历过程记录节点数量就行
     * @param root
     * @return
     */
    public static int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                count++;//区别与高度的depth++的位置（depth在for循环外面 ，while内）
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return count;
    }

    /**
     * 2.递归实现（后序）
     *      1.确定递归函数的参数和返回值：参数就是传入树的根节点，返回就返回以该节点为根节点二叉树的节点数量，所以返回值为int类型。
     *      2.确定终止条件：如果为空节点的话，就返回0，表示节点数为0。
     *      3.确定单层递归的逻辑：先求它的左子树的节点数量，再求的右子树的节点数量，最后取总和再加一（加1是因为算上当前中间节点）就是目前节点为根节点的节点数量。
     * @param root
     * @return
     */
    public static int countNodes2(TreeNode root) {
        return getNodesNum(root);
    }

    public static int getNodesNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftNum = getNodesNum(node.left);
        int rightNum = getNodesNum(node.right);
        int treeNum = leftNum + rightNum + 1;
        return treeNum;
    }

    /**
     * 递归简化写法
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
