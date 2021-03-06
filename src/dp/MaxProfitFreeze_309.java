package dp;

/**
 *309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 输入: prices = [1]
 * 输出: 0
 *
 */
public class MaxProfitFreeze_309 {
    public static void main(String[] args) {

    }

    /**
     * 动态规划；T(O(n))  S(O(n))
     *  相对于122，加上一个冷冻期
     *                 五部曲：①确定dp含义
     *                           dp[i][j]，第i天状态为j，所剩的最多现金为dp[i][j]。
     *                           状态一：买入股票状态（今天买入股票，或者是之前就买入了股票然后没有操作）
     *                           卖出股票状态，这里就有两种卖出股票状态
     *                                  状态二：两天前就卖出了股票，度过了冷冻期，一直没操作，今天保持卖出股票状态
     *                                  状态三：今天卖出了股票
     *                           状态四：今天为冷冻期状态，但冷冻期状态不可持续，只有一天！
     *
     *                           j的状态：0状态一；1状态二；2状态三；3状态四
     *
     *                           注意这里的每一个状态，例如状态一，是买入股票状态并不是说今天已经就买入股票，
     *                           而是说保存买入股票的状态即：可能是前几天买入的，之后一直没操作，所以保持买入股票的状态。
     *
     *                       ②确定递推公式
     *                           达到买入股票状态（状态一）即：dp[i][0]，有两个具体操作：
     *                              操作一：前一天就是持有股票状态（状态一），dp[i][0] = dp[i - 1][0]
     *                              操作二：今天买入了，有两种情况
     *                                  前一天是冷冻期（状态四），dp[i - 1][3] - prices[i]
     *                                  前一天是保持卖出股票状态（状态二），dp[i - 1][1] - prices[i]
     *                                  所以操作二取最大值，即：max(dp[i - 1][3], dp[i - 1][1]) - prices[i]
     *                             那么dp[i][0] = max(dp[i - 1][0], max(dp[i - 1][3], dp[i - 1][1]) - prices[i]);
     *
     *                           达到保持卖出股票状态（状态二）即：dp[i][1]，有两个具体操作：
     *                              操作一：前一天就是状态二
     *                              操作二：前一天是冷冻期（状态四）
     *                              dp[i][1] = max(dp[i - 1][1], dp[i - 1][3]);
     *
     *                          达到今天就卖出股票状态（状态三），即：dp[i][2] ，只有一个操作：
     *                              操作一：昨天一定是买入股票状态（状态一），今天卖出（注意冷冻期只出现在卖出后）
     *                              即：dp[i][2] = dp[i - 1][0] + prices[i];
     *
     *                          达到冷冻期状态（状态四），即：dp[i][3]，只有一个操作：
     *                              操作一：昨天卖出了股票（状态三）
     *                              dp[i][3] = dp[i - 1][2];
     *
     *                              dp[i][0] = max(dp[i - 1][0], max(dp[i - 1][3], dp[i - 1][1]) - prices[i]);
     *                              dp[i][1] = max(dp[i - 1][1], dp[i - 1][3]);
     *                              dp[i][2] = dp[i - 1][0] + prices[i];
     *                              dp[i][3] = dp[i - 1][2];
     *
     *                       ③dp数组如何初始化
     *                              如果是持有股票状态（状态一）那么：dp[0][0] = -prices[0]，买入股票所剩现金为负数。
     *                              保持卖出股票状态（状态二），第0天没有卖出dp[0][1]初始化为0就行，
     *                              今天卖出了股票（状态三），同样dp[0][2]初始化为0，因为最少收益就是0，绝不会是负数。
     *                              同理dp[0][3]也初始为0。
     *
     *                       ④确定遍历顺序
     *                              从递推公式可以看出dp[i]都是有dp[i - 1]推导出来的，那么一定是从前向后遍历。
     *
     *
     *                       ⑤举例推导dp数组
     *                                  输入[1,2,3,0,2]
     *
     *                                  最后结果是取 状态二，状态三，和状态四的最大值，不少同学会把状态四忘了，状态四是冷冻期，最后一天如果是冷冻期也可能是最大值。。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3], dp[i - 1][1])- prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2];
        }
        return Math.max(dp[prices.length - 1][3], Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]));
    }
}
