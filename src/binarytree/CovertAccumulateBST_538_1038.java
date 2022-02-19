package binarytree;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node的新值等于原树中大于或等于node.val的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *
 */
public class CovertAccumulateBST_538_1038 {
    public static void main(String[] args) {
        int[] Pre = {4, 2, 1, 3, 5, 6};
        int[] In = {1, 2, 3, 4, 5, 6};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        System.out.println("二叉搜索树的众数为：" + new CovertAccumulateBST_538_1038().convertBST2(root).right.val);//11
    }

    /**
     * 1.迭代（可以不用将两个分开写，可以优化，全局变量实际上也不需要）
     * 普通思路：中序遍历转换为数组，累加完成进行构造
     * 优化思路：中序遍历过程进行累加构造（右-中-左的顺序）（二叉搜索，只需要累加大于等于他的，故按照右中左的顺序）
     *
     * @return
     */
    int pre = 0;//全局变量，记录前一个结点的数值
    public TreeNode convertBST1(TreeNode root) {
        traversal1(root);
        return root;
    }

    public void traversal1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right; //右
            }else{
                cur = stack.pop();//中
                cur.val += pre;//原地修改，区别与递归
                pre = cur.val;//中

                cur = cur.left;//左
            }
        }
    }

    /**
     * 2.递归
     *      中序遍历过程进行累加构造（右-中-左的顺序）（二叉搜索，只需要累加大于等于他的，故按照右中左的顺序）
     * @return
     */
    int sum = 0;//全局变量
    public TreeNode convertBST2(TreeNode root) {
        traversal2(root);
        return root;
    }

    //按照右中左的顺序进行遍历，累加（二叉搜索，只需要累加大于等于他的，故按照右中左的顺序）
    public void traversal2(TreeNode root) {
        if (root == null) {
            return;
        }
        //右
        traversal2(root.right);
        //中
        sum += root.val;//注意区别于迭代的原地修改
        root.val = sum;
        //左
        traversal2(root.left);
    }

}
