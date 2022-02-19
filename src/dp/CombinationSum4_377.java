package dp;

/**
 * 377. 组合总和 Ⅳ（动态规划：Carl称它为排列总和）
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 *
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 输入：nums = [9], target = 3
 * 输出：0
 */
public class CombinationSum4_377 {
    public static void main(String[] args) {

    }

    /**
     * 动态规划：（本题题目描述说是求组合，但又说是可以元素相同顺序不同的组合算两个组合，其实就是求排列！）
     *              五部曲
     *                  ①确定dp数组以及下标含义
     *                      dp[i]:凑成整数i的排列个数为dp[i]
     *                  ②确定递推导公式
     *                      dp[i]+=dp[i-nums[j]]
     *                      这个递推公式大家应该不陌生了，我在讲解01背包题目的时候在这篇动态规划：目标和！ (opens new window)中就讲解了，
     *                      求装满背包有几种方法，一般公式都是：dp[j] += dp[j - nums[i]];
     *                  ③初始化dp数组
     *                     因为递推公式dp[i] += dp[i - nums[j]]的缘故，dp[0]要初始化为1，这样递归其他dp[i]的时候才会有数值基础。
     *                     非0下标初始化为0，这样才不会影响dp[i]累加所有的dp[i - nums[j]]。
     *                  ④确定遍历顺序
     *                      个数可以不限使用，说明这是一个完全背包。就要使用下面的2.
     *                      （如果求组合数就是外层for循环遍历物品，内层for遍历背包。如果求排列数就是外层for遍历背包，内层for循环遍历物品。）
     *                      1.外层for循环遍历物品（钱币），内层for遍历背包（金钱总额）的情况
     *                          for (int i = 0; i < coins.size(); i++) { // 遍历物品
     *                                 for (int j = coins[i]; j <= amount; j++) { // 遍历背包容量
     *                                          dp[j] += dp[j - coins[i]];
     *                                   }
     *                           }
     *                           假设：coins[0] = 1，coins[1] = 5。
     *                           那么就是先把1加入计算，然后再把5加入计算，得到的方法数量只有{1, 5}这种情况。而不会出现{5, 1}的情况。
     *                           所以这种遍历顺序中dp[j]里计算的是“组合数”！
     *                          故：先遍历硬币再遍历总金额背包
     *
     *                       2.for (int j = 0; j <= amount; j++) { // 遍历背包容量
     *                               for (int i = 0; i < coins.size(); i++) { // 遍历物品
     *                                     if (j - coins[i] >= 0) dp[j] += dp[j - coins[i]];
     *                                  }
     *                          }
     *                          背包容量的每一个值，都是经过 1 和 5 的计算，包含了{1, 5} 和 {5, 1}两种情况。
     *                          此时dp[j]里算出来的就是“排列数”！
     *
     *                  ⑤举例推导动态数组
     *                      nums=[1,2,3]  target=4
     *                      dp[0]=1, dp[1]=dp[0]=1, dp[2]=dp[1]+dp[0]=2, dp[3]=dp[2]+dp[1]+dp[0]=4, dp[4]=dp[3]+dp[2]+dp[1]=7
     *
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
