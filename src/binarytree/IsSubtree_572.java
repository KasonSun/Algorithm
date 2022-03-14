package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 */
public class IsSubtree_572 {
    /**
     * 1.1,1.2,1.3思路：将二叉树进行遍历，从遍历结果某个节点出发的子树看是否能够匹配到与给定的相同的子树
     * @param root
     * @param subRoot
     * @return
     */
    public static boolean isSubtree1(TreeNode root, TreeNode subRoot) {
        List<TreeNode> result = preOrderTraversal(root);
        for (int i = 0; i < result.size(); i++) {
            boolean flag=isSameTree(result.get(i), subRoot);
            if (flag == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * 1.2是否为同一棵树(递归实现)
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }else{
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 1.1前序遍历
     * @param root
     * @return
     */
    public static List<TreeNode> preOrderTraversal(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 2.递归实现
     *
     * @return
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;//子树为空一定是true
        }
        if (root == null) {
            return false;//上面if将subRoot为空排除，此时若root为空，则一定返回false
        }
        /**
         * 是子树则此时三种情况1.root和subRoot相同
         *                  2.subRoot是root某个左子树
         *                  3.subRoot是root某个右子树
         */
        return isSubtree(root.left, subRoot) || isSubtree(root.right,subRoot) ||isSameTree(root, subRoot);
    }
}
