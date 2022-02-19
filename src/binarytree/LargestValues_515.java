package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */
public class LargestValues_515 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("层序遍历层最大值的结果为："+largestValues(root).toString());
    }

    /**
     * 思路：层序遍历每一层取最大值进行保存
     * @param root
     * @return
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelMaxvalue = Integer.MIN_VALUE;
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                //当前层比较
                TreeNode node = queue.poll();
                currentLevelMaxvalue = Math.max(currentLevelMaxvalue, node.val);

                //拓展下层
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(currentLevelMaxvalue);
        }
        return result;
    }
}
