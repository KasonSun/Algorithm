package binarytree;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *      首先找到需要删除的节点；
 *      如果找到了，删除它。
 */
public class DeleteNode_450 {
    public static void main(String[] args) {
            int[] Pre = {4, 2, 1, 3, 7};
            int[] In = {1, 2, 3, 4, 7};
            TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
            int key = 2;
            System.out.println("二叉搜索树搜索最近公共祖先为：" + deleteNode2(root,key).left.val);//3
            System.out.println("二叉搜索树搜索最近公共祖先为：" + deleteCommonTreeNode(root,key).left.val);//3
    }

    /**
     * 1.递归法
     *      ①参数，返回值：参数：root，key；返回值：TreeNode
     *      ②终止条件：root==null 返回root
     *      ③单层逻辑：
     *    有以下五种情况：
     *       第一种情况：没找到删除的节点，遍历到空节点直接返回了
     *      找到删除的节点
     *      第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
     *      第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
     *      第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
     *      第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode1(TreeNode root, int key) {
        root = delete(root, key);
        return root;
    }

    public static TreeNode delete(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = delete(root.left, key);
        } else if (root.val < key) {
            root.right = delete(root.right, key);
        }else{//==key
            if(root.left==null) return root.right;
            if(root.right==null) return root.left;
            //root左右都不为空
            //则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            cur.left = root.left;
            TreeNode temp = root;//记录节点，后面又java自动回收或者手动进行释放
            root = root.right;
            return root;//返回删除节点右孩子为新的根节点
        }
        return root;
    }

    /**
     * 2.递归实现方法二
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return root;//1.
        if (root.val == key) {
            if (root.left == null) {//2.3
                return root.right;
            } else if (root.right == null) {//3.4.
                return root.left;
            } else {//5.
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left;
                root = root.right;
                return root;
            }
        }
        if (root.val > key) root.left = deleteNode2(root.left, key);
        if (root.val < key) root.right = deleteNode2(root.right, key);
        return root;
    }

    /**
     * 普通二叉树删除：
     *      1.递归删除普通二叉树的节点（遍历整棵树）
     *      2.迭代遍历，加上删除的逻辑
     *      代码中目标节点（要删除的节点）被操作了两次：
     *          第一次是和目标节点的右子树最左面节点交换；
     *          第二次直接被NULL覆盖了
     * 注意：实际上删除普通二叉树的操作没有意思，没有给出一个具体的删除后调整规则
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteCommonTreeNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            if (root.right == null) {//第二次操作目标值：最终删除作用
                return root.left;
            }
            TreeNode cur=root.right;
            while(cur.left!=null){
                cur=cur.left;
            }
            //第一次操作目标值：交换目标值右子树最左面节点
            int temp=root.val;
            root.val=cur.val;
            cur.val = temp;
        }
        root.left = deleteCommonTreeNode(root.left, key);
        root.right = deleteCommonTreeNode(root.right, key);
        return root;
    }
}
