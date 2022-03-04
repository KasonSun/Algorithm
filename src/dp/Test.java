package dp;

/**
 * 动态规划：T(O())  S(O())
 *
 *              五部曲：
 *                  ①dp数组定义
 *
 *
 *                  ②状态转移方程
 *
 *
 *                  ③dp[i]的初始化
 *
 *
 *                  ④确定遍历顺序
 *
 *
 *                  ⑤举例推导dp数组
 *
 *
 * @param
 * @return
 */
public class Test {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, -1, 5};
        BTreeNode root = constructBTree(nums);

        System.out.println("当前节点值为："+root.left.left.val);
    }

    public static BTreeNode constructBTree(int[] nums) {
        BTreeNode root = null;
        BTreeNode[] tree = new BTreeNode[nums.length];
        //1.将输入数组转化为二叉树的节点
        for (int i = 0; i < nums.length; i++) {
            BTreeNode node = null;
            if (nums[i] != -1) {//数组存储的二叉树，一般-1表示空
                node = new BTreeNode(nums[i]);
            }
            tree[i] = node;
            if (i == 0) {
                root = node;
            }
        }
        //2.构造过程
        // 父节点数组下标i，则左孩子下标i*2+1， 右孩子i*2+2
        for (int i = 0; i * 2 + 2 < nums.length; i++) {
            if (tree[i] != null) {
                tree[i].left = tree[i * 2 + 1];
                tree[i].right = tree[i * 2 + 2];
            }
        }
        return root;
    }


}

class BTreeNode {
    int val;
    BTreeNode left;
    BTreeNode right;

    public BTreeNode() {

    }

    public BTreeNode(int val) {
        this.val = val;
    }

    public BTreeNode(int val, BTreeNode left, BTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
