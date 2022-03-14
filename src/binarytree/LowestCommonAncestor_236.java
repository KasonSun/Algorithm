package binarytree;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *  百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 *  满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 */
public class LowestCommonAncestor_236 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        System.out.println("二叉树最近公共祖先为：" + lowestCommonAncestor1(root, node2, node3).val);
    }

    /**
     * 1.递归法（后序,由底向上遍历）
     *      注意：此处递归需要遍历整棵树，且需要递归处理返回值，因此递归函数有返回值
     *      ①递归返回左子树为空，右子树不空，返回右子树
     *      ②递归返回左子树不空，右子树为空，返回左子树
     *      ③递归返回左右子树都不为空，返回根
     *      ④递归返回左右都为空，返回空
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归返回值
        if (root == p || root == q || root == null) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);//左
        TreeNode right = lowestCommonAncestor(root.right, p, q);//右

        //递归返回值进一步处理部分                                   //中
        //左右子树p,q找到了，当前节点即为要求的结果
        if (left != null && right != null) {
            return root;
        }
        if (left != null && right == null) {
            return left;
        }else if (left == null && right != null) {
            return right;
        }else{//root.left==null && root.right==null
            return null;
        }
    }

    /**
     * 1.递归法（简化写法）
     *      注意：此处递归需要遍历整棵树，且需要递归处理返回值，因此递归函数有返回值
     *      ①递归返回左子树为空，右子树不空，返回右子树
     *      ②递归返回左子树不空，右子树为空，返回左子树
     *      ③递归返回左右都为空，返回空
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p ||root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left != null && right != null) return root;
        else if (left == null && right != null) return right;
        else if (left != null && right == null) return left;
        else return null;
    }
}
