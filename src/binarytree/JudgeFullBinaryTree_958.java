package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否是完全二叉树
 */
public class JudgeFullBinaryTree_958 {
    public static void main(String[] args) {

    }

    /**
     * 层序实现
     *      层序遍历二叉树
     *          1.对于完全二叉树的层序遍历，会在将所有叶子节点都加入队列中后才遇到null；
     *          2.对于不完全二叉树的层序遍历，会在遍历过程中就遇到null
     * @param root
     * @return
     */
    public static boolean judgeFullBinaryTree(TreeNode root) {
        if(root==null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag=false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //遇到空节点则假设当前有效节点已经遍历结束，设置flag为true
                if (node == null) {
                    flag = true;
                }else{
                    //此时已经遍历完所有非空节点，若此时遇到非空节点（且flag为true）则返回false
                    if(flag==true){
                        return false;
                    }
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        return flag;
    }
}
