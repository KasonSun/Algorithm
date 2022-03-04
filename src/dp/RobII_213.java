package dp;

/**
 *213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 输入：nums = [1,2,3]
 * 输出：3
 *
 */
public class RobII_213 {
    public static void main(String[] args) {

    }

    /**
     * 本题相较于198题，唯一的区别就是成环了，
     *          对于一个数组成环有三种情况；
     *                          ①考虑不包含首尾元素
     *                          ②考虑包含首元素，不包含尾元素
     *                          ③考虑不包含首元素，包含尾元素
     *                          例 0  1  2  3  4
     *                             1  6  1  9  1
     *                          注意我这里用的是"考虑"，例如情况三，虽然是考虑包含尾元素，但不一定要选尾部元素！ 对于情况三，取nums[1] 和 nums[3]就是最大的。
     *                          而情况二 和 情况三 都包含了情况一了，所以只考虑情况二和情况三就可以了。
     * @param nums
     * @return
     */
    public static int robII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int result1 = rob(nums, 0, nums.length - 2);//情况二②考虑包含首元素，不包含尾元素
        int result2 = rob(nums, 1, nums.length - 1);//情况三③考虑不包含首元素，包含尾元素
        return Math.max(result1, result2);
    }

    //198.打家劫舍I的逻辑
    public static int rob(int[] nums,int start, int end) {
        if (start == end) {//nums只有两个元素的情况则情况二和三则会进入到此处if
            return nums[start];
        }
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start+2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }
}
