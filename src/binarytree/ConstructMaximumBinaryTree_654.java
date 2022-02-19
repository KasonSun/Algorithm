package binarytree;

/**
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 *
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 *
 */
public class ConstructMaximumBinaryTree_654 {
    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 9, 8, 7, 2};
        System.out.println("构造的最大二叉树为："+constructMaximumBinaryTree(nums).val);
    }

    /**
     * 递归实现(与根据前序和中序、中序和后序构造二叉树相对简单一点，代码基本一样)
     * 思路：构造数一般采用前序遍历，先构造中间节点，在递归构造左子树和右子树
     *      1.递归参数和返回值：参数：数组；返回值：利用数组构造的二叉树的头结点
     *      2.终止条件：数组长度大于等于1，不用考虑小于1的情况，如果传入的数组大小为1，说明到了叶子节点。利用这个值构造节点并且返回
     *      3.确定单层递归逻辑：
     *              ①先找到数组最大的值和对应的下标，最大值构造根节点
     *              利用下标分割数组：
     *              ②最大值所在的下标左区间，构造左子树（需要判断maxValueIndex>0，保证区间至少有一个数值）
     *              ③最大值所在的下标右区间，构造右子树（需要判断maxValueIndex<(num.length-1),保证区间至少有一个数值）
     * @param nums
     * @return
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructDetail(nums, 0, nums.length);
    }

    public static TreeNode constructDetail(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 1) {//没有元素了
            return null;
        }
        if (rightIndex - leftIndex == 1) {//只有一个元素
            return new TreeNode(nums[leftIndex]);
        }
        int maxIndex = leftIndex;//最大值所在位置
        int maxValue = nums[leftIndex];//最大值
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(maxValue);
        //根据maxIndex划分左右子树
        root.left = constructDetail(nums, leftIndex, maxIndex);
        root.right = constructDetail(nums, maxIndex + 1, rightIndex);
        return root;
    }
}
