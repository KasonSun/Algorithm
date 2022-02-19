package binarytree;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 */
public class LowestCommonAncestor_235 {
    public static void main(String[] args) {
        int[] Pre = {4, 2, 1, 3, 7};
        int[] In = {1, 2, 3, 4, 7};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        System.out.println("二叉搜索树搜索最近公共祖先为：" + lowestCommonAncestor3(root, root.left.left, root.right).val);
    }

    /**
     * 1.自顶向下遍历只需要满足遍历过程中cur.value在【p.value,q.value】之间，则可以进行返回
     *  分区间讨论
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        return traversal(root, p, q);
    }

    public static TreeNode traversal(TreeNode cur, TreeNode p, TreeNode q) {
        //这个条件其实可以不要，题目说了p、q 为不同节点且均存在于给定的二叉搜索树中。也就是说一定会找到公共祖先的，所以并不存在遇到空的情况。
        if (cur == null) {
            return cur;
        }
        //cur在区间右边，则向左搜索
        if (cur.val > p.val && cur.val > q.val) {
            TreeNode left = traversal(cur.left, p, q);
            if (left != null) {
                return left;
            }
        }
        //cur在区间左边，则向右搜索
        if (cur.val < p.val && cur.val < q.val) {
            TreeNode right = traversal(cur.right, p, q);
            if (right != null) {
                return right;
            }
        }
        //刚好在区间之间，直接返回cur
        return cur;
    }

    /**
     * 2.当成普通二叉树进行递归求解(自底向上)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        //在root的左右子树进行搜索
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        //此时找到p,q
        if (left != null && right != null) {
            return root;
        }
        if (left != null && right == null) {
            return left;
        }
        if (left == null && right != null) {
            return right;
        }
        return null;
    }

    /**
     * 3.迭代实现（分区间讨论）
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            }else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            }else{
                break;
            }
        }
        return root;
    }
}
