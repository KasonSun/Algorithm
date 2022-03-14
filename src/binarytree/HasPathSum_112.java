package binarytree;

import java.util.Stack;

/**
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点
 */
public class HasPathSum_112 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        int targetSum = 3;

        System.out.println("路径和是否存在："+hasPathSum2(root,targetSum));
    }

    /**
     * 深度优先遍历的方式（本题前中后序都可以，无所谓，因为中节点也没有处理逻辑）来遍历二叉树
     * 1.递归实现（深度）
     *  注意：此处不需要遍历整棵树，只需要搜索其中一条符合条件的路径，则递归一定需要返回值，遇到符合条件路径就要及时返回
     *      ①确定递归函数的参数和返回值类型：参数：二叉树根节点和计数器；返回值类型：boolean
     *      ②确定终止条件：可以使用递减，让计数器count初始为目标和，然后每次减去遍历路径节点上的数值，最后count为0且同时到了叶子节点则找到，否则没找到
     *      ③单层递归逻辑：因为终止条件是判断叶子节点，所以递归的过程中就不要让空节点进入递归了。递归函数是有返回值的，如果递归函数返回true，说明找到了合适的路径，应该立刻返回。
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return traversal(root, targetSum-root.val);
    }

    public static boolean traversal(TreeNode cur, int count) {
        if (cur.left == null && cur.right == null && count == 0) {//遇到叶子节点，并且计数==0，直接返回true
            return true;
        }
        if (cur.left == null && cur.right == null) {//遇到叶子节点，计数≠0，直接返回false
            return false;
        }
        //注意这里并没有中结点的处理逻辑，故前中后序都可以
        if (cur.left != null) {//左
            count -= cur.left.val;//递归，处理节点
            if (traversal(cur.left, count)) {
                return true;
            }
            count += cur.left.val;//回溯，撤销处理

            //以上可以简化为
            //if(traversal(cur.left,count-cur.left.value)){
            //      return true;
            // }
        }

        if (cur.right != null) {//右
            count -= cur.right.val;//递归，处理节点
            if (traversal(cur.right, count)) {
                return true;
            }
            count += cur.right.val;//回溯，撤销处理

            //以上可以简化为(回溯隐藏 在count-cur.right.value中，本次递归介绍，count并没有改变)
            //if(traversal(cur.right,count-cur.right.value)){
            //      return true;
            // }
        }
        return false;
    }

    /**
     * 1.递归简化
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && targetSum == root.val) {
            return true;
        }
        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum1(root.right, targetSum - root.val);
    }

    /**
     * 2.迭代实现(模拟前序)
     *      使用两个栈，一个记录节点，一个记录从头结点到该节点的路径值的总和
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        stack2.push(root.val);
        while (!stack1.isEmpty()) {
            int size = stack1.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = stack1.pop();
                int sum = stack2.pop();
                // 如果该节点是叶子节点了，同时该节点的路径数值等于sum，那么就返回true
                if (node.left == null && node.right == null && sum == targetSum) {
                    return true;
                }
                // 右节点，压进去一个节点的时候，将该节点的路径数值也记录下来
                if (node.right != null) {
                    stack1.push(node.right);
                    stack2.push(sum + node.right.val);
                }
                // 左节点，压进去一个节点的时候，将该节点的路径数值也记录下来
                if (node.left != null) {
                    stack1.push(node.left);
                    stack2.push(sum + node.left.val);
                }
            }
        }
        return false;
    }
}
