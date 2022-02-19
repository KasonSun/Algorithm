package greedy;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
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
 */
public class MaxProfit_714 {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println("买卖股票的最佳实际含手续费的结果为：" + maxProfit(prices, fee));
    }

    /**
     * 贪心法：T:O(n)  S:O(1)
     *      思路：贪心策略：最低值买，最高值（如果算上手续费还盈利）就卖
     *          此时就是找两个点：买入和卖出点
     *                      买入：遇到更低点就记录一下
     *                      卖出：这个相对就不那么好找，但是也没必要算出准确的卖出点，只要当前的价格大于（最低价格+手续费），
     *                           就可以获得利润，至于准确的卖出点，就是连续收获利润区间里的最后点（并不需要计算具体哪一点）
     *
     *                           收获利润操作时三种情况：①收获利润并不是收获利润区间里最后点（不是真正的卖出点，相当于持有股票）
     *                                               ②前一天是收获利润区间里的最后点（相当于真正的卖出了），今天要重新记录最小价格了
     *                                               ③不作操作，保持原有状态（买入，卖出，不买不卖）
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        int result = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            //情况②：相当于买入
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            //情况③：保持原有状态（因为此时买不便宜，卖亏本）
            if (prices[i] > minPrice && prices[i] <= minPrice + fee) {
                continue;
            }

            //计算利润，可能有多次计算利润，最后一次计算利润才是真正意义的卖出
            if (prices[i] > minPrice + fee) {
                result += prices[i] - minPrice - fee;
                minPrice = prices[i] - fee;//情况①，这一步很关键
                //从代码中可以看出对情况①的操作，因为如果还在收获利润的区间里，表示并不是真正的卖出，
                // 而计算利润每次都要减去手续费，所以要让minPrice = prices[i] - fee;，这样在明天收获利润的时候，才不会多减一次手续费！
            }
        }
        return result;
    }
}
