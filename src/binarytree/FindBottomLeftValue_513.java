package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 */
public class FindBottomLeftValue_513 {
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("二叉树的最底层最左边节点的值："+findBottomLeftValue(root));
    }

    /**
     * 1.迭代实现（层序）
     * 思路：只需要记录最后一行第一个节点值
     * @param root
     * @return
     */
    public static int findBottomLeftValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (i == 0) {
                    res = poll.val;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return res;
    }

    /**
     * 2.递归实现（前序）
     *      深度最大的叶子节点一定是最后一行
     *      关于递归返回值：如果需要遍历整颗树，递归函数就不能有返回值。如果需要遍历某一条固定路线，递归函数就一定要有返回值！
     *      本题我们是要遍历整个树找到最深的叶子节点，需要遍历整颗树，所以递归函数没有返回值。
     * @param root
     * @return
     */
    int Deep = -1;// 全局变量 记录最大深度
    int value = 0;// 全局变量 最大深度最左节点的数值
    public int findBottomLeftValue1(TreeNode root) {
        value = root.val;
        findLeftValue(root,0);
        return value;
    }

    public void findLeftValue (TreeNode root,int deep) {
        if (root == null) return;
        if (root.left == null && root.right == null) {//当遇到叶子节点的时候，就需要统计一下最大的深度了，所以需要遇到叶子节点来更新最大深度（深度最大的叶子节点一定是最后一行）
            if (deep > Deep) {//这个条件加上前序遍历决定了同样深度的一层只会保留最左边的第一个节点值
                value = root.val;
                Deep = deep;
            }
        }
        if (root.left != null) findLeftValue(root.left,deep + 1);//隐藏着回溯
        if (root.right != null) findLeftValue(root.right,deep + 1);//隐藏着回溯
    }
}
