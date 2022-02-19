package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * eg: root=[4,2,6,1,3]  输出；1
 *     root=[1,0,null,null,12,49]  输出;1
 *
 */
public class GetMinimumDifference_530 {
    public static void main(String[] args) {
        int[] Pre = {4, 2, 1, 3, 7};
        int[] In = {1, 2, 3, 4, 7};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        System.out.println("二叉搜索树的最小绝对值之差为："+new GetMinimumDifference_530().getMinimumDifference1(root));
    }

    /**
     * 1.中序递归，返回结果list，遍历list求差值(有序之间的差值最小一定在相邻位置)
     *
     * @param root
     * @return
     */

    public int getMinimumDifference1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        inOrder(root, result);
        for (int i = 1; i < result.size(); i++) {
            int absValue = result.get(i) - result.get(i - 1);
            if ( absValue< minValue) {
                minValue = absValue;
            }
        }
        return minValue;
    }

    public static void inOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return ;
        }
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
    }

    /**
     * 2.中序迭代（遍历过程中进行条件判断，需要一个pre记录前一个结点）
     * @param root
     * @return
     */
    public static int getMinimumDifference2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        int result = Integer.MAX_VALUE;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null) {
                    result = Math.min(result, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return result;
    }
}
