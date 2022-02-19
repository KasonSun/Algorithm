package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class IsSameTree_100 {
    public static void main(String[] args) {

    }

    /**
     * 1.递归实现
     * 思路：类似于单棵二叉树的左右是否对称，将两棵树想象为两棵子树
     * @param p,q
     * @return
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {//或的条件可以拆开来看（实际上就是在第一个条件排除两个都为空条件后，剩下的就是其中一方为空）
            return false;
        } else if (p.val != q.val) {
            return false;
        }else{
            return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);//注意此处逻辑与判断单棵二叉树是否对称区别（对称：对称位置判断；相同：相同的位置）
        }
    }

    /**
     * 2.迭代实现
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            TreeNode qNode = queue.poll();
            if (pNode == null && qNode == null) {
                continue;
            }
            //有一个为空则一定不相同（上面的if已经排除两个都为空的情况）
            if (pNode == null || qNode == null) {
                return false;
            }
            if (pNode.val != qNode.val) {
                return false;
            }

            //符合条件的左右节点入队
            queue.offer(pNode.left);
            queue.offer(qNode.left);
            queue.offer(pNode.right);
            queue.offer(qNode.right);
        }
        return true;
    }

}
