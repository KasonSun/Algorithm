package dp;

/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 */
public class FindMaxForm_474 {
    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println("一和零的结果为：" + findMaxForm(strs, m, n));
    }

    /**
     *  动态规划：使用0/1背包  T:O(m*n) S:O(m*n)
     *         strs数组里的元素就是物品，每个物品都是一个；m和n相当于是一个背包，两个维度的背包
     *
     *      五部曲：①确定dp数组以及下标的含义
     *               dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
     *            ②确定递推公式
     *               dp[i][j] 可以由前一个strs里的字符串推导出来，strs里的字符串有zeroNum个0，oneNum个1。
     *               dp[i][j] 就可以是 dp[i - zeroNum][j - oneNum] + 1。
     *              然后我们在遍历的过程中，取dp[i][j]的最大值。
     *              所以递推公式：dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
     *              对比一下就会发现，字符串的zeroNum和oneNum相当于物品的重量（weight[i]），字符串本身的个数相当于物品的价值（value[i]）
     *           ③dp数组的初始化
     *               因为物品价值不会是负数，初始为0，保证递推的时候dp[i][j]不会被初始值覆盖。
     *           ④确定遍历顺序
     *               for循环遍历物品，内层for循环遍历背包容量且从后向前遍历！
     *              那么本题也是，物品就是strs里的字符串，背包容量就是题目描述中的m和n
     *           ⑤举例推导dp数组
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        //dp[i][j]表示i个0和j个1时的最大子集
        int[][] dp = new int[m + 1][n + 1];
        int zeroNum, oneNum;//注意此处的初始化语句应该放在for循环中,每一次循环都得置0
        for (String str : strs) {
            zeroNum = 0;
            oneNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    zeroNum++;
                }else{
                    oneNum++;
                }
            }
            //倒序遍历
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] +1);
//                    System.out.println("i="+i+",j="+j+",dp[i][j]="+dp[i][j]);
                }
            }
        }
        return dp[m][n];
    }
}
