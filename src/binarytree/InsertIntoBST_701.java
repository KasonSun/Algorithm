package binarytree;

/**
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 */
public class InsertIntoBST_701 {
    public static void main(String[] args) {
        int[] Pre = {4, 2, 1, 3, 7};
        int[] In = {1, 2, 3, 4, 7};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        int value = 5;
        System.out.println("二叉搜索树搜索最近公共祖先为：" + insertIntoBST1(root,value).right.left.val);//5
    }

    /**
     * 1.迭代实现（不改变树的结构）
     * @param root
     * @param value
     * @return
     */
    public static TreeNode insertIntoBST1(TreeNode root, int value) {
        if (root == null) {
            TreeNode node = new TreeNode(value);
            return node;
        }
        TreeNode cur = root;
        TreeNode parent = null;//需要记录上一个节点，否则无法进行节点插入
        //迭代搜索，找到插入位置的父节点
        while (cur != null) {
            parent = cur;
            if (cur.val > value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        //在其父节点后进行插入
        TreeNode node = new TreeNode(value);
        if (parent.val > value) {
            parent.left = node;
        }else{
            parent.right = node;
        }
        return root;
    }

    /**
     * 2.递归实现
     *
     * @return
     */
    public static TreeNode insertIntoBST2(TreeNode root, int value) {
        //为空则进行插入
        if (root == null) {
            TreeNode node = new TreeNode(value);
            return node;
        }
        if (root.val > value) {
            root.left = insertIntoBST2(root.left, value);
        }
        if (root.val < value) {
            root.right = insertIntoBST2(root.right, value);
        }
        //返回根节点（上面递归完，最终栈里的root还是原始的root，没有变）
        return root;
    }
}
