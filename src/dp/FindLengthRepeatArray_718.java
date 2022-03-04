package dp;

/**
 *  718. 最长重复子数组（连续）
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 *
 *
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 *
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 */
public class FindLengthRepeatArray_718 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 2, 1};
        int[] nums2 = new int[]{3, 2, 1, 4, 7};
        System.out.println("最长相等子数组长度为：" + findLength(nums1, nums2));
    }

    /**
     * 动态规划：T(O(n*m))  S(O(n*m)) n为nums1长度，m为nums2的长度
     *
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i][j]:以下标i-1为结尾的num1数组，和以j-1为结尾的数组nums2，最长重复子数组为dp[i][j]
     *                      定义i-1和j-1是为了后面代码实现方便
     *
     *                  ②状态转移方程
     *                      dp[i][j]=dp[i-1][j-1]+1只能由前一个状态推出，遍历i,j从1开始
     *
     *                  ③dp的初始化
     *                      dp[i][j]定义可知，dp[i][0]和dp[j][0]都是没有意义的，
     *                      但dp[i][0] 和dp[0][j]要初始值，为了方便递归公式dp[i][j] = dp[i - 1][j - 1] + 1;
     *                      所以dp[i][0] 和dp[0][j]初始化为0
     *
     *                  ④确定遍历顺序
     *                      根据状态转移方程，可以选择外层遍历nums1，内层遍历nums2，同时在遍历过程中记录最大值
     *
     *                  ⑤举例推导dp数组
     *                      nums1=[1,2,3,2,1] nums2=[3,2,1,4,7]
     * @param nums1,nums2
     * @return
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];//初始化到长度+1实际上遍历过程是通过i-1和j-1作为游标实现的，要想取到i和j故需要初始化长度+1
        //初始化默认值为0，可以不再初始化
        int result = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > result) {
                    result = dp[i][j];
                }
            }
        }
        return result;
    }
}
