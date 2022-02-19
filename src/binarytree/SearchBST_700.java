package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 */
public class SearchBST_700 {
    public static void main(String[] args) {
        int[] Pre = {4, 2, 1, 3, 7};
        int[] In = {1, 2, 3, 4, 7};
        int value = 1;
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        System.out.println("二叉搜索树搜索与value值相等的节点为："+ searchBST3(root,value).val);
    }

    /**
     * 1.迭代实现(层序,不推荐，看第三种实现方法)
     *  时间复杂度 O(n)可以改进
     * @param root
     * @param val
     * @return
     */
    public static TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == val) {
                    return node;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return null;
    }

    /**
     * 2.递归实现
     *      ①递归参数和返回值：参数：根节点和搜索数值；返回值：就是搜索数值所在节点
     *      ②终止条件：root为空或者找到这个节点了就返回root
     *      ③单层递归逻辑：
     *          二叉搜索树的特性决定了可以有方向的进行搜索，
     *          root.value>value则继续在左子树搜索，
     *          root.value<value则继续在右子树搜索；
     *          没有搜素到就返回null
     * @param root
     * @param val
     * @return
     */
    public static TreeNode searchBST2(TreeNode root, int val) {
        //终止条件
        if (root == null || root.val == val) {
            return root;
        }

        //左子树搜索
        if (root.val > val) {
            return searchBST2(root.left, val);
        }
        //右子树搜索
        if (root.val < val) {
            return searchBST2(root.right, val);
        }
        //没有搜索到
        return null;
    }

    /**
     * 3.迭代简化
     *      对于二叉搜索树可就不一样了，因为二叉搜索树的特殊性，也就是节点的有序性，可以不使用辅助栈或者队列就可以写出迭代法。
     *      对于一般二叉树，递归过程中还有回溯的过程，例如走一个左方向的分支走到头了，那么要调头，在走右分支。
     *      而对于二叉搜索树，不需要回溯的过程，因为“节点的有序性就帮我们确定了搜索的方向”。
     * @param root
     * @param val
     * @return
     */
    public static TreeNode searchBST3(TreeNode root, int val) {
        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
    }
}
