package dp;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的"非负整数"数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 */
public class RobI_198 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println("打家劫舍198为：" + robI(nums));
    }

    /**
     * 动态规划；T(O())  S(O())
     *
     *                五部曲：①确定dp数组以及下标的含义
     *                            dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]
     *                       ②确定递推公式
     *                            dp[i] = max(dp[i - 2] + nums[i], dp[i - 1]);
     *                       ③dp数组的初始化
     *                            dp[0] 一定是 nums[0]
     *                            dp[1] = max(nums[0], nums[1]);
     *                       ④确定遍历顺序
     *                            dp[i] 是根据dp[i - 2] 和 dp[i - 1] 推导出来的，那么一定是从前到后遍历！
     *                       ⑤举例推导dp数组
     *                                  输入[2,7,9,3,1]为例。
     *
     *
     * @param nums
     * @return
     */
    public static int robI(int[] nums) {
        if (nums==null||nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
