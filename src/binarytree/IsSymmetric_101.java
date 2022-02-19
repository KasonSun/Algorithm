package binarytree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
public class IsSymmetric_101 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
//        node2.left = node3;

        System.out.println(isSymmetric2(root));
    }

    /**
     * 1.递归法实现(相对更快)
     * 思路：需要遍历左右子树 内侧 和 外侧 节点，一个树遍历顺序是左右中，则另一个树的顺序为右左中
     * 情况：1.左子树存在，右子树不存在
     *      2.左子树不存在， 右子树存在
     *      3.左右子树都不存在
     *      4.左右子树都存在，比较值是否相等
     * @param root
     * @return
     */
    public static boolean isSymmetric1(TreeNode root) {
        return compare(root.left, root.right);
    }

    public static boolean compare(TreeNode left, TreeNode right) {
        if (left != null && right == null) {
            return false;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        if (left.val != right.val) {
            return false;
        }
        //if(left.value==right.value)如果左右值相等则继续向下递归遍历，只是暂时递归的节点满足条件

        //比较外侧节点
        boolean outSide = compare(left.left, right.right);
        //比较内侧节点
        boolean inSide = compare(left.right, right.left);

        return outSide && inSide;
    }

    /**
     * 2.迭代法（使用队列实现,比较节点同时进行队列相关操作）
     * @param root
     * @return
     */
    public static boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();

            //左右为空则进行下一次比较,直到遍历完（注意区别递归的判断条件处理）
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode != null && rightNode == null) {
                return false;
            }
            if (leftNode == null && rightNode != null) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }

            //if(left.value==right.value)如果左右值相等则继续向下递归遍历，只是暂时递归的节点满足条件
            //同样与递归相似比较外侧、内侧
            queue.offer(leftNode.left);
            queue.offer(rightNode.right);
            queue.offer(leftNode.right);
            queue.offer(rightNode.left);
        }
        return true;//遍历完没有返回false，则返回true
    }
}
