package dp;

/**
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的"连续"子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 输入：nums = [1]
 * 输出：1
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 */
public class MaxSubArray_53 {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("最大子数组和为：" + maxSubArray01(nums));
    }

    /**
     * 1.动态规划：T(O(n))  S(O(n))
     *                  注意：需要保证连续并且和最大
     *              五部曲：
     *                  ①dp数组定义
     *                      dp[i]:包含下标i之前的最大连续子序列和dp[i]
     *
     *                  ②状态转移方程
     *                      dp[i]只有两个方向可以推出来：
     *                          dp[i - 1] + nums[i]，即：nums[i]加入当前连续子序列和；
     *                          nums[i]，即：从头开始计算当前连续子序列和
     *                     一定是取最大的，所以dp[i] = max(dp[i - 1] + nums[i], nums[i]);
     *
     *                  ③dp[i]的初始化
     *                      从递推公式可以看出来dp[i]是依赖于dp[i - 1]的状态，dp[0]就是递推公式的基础。
     *                      很明显 dp[0]就是nums[0]
     *
     *                  ④确定遍历顺序
     *                      根据状态转移方程，一定是从前向后遍历
     *
     *                  ⑤举例推导dp数组
     *                      输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     *                      注意注意最后的结果可不一定是dp[nums.size() - 1]！
     *                      因此需要在遍历过程中记录最大和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

    /**
     * 2.贪心法
     *      贪心准则：-2,1 计算起点的时候，一定是1开始计算，因为负数会拉低总和
     *      局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
     *      全局最优：选取最大“连续和”
     * @param nums
     * @return
     */
    public static int maxSubArray01(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > result) {
                result = count;
            }
            if (count <= 0) {
                count = 0;
            }
        }
        return result;
    }
}
