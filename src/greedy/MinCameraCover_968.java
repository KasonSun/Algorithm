package greedy;

/**
 * 968. 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 */
public class MinCameraCover_968 {
    public static void main(String[] args) {

    }

    /**
     * 贪心法：
     *         思路：从题目实例中可以得到：摄像头都没有放在叶子节点，这一点很重要，因为如果我们将摄像头放在叶子节点上，就浪费了一层覆盖
     *              所以将摄像头放在叶子节点的父节点，才能充分利用摄像头的覆盖面积
     *              （那么有同学可能问了，为什么不从头结点开始看起呢，为啥要从叶子节点看呢？
     *              因为头结点放不放摄像头也就省下一个摄像头， 叶子节点放不放摄像头省下了的摄像头数量是指数阶别的。）
     *
     *              所以，我们要从下往上看，局部最优：让叶子节点的父节点安摄像头，所用摄像头最少；整体最优：全部摄像头数量所用最少
     *              此时，大体思路就是从低到上，先给叶子节点父节点放个摄像头，然后隔两个节点放一个摄像头，直至到二叉树头结点。
     *              此时这道题目还有两个难点：
     *                      二叉树的遍历--后序
     *                      如何隔两个节点放一个摄像头--状态转移公式（三种状态：0节点无覆盖；1节点有摄像头；2本节点有覆盖）
     *
     *                      因为在遍历树的过程中，就会遇到空节点，那么问题来了，空节点究竟是哪一种状态呢？ 空节点表示无覆盖？ 表示有摄像头？还是有覆盖呢？
     *                      空节点不能是无覆盖的状态，这样叶子节点就要放摄像头了，空节点也不能是有摄像头的状态，这样叶子节点的父节点就没有必要放摄像头了，
     *                      而是可以把摄像头放在叶子节点的爷爷节点上。所以空节点的状态只能是有覆盖，这样就可以在叶子节点的父节点放摄像头了
     *
     * @param root
     * @return
     */
    int result;
    public int minCameraCover(TreeNode root) {
        result = 0;
        //情况四 root无覆盖
        if(traversal(root)==0){
            result++;
        }
        return result;
    }

    //0节点无覆盖；1节点有摄像头；2本节点有覆盖
    public int traversal(TreeNode cur) {
        //空节点，该节点有覆盖(空节点的状态只能是有覆盖，这样才可以在叶子节点的父节点放摄像头)
        if (cur == null) {//相当于是到了叶子节点
            return 2;
        }

        int left = traversal(cur.left);
        int right = traversal(cur.right);

        //情况一
        //左右节点都有覆盖
        // 此时情况为根节点只能为无覆盖，这样才能在当前根节点的父节点放摄像头
        if (left == 2 && right == 2) {
            return 0;
        }

        //情况二
        //left==0 && right==0 左右节点无覆盖
        //left==1 && right==0 左有摄像头，右无覆盖
        //left==0 && right==1 左无覆盖，右有摄像头
        //left==0 && right==2 左无覆盖，右有覆盖
        //left==2 && right==0 左有覆盖，右无覆盖
        //以上情况其父节点都需要设置摄像头
        if (left == 0 || right == 0) {
            result++;
            return 1;
        }

        //情况三
        //left==1 && right==2 左节点有摄像头，右节点有覆盖
        //left==2 && right==1 左节点有覆盖，右节点有摄像头
        //left==1 && right==1 左右节点都有摄像头
        //以上情况根节点已覆盖
        if (left == 1 || right == 1) {
            return 2;
        }

        //以上代码没有使用else，主要是为了把各个分支的条件展现出来，利于阅读
        //这个-1的逻辑不会走到这里
        return -1;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){this.val=val;}
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
