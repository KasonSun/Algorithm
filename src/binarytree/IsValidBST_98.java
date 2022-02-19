package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *          节点的左子树只包含 小于 当前节点的数。
 *          节点的右子树只包含 大于 当前节点的数。
 *          所有左子树和右子树自身必须也是二叉搜索树。
 */
public class IsValidBST_98 {
    public static void main(String[] args) {
        int[] Pre = {4, 2, 1, 3, 7};
        int[] In = {1, 2, 3, 4, 7};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        System.out.println("该二叉树是否是二叉搜索树："+new IsValidBST_98().isValidBST3(root));
    }

    /**
     * 1.递归实现（递归中序过程中有序判断）
     * ①参数和返回值：参数：当前根节点root；返回值：boolean(需要定义一个全局变量最小值)
     * ②终止条件：二叉搜索树可以为空
     * ③单层递归逻辑：中序遍历，一直更新maxValue，一旦发现maxValue>=root.value就返回false(相同值也要返回)
     *
     * @param root
     * @return
     */
    long maxValue = Long.MIN_VALUE;//测试数据中有int最小值
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST1(root.left);
        //中序遍历，验证遍历的元素是否从小到大
        if (maxValue < root.val) {
            maxValue = root.val;
        }else{
            return false;
        }
        boolean right = isValidBST1(root.right);
        return left && right;
    }

    /**
     * 2.遍历法(中序遍历，结果加入list，再看list是否有序)
     * 二叉搜索树中序遍历有序
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        List<Integer> inOrderList=new ArrayList<>();
        inOrder(root,inOrderList);
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i) <= inOrderList.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    //中序递归
    public void inOrder(TreeNode root,List<Integer> inOrderList) {
        if (root == null) {
            return ;
        }
        inOrder(root.left,inOrderList);
        inOrderList.add(root.val);
        inOrder(root.right,inOrderList);
    }

    /**
     * 3.中序迭代
     * @param root
     * @return
     */
    public static boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;//遍历节点
        TreeNode pre = null;//记录前一个结点
        //从根开始直到左子树为空（期间的遍历的节点入栈），开始出栈，访问，再遍历右子树
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur=cur.left;
            }else{
                cur = stack.pop();
                if (pre != null && cur.val <= pre.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }
}
