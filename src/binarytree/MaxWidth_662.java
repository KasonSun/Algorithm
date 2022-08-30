package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 662.二叉树的最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 */
public class MaxWidth_662 {
    public static void main(String[] args) {
        int[] Pre = {1, 2, 4, 5, 3, 9};
        int[] In = {4, 2, 5, 1, 3, 9};
        TreeNode root = BuildTree_105_106.buildTree1(In, Pre);
        System.out.println("二叉树的最大宽度为："+ widthOfBinaryTree(root));
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        Deque<TreeNode> queue=new LinkedList<>();
        root.val=0;
        queue.offer(root);
        int width=0;
        while(!queue.isEmpty()){
            int currentSize = queue.size();
            int  currentWidth=(queue.peekLast().val-queue.peekFirst().val)+1;
            if(currentWidth>width){
                width=currentWidth;
            }
            for(int i=0;i<currentSize;i++){
                TreeNode node=queue.pollFirst();
                if(node.left!=null){
                    node.left.val=2*node.val+1;
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    node.right.val=2*node.val+2;
                    queue.offer(node.right);
                }
            }
        }
        return width;
    }
}
