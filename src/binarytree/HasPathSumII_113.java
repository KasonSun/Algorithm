package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点:是指没有子节点的节点
 */
public class HasPathSumII_113 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(10);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node1.left = node4;

        int targetSum = 8;

        System.out.println("所有满足条件路径为："+pathSum(root,targetSum).toString());
    }

    /**
     * 1.递归实现（区别于leetcode112）
     *   注意：此题需要遍历整棵树，找到所有路径，所以递归函数不需要处理递归返回值，递归函数就不要返回值
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        preOrderDfs(root, targetSum, result, path);
        return result;
    }

    public static void preOrderDfs(TreeNode root, int targetSum, List<List<Integer>> result, List<Integer> path) {
        //将当前处理节点加入list
        path.add(root.val);
        //遇到叶子节点
        if (root.left == null && root.right == null) {
            //找到了和为targetSum的路径
            if (targetSum - root.val == 0) {
//                result.add(path);//这种写法会出错，相当于一直在一个list上操作
                result.add(new ArrayList<>(path));
            }
            return ;//和不为targetSum则返回
        }

        if (root.left != null) {
            targetSum -= root.val;
            preOrderDfs(root.left, targetSum, result, path);
            targetSum += root.val;//回溯
            path.remove(path.size() - 1);//回溯
        }

        if (root.right != null) {
            targetSum -= root.val;
            preOrderDfs(root.right, targetSum, result, path);
            targetSum -= root.val;//回溯
            path.remove(path.size() - 1);//回溯
        }
    }
}
