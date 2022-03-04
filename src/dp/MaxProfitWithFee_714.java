package dp;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 *
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 *
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 *
 */
public class MaxProfitWithFee_714 {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println("with fee:" + maxProfit(prices, fee));
    }

    /**
     * 动态规划；T(O(n))  S(O(n))
     * 相对于动态规划：122.买卖股票的最佳时机II ，本题只需要在计算“卖出操作的时候减去手续费”就可以了，代码几乎是一样的。
     *
     * 唯一差别在于递推公式部分，所以本篇也就不按照动规五部曲详细讲解了，主要讲解一下递推公式部分。
     *     dp[i][0] 表示第i天持有股票所剩最多现金。 dp[i][1] 表示第i天不持有股票所得最多现金
     *     如果第i天持有股票即dp[i][0]， 那么可以由两个状态推出来
     *          第i-1天就持有股票，那么就保持现状，所得现金就是昨天持有股票的所得现金 即：dp[i - 1][0]
     *          第i天买入股票，所得现金就是昨天不持有股票的所得现金减去 今天的股票价格 即：dp[i - 1][1] - prices[i]
     *          所以：dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
     *     如果第i天不持有股票即dp[i][1]的情况， 依然可以由两个状态推出来
     *          第i-1天就不持有股票，那么就保持现状，所得现金就是昨天不持有股票的所得现金 即：dp[i - 1][1]
     *          第i天卖出股票，所得现金就是按照今天股票价格卖出后所得现金，注意这里需要有手续费了即：dp[i - 1][0] + prices[i] - fee
     *          所以：dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
     *
     *      初始化：dp[0][0]表示今天持有股票则一定是今天买入，不可能存在前一天：dp[0][0]=0-prices[0]
     *             dp[0][1]表示今天不持有股票，则一定是0 ：dp[0][1]=0
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return Math.max(dp[prices.length-1][0],dp[prices.length-1][1]);
    }
}
