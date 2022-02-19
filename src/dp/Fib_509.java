package dp;

/**
 * 509. 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 *
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 *
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 *
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 */
public class Fib_509 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("fib:" + fibRecur(n));
    }

    /**
     *  动态规划；T:O(n) S:O(n)
     *          五部曲：①确定dp[i]数组以及下标的含义
     *                      dp[i]的定义为：第i个数的斐波那契数值
     *                 ②确定递推公式
     *                      状态转移方程：dp[i]=dp[i-1]+dp[i-2]
     *                 ③dp数组的初始化
     *                      dp[0]=0,dp[1]=1
     *                 ④确定遍历顺序
     *                      递推公式可以看出，遍历顺序一定是从前到后的
     *                 ⑤举例推导dp数组
     *                      按照递归公式，当n为10时候，dp数组应该是下面的数列：
     *                              0 1 1 2 3 5 8 13 21 34 55
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];//要得到n对应fib数值，则i可以取到n，因此数组长度为n+1
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     *  动态规划；T:O(n) S:O(1) 实际上只需要两个数值就可以维护，不需要记录整个序列
     *          五部曲：①确定dp[i]数组以及下标的含义
     *                      dp[i]的定义为：第i个数的斐波那契数值
     *                 ②确定递推公式
     *                      状态转移方程：dp[i]=dp[i-1]+dp[i-2]
     *                 ③dp数组的初始化
     *                      dp[0]=0,dp[1]=1
     *                 ④确定遍历顺序
     *                      递推公式可以看出，遍历顺序一定是从前到后的
     *                 ⑤举例推导dp数组
     *                      按照递归公式，当n为10时候，dp数组应该是下面的数列：
     *                              0 1 1 2 3 5 8 13 21 34 55
     * @param n
     * @return
     */
    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[2];//要得到n对应fib数值，则i可以取到n，因此数组长度为n+1
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }
        return dp[1];
    }

    /**
     * 递归法 T:O(n^2) S:O(n)
     * @param n
     * @return
     */
    public static int fibRecur(int n) {
        if(n<2){
            return n;
        }
        return fibRecur(n - 1) + fibRecur(n - 2);
    }
}
