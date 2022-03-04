package dp;

/**
 *小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
 * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
 * 聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
 *
 *
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 *
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
 */
public class RobIII_337 {
    public static void main(String[] args) {

    }

    /**
     * 动态规划；T(O(n))  S(O(logn)) 后序遍历，需要通过递归函数的返回值来做下一步计算
     *  这道题目算是树形dp的入门题目，因为是在树上进行状态转移，我们在讲解二叉树的时候说过递归三部曲，那么下面我以递归三部曲为框架，其中融合动规五部曲的内容来进行讲解。
     *                五部曲：①确定递归函数的参数和返回值
     *                          int[] robTree(TreeNode cur)
     *
     *                       ②确定dp数组以及下标的含义
     *                            这里可以使用一个长度为2的dp数组，记录当前节点偷与不偷所得到的的最大金钱。
     *                            下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱。
     *                            那么有同学可能疑惑，长度为2的数组怎么标记树中每个节点的状态呢？
     *                            别忘了在递归的过程中，系统栈会保存每一层递归的参数。
     *
     *                       ③终止条件和确定遍历顺序
     *                              终止条件：if(cur==null)  return new int{0,0};//也相当于dp数组的初始化
     *                            首先明确的是使用后序遍历。 因为通过递归函数的返回值来做下一步计算。
     *                            通过递归左节点，得到左节点偷与不偷的金钱。int[] left=robTree(cur.left);  //左
     *                            通过递归右节点，得到右节点偷与不偷的金钱。int[] right=robTree(cur.right);//右
     *                                                                                       //中
     *                       ④确定单层递归的逻辑
     *                            如果是偷当前节点，那么左右孩子就不能偷，val1 = cur->val + left[0] + right[0]; （如果对下标含义不理解就在回顾一下dp数组的含义）;
     *                            如果不偷当前节点，那么左右孩子就可以偷，至于到底偷不偷一定是选一个最大的，所以：val2 = max(left[0], left[1]) + max(right[0], right[1]);
     *                            最后当前节点的状态就是{val2, val1};即：{不偷当前节点得到的最大金钱，偷当前节点得到的最大金钱}
     *                       ⑤举例推导dp数组
     *                                  输入[2,7,9,3,1]为例。
     * @param root
     * @return
     */
    public static int robIII(TreeNode root) {
        int[] result = robTree(root);
        return Math.max(result[0], result[1]);
    }

    // 长度为2的数组，0：不偷，1：偷
    public static int[] robTree(TreeNode cur) {
        if (cur == null) {
            return new int[]{0, 0};
        }
        int[] left = robTree(cur.left);
        int[] right = robTree(cur.right);
        //不偷cur，则可以偷左右孩子
        int val0 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //偷cur，则不可以偷左右孩子
        int val1 = cur.val + left[0] + right[0];//包含递归过程因此left[0]，right[0]也是遍历过程中一员，他们也是有值的（不偷左右孩子情况下也是有值存在的）
        return new int[]{val0, val1};
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val=val;
        this.left = left;
        this.right = right;
    }
}
