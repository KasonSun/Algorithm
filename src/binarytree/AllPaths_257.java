package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个二叉树的根节点 root ，按任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 */
public class AllPaths_257 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("二叉树所有路径为："+allPaths1(root));
    }

    /**
     * 1.递归实现（前序）
     * @param root
     * @return
     */
    public static List<String> allPaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, res);
        return res;
    }

    /**
     * 递归遍历逻辑：叶子节点，则进行输出；否则继续递归遍历（注意中间的回溯过程，同时回溯和递归是一一对应的，有一个递归，就要有一个回溯）
     * @param root
     * @param paths
     * @param res
     */
    public static void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val);//中
        //当前节点为叶子节点
        if (root.left == null && root.right == null) {
            //输出
            StringBuilder sb = new StringBuilder();
            //此时注意输出形式->，paths中的最后一个结点，也即为叶子节点，他的后面没用这个->,故其应该单独处理
            for (int i = 0; i < paths.size()-1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));//最后叶子节点单独处理
            res.add(sb.toString());//讲当前满足条件的一个路径加入list
            return;
        }
        if (root.left != null) {//左
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1);//回溯
        }

        if (root.right != null) {//右
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1);//回溯
        }
    }

    /**
     * 2.迭代实现(掌握递归实现)
     * @param root
     * @return
     */
    public static List<String> allPaths1(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<Object> stack = new Stack<>();//定义为Object，则可以同时放入路径和节点
        //节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");//转换为字符串形式
        while (!stack.isEmpty()) {
            //节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            //遍历到叶子节点
            if (node.left == null && node.right == null) {
                result.add(path);
            }
            //左子节点不为空
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
            //右子节点不为空
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
        }
        return result;
    }
}
