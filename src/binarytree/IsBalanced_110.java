package binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 *                          一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 *  注意区别：二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数（可以从上到下去查，可以前序（注意此时的前序是真正的逻辑，深度还有一些其他的特殊方法），中左右）。
 *          二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数。（只能从下到上去查，只能后序，左右中）
 */
public class IsBalanced_110 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
//        TreeNode node4 = new TreeNode(5);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
//        node3.left = node4;

        System.out.println("二叉树最大深度为："+isBalanced(root));
    }

    /**
     * 实现较为复杂(还没有真正理解)
     * 1.迭代实现（分别求每个节点的左右子树高度，进行差比较）
     *      计算高度时，会重复遍历，效率低，O（n^2）
     *
     *      优化迭代法，针对暴力迭代法的getHeight方法做优化，利用TreeNode.val来保存当前结点的高度，这样就不会有重复遍历
     *      获取高度算法时间复杂度可以降到O(1)，总的时间复杂度降为O(n)。
     *      时间复杂度：O(n)
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        //模拟前序遍历的过程
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode inNode = stack.peek();
            //右节点为空或者已经遍历过
            if (inNode.right == null || inNode.right == pre) {
                if (Math.abs(height(inNode.left) - height(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;//当前节点下，没有需要遍历的节点了
            }else{
                root = inNode.right;//右节点还没遍历
            }
        }
        return true;
    }
    //利用节点value值保存当前节点的高度
    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //下面实际上是一个后序的过程
        int leftHeight = root.left != null ? root.left.val : 0;
        int rightHeight = root.right != null ? root.right.val : 0;
        int height = Math.max(leftHeight, rightHeight) + 1;
        root.val = height;
        return height;
    }

    /**
     * 2.递归实现,O(n)
     * @param root
     * @return
     */
    public static boolean isBalanced1(TreeNode root) {
        return height1(root) != -1;
    }

    public static int height1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height1(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = height1(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        //左右子树高度差大于1，return -1 表示已经不是平衡二叉树了
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 获取高度（层序实现）
     * node节点的最大深度就是node的高度
     * @param node
     * @return
     */
    public static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 0;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            depth++;
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return depth;
    }

    /**
     * 获取高度（递归实现）
     * @param node
     * @return
     */
    public static int getHeight1(TreeNode node) {
        if (node == null) {
            return 0;
        }else{
            return Math.max(getHeight1(node.left), getHeight1(node.right))+1;
        }
    }
}
