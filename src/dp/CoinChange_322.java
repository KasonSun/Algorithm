package dp;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 */
public class CoinChange_322 {
    public static void main(String[] args) {

    }
    /**
     * 动态规划；完全背包
     *                五部曲：①确定dp数组以及下标的含义
     *                            dp[j]的定义为：凑足总额为j所需钱币的最少个数为dp[j]
     *                       ②确定递推公式
     *                            dp[j] = min(dp[j - coins[i]] + 1, dp[j]);
     *                       ③dp数组的初始化
     *                            首先凑足总金额为0所需钱币的个数一定是0，那么dp[0] = 0;
     *                            考虑到递推公式的特性，dp[j]必须初始化为一个最大的数，否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。
     *                            所以下标非0的元素都是应该是最大值。
     *                       ④确定遍历顺序
     *                            本题并不强调集合是组合还是排列，一般选取先遍历物品
     *                              coins（物品）放在外循环，target（背包）在内循环。且内循环正序。
     *                       ⑤举例推导dp数组
     *                                  coins=[1,2,5] amount=5
     *
     *                                  0 1 1 2 2 1
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int k = 1; k < dp.length; k++) {
            dp[k] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < coins.length; i++) {//遍历物品
            for (int j = coins[i]; j <= amount; j++) {//遍历背包
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {// 如果dp[j - coins[i]]是初始值则跳过
                    dp[j] = Math.min(dp[j], dp[j - coins[i]]+1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }
}
