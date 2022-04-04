package dp;

/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y(x<y)，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 *
 * 输入：stones = [1,2]
 * 输出：1
 */
public class LastStoneWeightII_1049 {
    public static void main(String[] args) {

    }

    /**
     * 动态规划：使用0/1背包  T:O(m*n) S:O(m) m是石头总重（准确的说是总重的一半），n为石头块数
     *      本题和之前的416题分割等和子集很像；本题重量为stones[i]，物品的价值也为stones[i]
     *      "本题其实就是尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，这样就化解成01背包问题了。"
     *     五部曲：①确定dp数组以及下标的含义
     *              dp[j]表示 背包总容量(实际上就是重量)是j，最多可以别dp[j]这么重的石头。
     *           ②确定递推公式
     *              dp[j] = max(dp[j], dp[j - stones[i]] + stones[i])
     *           ③dp数组的初始化
     *              因为重量都不会是负数，所以dp[j]都初始化为0就可以了，这样在递归公式dp[j] = max(dp[j], dp[j - stones[i]] + stones[i]);中dp[j]才不会初始值所覆盖
     *           ④确定遍历顺序
     *              在动态规划：关于01背包问题（滚动数组）中就已经说明：
     *              如果使用一维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒序遍历！
     *           ⑤举例推导dp数组
     *              最后dp[target]里是容量为target的背包所能背的最大重量
     *              那么分成两堆石头，一堆石头的总重量是dp[target]，另一堆就是sum - dp[target]
     *              在计算target的时候，target = sum / 2 因为是"向下取整"，所以sum - dp[target] 一定是大于等于dp[target]的。
     *              那么相撞之后剩下的最小石头重量就是 (sum - dp[target]) - dp[target]
     *
     * @param stones
     * @return
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }

        int target = sum / 2;
        //初始化数组
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            //倒序
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }
}
