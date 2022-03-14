package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点
 *
 * 注意：合并必须从两个树的根节点开始
 */
public class MergeTrees_617 {
    public static void main(String[] args) {
        int[] tree1Pre = {1, 3, 5, 2};
        int[] tree1In = {5, 3, 1, 2};
        int[] tree2Pre = {2, 1, 4, 3, 7};
        int[] tree2In = {1, 4, 2, 3, 7};

        TreeNode root1 = BuildTree_105_106.buildTree1(tree1In, tree1Pre);
        TreeNode root2 = BuildTree_105_106.buildTree1(tree2In, tree2Pre);
        System.out.println("合并后的而二叉树根节点值为："+mergeTrees2(root1, root2).val);
    }

    /**
     * 1.迭代实现(直接利用root1进行修改返回)
     * @param root1
     * @param root2
     * @return 新树的根节点
     */
    public static TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if(root1==null) return root2;
        if(root2==null) return root1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            //此时两节点值一定不为空，value值相加
            node1.val += node2.val;

            //左节点都不为空，加入队列
            if (node1.left != null && node2.left != null) {
                queue.offer(node1.left);
                queue.offer(node2.left);
            }

            //右节点都不为空，加入队列
            if (node1.right != null && node2.right != null) {
                queue.offer(node1.right);
                queue.offer(node2.right);
            }

            //root1的左节点为空，root2的左节点不为空，直接赋值
            if (node1.left == null && node2.left != null) {
                node1.left = node2.left;
            }
            //root1的右节点为空，root2的右节点不为空，直接赋值
            if (node1.right == null && node2.right != null) {
                node1.right = node2.right;
            }

            //还有两种情况分别为：root1左不空root2左空和root1右不空root2右空，这两种情况不需要进行处理，因为本解法以root1返回作为新树
        }
        return root1;
    }

    /**
     * 2.递归实现(直接利用root1进行修改返回)
     *      ① 递归的参数和返回值：参数：两个二叉树的根节点； 返回值：合并后的二叉树
     *      ② 终止条件：两树的遍历节点root1==null则合并之后就是root2节点（root2为空也一样，合并还是空）；同理root2==null则合并之后为root1节点（root1为空，合并也还是空）
     *      ③单层递归逻辑：root1.value+=root2.value 分别处理root1、root2的左子树和root1、root2的右子树，最终返回的root1就是合并之后的树
     * @param root1
     * @param root2
     * @return
     */
    public static TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if(root1==null) return root2;
        if(root2==null) return root1;

        //此时就是root1和root2都不为空的情况
        root1.val += root2.val;
        root1.left = mergeTrees2(root1.left, root2.left);
        root1.right = mergeTrees2(root1.right, root2.right);

        return root1;
    }
}
