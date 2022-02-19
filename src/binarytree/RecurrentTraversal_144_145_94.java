package binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的前中后序递归遍历
 */
public class RecurrentTraversal_144_145_94 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        List<Integer> result = new ArrayList<>();

        //前序遍历
        result=Pre.preOrderTraversal(root);
        System.out.println("前序遍历为："+result.toString());

        //中序遍历
        result = In.inOrderTraversal(root);
        System.out.println("中序遍历为："+result.toString());

        //后序遍历
        result = Post.postOrderTraversal(root);
        System.out.println("后序遍历为："+result.toString());
    }
}
/**
 *二叉树的定义
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(){

    };

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 二叉树递归前序遍历
 */
class Pre{
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    public static void preOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return ;
        }
        result.add(root.val);//访问数据，放入list
        preOrder(root.left, result);
        preOrder(root.right, result);
    }
}

/**
 * 二叉树递归中序遍历
 */
class In{
    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    public static void inOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrder(root.left,result);
        result.add(root.val);//访问数据，放入list
        inOrder(root.right,result);
    }
}

/**
 * 二叉树递归后序遍历
 */
class Post{
    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    public static void postOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);//访问数据，放入list
    }
}