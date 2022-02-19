package binarytree;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 */
public class NTreeLevelOrder_429 {

    /**
     * 思路：只需要将二叉树的每层每个节点左右节点遍历入队部分改为每个节点for循环遍历所有孩子即可（n叉树有一个或多个孩子，不再分左右）
     * @param root
     * @return
     */
    public static List<List<Integer>> nTreeLevelOrder(NTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<NTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                NTreeNode node = queue.poll();
                level.add(node.val);
                //每一层中的每个节点可能有多个节点，故需要循环遍历入队
                for (NTreeNode child : node.children) {
                    queue.offer(child);
                }
            }
            result.add(level);
        }
        return result;
    }
}

/**
 * N叉树节点定义
 */
class NTreeNode{
    public  int val;//此处为了方便外类直接访问，使用public
    public List<NTreeNode> children;

    public NTreeNode(){

    }

    public NTreeNode(int val) {
        this.val = val;
    }

    public NTreeNode(int val, List<NTreeNode> children) {
        this.val = val;
        this.children = children;
    }
}
