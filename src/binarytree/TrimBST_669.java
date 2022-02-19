package binarytree;

/**
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
 * 修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。
 *
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变
 *
 */
public class TrimBST_669 {
    public static void main(String[] args) {
        int[] Pre = {4, 2, 1, 3, 7};
        int[] In = {1, 2, 3, 4, 7};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        int low = 1;
        int high = 4;
        System.out.println("修剪后的二叉搜索树指定节点为：" + trimBST1(root, low, high).right);//null 7被修剪了
    }

    /**
     * 递归实现
     *      ①TreeNode* trimBST(TreeNode* root, int low, int high)
     *      ②修剪的操作并不是在终止条件上进行的，所以就是遇到空节点返回就可以了。if (root == nullptr ) return nullptr;
     *      ③（以下几种情况的递归逻辑是因为移除节点是通过直接将满足区间的节点直接跳过不满足条件节点，进行赋值实现的）
     *          如果root（当前节点）的元素小于low的数值，那么应该递归右子树（找到不需要移除的节点），并返回右子树符合条件的头结点
     *          如果root(当前节点)的元素大于high的，那么应该递归左子树（找到不需要移除的节点），并返回左子树符合条件的头结点。
     *          接下来要将下一层处理完左子树的结果赋给root->left，处理完右子树的结果赋给root->right。最后返回root节点
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static TreeNode trimBST1(TreeNode root, int low, int high) {
        if(root==null) return null;
        if(root.val <low){
            TreeNode right = trimBST1(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            TreeNode left = trimBST1(root.left, low, high);
            return left;
        }
        root.left = trimBST1(root.left, low, high);
        root.right = trimBST1(root.right, low, high);
        return root;
    }

    /**
     * 递归简化
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static TreeNode trimBST2(TreeNode root, int low, int high) {
        if(root==null) return null;
        if(root.val <low) return trimBST2(root.right, low, high);
        if (root.val > high) return trimBST2(root.left, low, high);
        root.left = trimBST2(root.left, low, high);
        root.right = trimBST2(root.right, low, high);
        return root;
    }

    /**
     * 2.迭代实现(比较难理解，不容易想到)
     *      因为二叉搜索树的有序性，不需要使用栈模拟递归的过程。
     *      在剪枝的时候，可以分为三步：
     *          将root移动到[L, R] 范围内，注意是左闭右闭区间
     *          剪枝左子树
     *          剪枝右子树
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static TreeNode trimBST3(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        //处理头结点，让root移动到[L, R] 范围内，注意是左闭右闭区间
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {// 小于L往右走
                root = root.right;
            }else{
                root = root.left;// 大于R往左走
            }
        }
        TreeNode cur = root;
        // 此时root已经在[L, R] 范围内，处理左孩子元素小于L的情况
        while (cur != null) {
            while (cur.left != null && cur.left.val < low) {
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }

        cur = root;
        // 此时root已经在[L, R] 范围内，处理右孩子大于R的情况
        while (cur != null) {
            while (cur.right != null && cur.right.val > high) {
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }
}
