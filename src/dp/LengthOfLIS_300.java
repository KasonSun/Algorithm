package dp;

import java.util.Arrays;

/**
 *  300. 最长递增子序列（可以不连续）
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 */
public class LengthOfLIS_300 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 2};
        System.out.println("最长递增子序列长度为:" + lengthOfLIS(nums));
    }

    /**
     *  动态规划；T(O(n^2))  S(O(n))
     *          动态规划五部曲：
     *                      ①dp数组的定义
     *                          dp[i]表示i之前包括i的最长上升子序列
     *
     *                      ②状态转移方程
     *                          位置i的最长升序子序列等于j从0到i-1各个位置的最长上升子序列+1的最大值
     *                          所以：if(nums[i]>nums[j]) dp[i]=max(dp[i],dp[j]+1)
     *                          注意这里不是要dp[i] 与 dp[j] + 1进行比较，而是我们要取dp[j] + 1的最大值。
     *
     *                      ③dp[i]的初始化
     *                          每一个i，对应的dp[i]（最长上升子序列）起始大小至少都是1
     *
     *                      ④确定遍历顺序
     *                          dp[i] 是有0到i-1各个位置的最长升序子序列 推导而来，那么遍历i一定是从前向后遍历。
     *                          j其实就是0到i-1，遍历i的循环在外层，遍历j则在内层，
     *
     *                      ⑤举例推导dp数组
     *                          输入：[0,1,0,3,2]
     *
     *
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {//nums[i]>nums[j]只是迭代过程对0-i-1每个位置单独判断，j位置是不是可以跟在i的后面，是不是可以满足上升子序列的要求
                    //一轮迭代即可迭代出当前i位置的最长上升子序列值
                    //关于这个也可以看看 https://www.bilibili.com/video/BV1XF411t76w?spm_id_from=333.337.search-card.all.click
                    dp[i] = Math.max(dp[i], dp[j] + 1);// 注意这里不是要dp[i] 与 dp[j] + 1进行比较，而是我们要取dp[j] + 1的最大值。
                }
                //System.out.println("dp["+i+"]："+dp[i]);
            }
            if (dp[i] > result) {
                result = dp[i];
            }
            //System.out.println("*****result："+result+"******");
        }
        return result;
    }
}
