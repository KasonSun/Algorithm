package dp;

/**
 * 0/1背包遍历：二维数组（物品和背包遍历顺序可以颠倒）；一维数组（一定是先遍历物品，再遍历背包，且背包倒序遍历）
 * 完全背包：二维数组（物品和背包遍历顺序可以颠倒）；一维数组（物品和背包遍历顺序可以颠倒，背包从小到大遍历）
 *
 *   动态规划：经典0/1背包(二维数组)
 *          int[] weight = {1, 3, 4};
 *          int[] value = {15, 20, 30};
 *          int bagsize = 4;
 */
public class Bag01_carl {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagsize = 4;
        bag01(weight, value, bagsize);
    }

    /**
     * 动态规划；T:O(n^2) S:O(n)
     *                五部曲：①确定dp数组以及下标的含义
     *                               dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少
     *                       ②确定递推公式
     *                              那么可以有两个方向推出来dp[i][j]
     *                             放与不放物品i：dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
     *                       ③dp数组的初始化
     *                          两种初始化方式：
     *                            如果背包容量j为0的话，即dp[i][0]，无论是选取哪些物品，背包价值总和一定为0，即为dp[i][0]=0;
     *                            如果放物品0的话，dp[0][j]=value[0] (此时j>=weight[0]);dp[0][j]=0 (此时j<weight[0])
     *                       ④确定遍历顺序
     *                            节点数为i的状态是依靠 i之前节点数的状态。那么遍历i里面每一个数作为头结点的状态，用j来遍历。
     *                       ⑤举例推导dp数组
     *                             n=5      0  1  2  3  4  5
     *                             dp[i]    1  1  2  5  14 42
     * @param weight,value,bagsize
     * @return
     */
    public static void bag01(int[] weight, int[] value, int bagsize) {
        //dp数组，dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        int[][] dp = new int[weight.length][bagsize + 1];

//        for (int j = 0 ; j < weight[0]; j++) {  // 当然这一步，如果把dp数组预先初始化为0了，这一步就可以省略，但很多同学应该没有想清楚这一点。
//            dp[0][j] = 0;
//        }
        //初始化
        for (int j = weight[0]; j <= bagsize; j++) {
            dp[0][j] = value[0];
        }

        //遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= bagsize; j++) {
                //此时不能放i（0/1的0）
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //此时能放i（0/1的1）,但需要比较取最大价值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        //打印dp数组
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= bagsize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
