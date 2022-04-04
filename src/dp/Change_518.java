package dp;

/**
 * 518. 零钱兑换 II  (零钱兑换I-NC_BM.70)
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 输入：amount = 10, coins = [10]
 * 输出：1
 */
public class Change_518 {
    public static void main(String[] args) {

    }

    /**
     * 动态规划：
     *              五部曲
     *                  ①确定dp数组以及下标含义
     *                      dp[j]:凑成总金额j的货币组合数为dp[j]
     *                  ②确定递推导公式
     *                      dp[j]+=dp[j-coins[i]]
     *                      这个递推公式大家应该不陌生了，我在讲解01背包题目的时候在这篇动态规划：目标和！ (opens new window)中就讲解了，
     *                      求装满背包有几种方法，一般公式都是：dp[j] += dp[j - nums[i]];
     *                  ③初始化dp数组
     *                      首先dp[0]一定要为1，dp[0] = 1是 递归公式的基础
     *                      dp[0]=1 凑成总金额0的货币组合数为1
     *                      下标非0的dp[j]初始化为0，这样累计加dp[j - coins[i]]的时候才不会影响真正的dp[j]
     *                  ④确定遍历顺序
     *                      1.外层for循环遍历物品（钱币），内层for遍历背包（金钱总额）的情况
     *                          for (int i = 0; i < coins.size(); i++) { // 遍历物品
     *                                 for (int j = coins[i]; j <= amount; j++) { // 遍历背包容量
     *                                          dp[j] += dp[j - coins[i]];
     *                                   }
     *                           }
     *                           假设：coins[0] = 1，coins[1] = 5。
     *                           那么就是先把1加入计算，然后再把5加入计算，得到的方法数量只有{1, 5}这种情况。而不会出现{5, 1}的情况。
     *                           所以这种遍历顺序中dp[j]里计算的是组合数！
     *                          故：先遍历硬币再遍历总金额背包
     *
     *                       2.for (int j = 0; j <= amount; j++) { // 遍历背包容量
     *                               for (int i = 0; i < coins.size(); i++) { // 遍历物品
     *                                     if (j - coins[i] >= 0) dp[j] += dp[j - coins[i]];
     *                                  }
     *                          }
     *                          背包容量的每一个值，都是经过 1 和 5 的计算，包含了{1, 5} 和 {5, 1}两种情况。
     *                          此时dp[j]里算出来的就是排列数！
     *
     *                  ⑤举例推导动态数组
     *
     *                 注意题目描述中是凑成总金额的硬币组合数，为什么强调是组合数呢？
     *                 5 = 2 + 2 + 1;5 = 2 + 1 + 2
     *                 上面两种若问的是排列数，那么就是两种排列；若问的是组合数，那么就是一种组合，
     *                 组合不强调元素之间的顺序，排列强调元素之间的顺序。
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
