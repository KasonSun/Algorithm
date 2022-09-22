package linkedlist;

/**
 * 考虑使用中序遍历访问树的各节点 cur ；并在访问每个节点时构建 cur 和前驱节点 pre 的引用指向；
 * 中序遍历完成后，最后构建头节点和尾节点的引用指向即可。
 *
 *  left指向前面的节点，right指向后面的节点
 */
public class BinaryTreeDoubleCircleList_jianzhi36 {
    Node pre, head;
    public Node treeToDoublyList(Node root) {
        if(root==null) return null;
        inorder(root);
        //首尾拼接
        head.left=pre;
        pre.right=head;
        return head;
    }

    /**
     * dfs(cur): 递归法中序遍历；
     *
     * 终止条件： 当节点 cur 为空，代表越过叶节点，直接返回；
     * 递归左子树，即 dfs(cur.left) ；
     * 构建链表：
     *      当 pre 为空时： 代表正在访问链表头节点，记为 head ；
     *      当 pre 不为空时： 修改双向节点引用，即 pre.right = cur ， cur.left = pre ；
     *      保存 cur ： 更新 pre = cur ，即节点 cur 是后继节点的 pre ；
     * 递归右子树，即 dfs(cur.right) ；
     * @param cur
     */
    private void inorder(Node cur){
        if(cur==null) return;
        inorder(cur.left);
        if(pre!=null) pre.right=cur;
        else head=cur;
        cur.left=pre;
        pre=cur;
        inorder(cur.right);
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}