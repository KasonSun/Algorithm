/*
package interview.bilibili;

import java.util.*;

public class Q2 {
    public int maxValue (TreeNode root) {
        // write code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);//根节点先加入队列
        List<List<Integer>> result = new ArrayList<>();
        int high=0;
        int currentSubValue=0;
        int currentHigh=0;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();//用来存放每一层的节点值的列表（每次while会重新赋值）
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                    if(node.left.val-node.val>currentSubValue){
                        currentSubValue=node.left.val-node.val;
                        currentHigh=high;
                    }
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    if(node.right.val-node.val>currentSubValue){
                        currentSubValue=node.left.val-node.val;
                        currentHigh=high;
                    }
                }
            }
            result.add(level);
            high++;
        }
        int max=0;
        for(int i=0;i<result.get(currentHigh-1).size();i++){
            max+=result.get(currentHigh-1).get(i);
        }
        return max+currentSubValue;
    }

    public static void postOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);//访问数据，放入list
    }

    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();//中（此时已经出来了）
            result.add(node.val);
            if (node.left != null) {//左
                stack.push(node.left);
            }
            if (node.right != null) {//右
                stack.push(node.right);
            }
        }
        Collections.reverse(result);//最后翻转结果
        return result;
    }
}
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

*/
