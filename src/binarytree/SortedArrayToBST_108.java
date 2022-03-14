package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 */
public class SortedArrayToBST_108 {
    public static void main(String[] args) {

    }

    /**
     * 1.递归法
     * 思路：其实就是之前的两种遍历序列构造二叉树，这里不用强调平衡二叉搜索树，数组构造二叉树，构成平衡树是自然而然的事情，
     * 因为大家默认都是从数组中间位置取值作为节点元素，一般不会随机取，所以想构成不平衡的二叉树是自找麻烦。
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST1(int[] nums) {
        //左闭右开
        return toBST(nums, 0, nums.length);
    }

    public static TreeNode toBST(int[] nums, int left, int right) {
        if (right - left < 1) {
            return null;
        }
        if (right - left == 1) {
            return new TreeNode(nums[left]);
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBST(nums, left, mid);
        root.right = toBST(nums, mid + 1, right);
        return root;
    }

    /**
     * 2.迭代法（层序）
     *      通过三个队列来模拟，一个队列放遍历的节点，一个队列放左区间下标，一个队列放右区间下标。
     *      不断分割(左闭右闭)
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST2(int[] nums) {
        if (nums.length == 0) return null;

        //根节点初始化
        TreeNode root = new TreeNode(-1);
        Queue<TreeNode> nodeQueue = new LinkedList<>();//遍历节点
        Queue<Integer> leftQueue = new LinkedList<>();//左区间下标
        Queue<Integer> rightQueue = new LinkedList<>();//右区间下标

        // 根节点入队列
        nodeQueue.offer(root);
        // 0为左区间下标初始位置
        leftQueue.offer(0);
        // nums.size() - 1为右区间下标初始位置
        rightQueue.offer(nums.length - 1);

        while (!nodeQueue.isEmpty()) {
            TreeNode currNode = nodeQueue.poll();
            int left = leftQueue.poll();
            int right = rightQueue.poll();
            int mid = left + ((right - left) >> 1);

            // 将mid对应的元素给中间节点
            currNode.val = nums[mid];

            // 处理左区间
            if (left <= mid - 1) {
                currNode.left = new TreeNode(-1);//初始化左节点
                nodeQueue.offer(currNode.left);
                leftQueue.offer(left);
                rightQueue.offer(mid - 1);
            }

            // 处理右区间
            if (right >= mid + 1) {
                currNode.right = new TreeNode(-1);//初始化右节点
                nodeQueue.offer(currNode.right);
                leftQueue.offer(mid + 1);
                rightQueue.offer(right);
            }
        }
        return root;
    }
}
