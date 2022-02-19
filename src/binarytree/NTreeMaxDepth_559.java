package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）
 *
 */
public class NTreeMaxDepth_559 {
    /**
     * 1.迭代实现（与二叉树思路一致，使用层序实现）
     * @param root
     * @return
     */
    public static int maxDepth1(NTreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<NTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            depth++;
            for (int i = 0; i < currentLevelSize; i++) {
                NTreeNode node = queue.poll();
                for (int j = 0; j < node.children.size(); j++) {
                    if (node.children.get(j) != null) {
                        queue.offer(node.children.get(j));
                    }
                }
            }
        }
        return depth;
    }

    /**
     * 2.递归实现(后序)
     * @param root
     * @return
     */
    public static int maxDepth(NTreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                depth = Math.max(depth, maxDepth(root.children.get(i)));
            }
        }
        return depth + 1;
    }
}
