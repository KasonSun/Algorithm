package binarytree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class RightSideView_199 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("二叉树右视图(队列实现)的结果为："+rightSideView(root).toString());
        System.out.println("二叉树右视图(队列实现)的结果为："+new RightSideView_199().rightSideView2(root).toString());
    }
    /**
     * （请看改进方法）二叉树的右视图（思路：二叉树层次遍历结果列表，每一层取最后一个）
     * @param root
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();//二叉树层次遍历结果列表
        List<Integer> resultList = new ArrayList<>();//二叉树层次遍历每一层的最后一个结点元素值
        Queue<TreeNode> queue = new LinkedList<>();//队列实现

        if (root == null) {
            return resultList;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();//存放每一层节点值的列表
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            levelList.add(level);
        }
        for (int i = 0; i < levelList.size(); i++) {
            int len=levelList.get(i).size();
            resultList.add(levelList.get(i).get(len - 1));
        }
        return resultList;
    }

    /**
     * 改进：二叉树的右视图（思路：二叉树层次遍历结果列表，每一层取最后一个，直接在for循环中判断是否遍历到当前层最后一个，是则将其值加入结果列表）
     * @param root
     * @return
     */
    public static List<Integer> rightSideView1(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();//二叉树层次遍历每一层的最后一个结点元素值保存

        if (root == null) {
            return resultList;
        }

        Queue<TreeNode> queue = new LinkedList<>();//队列实现
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();//存放每一层节点值的列表
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if(i==currentLevelSize-1){
                    resultList.add(node.val);
                }
            }
        }

        return resultList;
    }

    /**
     * 2.递归实现
     */
    List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideView2(TreeNode root){
        dfs(root, 0);
        return result;
    }
    public void dfs(TreeNode root, int level){
        if(root==null){
            return;
        }
        if(level==result.size()){
            result.add(root.val);
        }
        dfs(root.right, level + 1);//此处一定是先右边
        dfs(root.left, level+1);
    }
}
